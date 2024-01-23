package com.client.wsRasmooplus.service.impl;

import com.client.wsRasmooplus.Exception.BadRequestException;
import com.client.wsRasmooplus.Exception.BusinessException;
import com.client.wsRasmooplus.Exception.NotFoundException;
import com.client.wsRasmooplus.dto.PaymentProcessDto;
import com.client.wsRasmooplus.dto.wsraspay.CustomerDto;
import com.client.wsRasmooplus.dto.wsraspay.OrderDto;
import com.client.wsRasmooplus.dto.wsraspay.PaymentDto;
import com.client.wsRasmooplus.enums.UserTypeEnums;
import com.client.wsRasmooplus.integration.EmailIntegration;
import com.client.wsRasmooplus.integration.WsRaspayIntegration;
import com.client.wsRasmooplus.mapper.UserPaymentInfoMapper;
import com.client.wsRasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.wsRasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.wsRasmooplus.mapper.wsraspay.OrderMapper;
import com.client.wsRasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.wsRasmooplus.model.SubscriptionsType;
import com.client.wsRasmooplus.model.User;
import com.client.wsRasmooplus.model.UserCredentials;
import com.client.wsRasmooplus.model.UserType;
import com.client.wsRasmooplus.repository.*;
import com.client.wsRasmooplus.service.PaymentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final WsRaspayIntegration wsRaspayIntegration;
    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final EmailIntegration emailIntegration;
    private final UserCredentialsRepository userDetailsRepository;
    private final UserTypeRepository userTypeRepository;
    private final SubscriptionsTypeRepository subscriptionsTypeRepository;

    @Value("${webservices.rasplus.jwt.password}")
    private String passwordDefault;

    @Override
    public Boolean process(PaymentProcessDto dto) {


        try {
            // verificar se o usuario existe e tem assinatura
            User user = getUser(dto);

            if (Objects.nonNull(user.getSubscriptionsTypeId())) {
                throw new BusinessException("O pagamento não pode ser processado, o usuário já possui assunatura");
            }


            //criar pedido de pagamento
            OrderDto orderDto = orderDtoIntegration(dto, user);

            //processar o pagamento
            PaymentDto paymentDto = getPaymentDto(dto, orderDto, user);


            return createUserCredentialsAndProcessPayment(dto, paymentDto, user);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }


    private CustomerDto customerDtoIntegration(User user) {
        //criar ou atualizar o usuario no raspay
        CustomerDto customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.buildCustomerDto(user));
        return customerDto;
    }

    private OrderDto orderDtoIntegration(PaymentProcessDto dto, User user) {
        //criar pedido de pagamento
        OrderDto orderDto = wsRaspayIntegration.createOrder(OrderMapper.build(customerDtoIntegration(user).getId(), dto));
        return orderDto;
    }

    private boolean createUserCredentialsAndProcessPayment(PaymentProcessDto dto, PaymentDto paymentDto, User user) {
        Boolean sucess = wsRaspayIntegration.processPayment(paymentDto);
        if (Boolean.TRUE.equals(sucess)) {

            userPaymentInfoRepository.save(UserPaymentInfoMapper.fromDtoToEntity(dto.userPaymentInfoDto(), user));

            //Assim que ele pagar ja cria o usuario dele
            createLoginUserCredentials(user);

            user.setSubscriptionsTypeId(getSubscriptionsType(dto));
            userRepository.save(user);
            sendEmail(user);
            return true;
        }
        return false;
    }

    private void createLoginUserCredentials(User user) {
        UserCredentials userCredentials =
                new UserCredentials(null, user.getEmail(),
                        new BCryptPasswordEncoder().encode(passwordDefault), getUserType().get());
        userDetailsRepository.save(userCredentials);
    }

    private SubscriptionsType getSubscriptionsType(PaymentProcessDto dto) {
        var subscriptionOpt = subscriptionsTypeRepository.findByProductKey(dto.processKey());

        if (subscriptionOpt.isEmpty()) {
            throw new NotFoundException("SubscriptionType não existe");
        }
        return subscriptionOpt.get();
    }

    private PaymentDto getPaymentDto(PaymentProcessDto dto, OrderDto orderDto, User user) {
        return PaymentMapper.buildPaymentDto(orderDto.getCustomerId(), orderDto.getId(),
                CreditCardMapper.buildCardNumbertDto(dto.userPaymentInfoDto(), user.getCpf()));
    }


    private Optional<UserType> getUserType() {
        var userType = userTypeRepository.findById(UserTypeEnums.ALUNO.getId());

        if (userType.isEmpty()) {
            throw new NotFoundException("Us erType não encontrado");
        }
        return userType;
    }

    private User getUser(PaymentProcessDto dto) {
        Optional<User> userOptional = userRepository.findById(dto.userPaymentInfoDto().userId());

        if (userOptional.isEmpty()) {
            throw new NotFoundException("Usuario não encontrado.");
        }
        return userOptional.get();
    }

    private void sendEmail(User user) {
        //enviar email de criacao de conta
        emailIntegration.sendEmail(user.getEmail(), "Acesso liberado!",
                "Usuario: " + user.getEmail() + "\nSenha: rasmooplus");
    }


}
