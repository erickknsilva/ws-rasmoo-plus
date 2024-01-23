package com.client.wsRasmooplus.integration;


import com.client.wsRasmooplus.dto.wsraspay.CreditCardDto;
import com.client.wsRasmooplus.dto.wsraspay.CustomerDto;
import com.client.wsRasmooplus.dto.wsraspay.OrderDto;
import com.client.wsRasmooplus.dto.wsraspay.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;


    @Test
    void createCustomerWhenDtoOk() {
        CustomerDto dto = new CustomerDto(null, "Erick",
                "Silva", "erick@gmail.com", "09966556036");

        wsRaspayIntegration.createCustomer(dto);

    }


    @Test
    void createOrderWhenDtoOK() {
        OrderDto dto = new OrderDto(null, "65873934784f5324ec695ce3",
                "YEAR22", BigDecimal.TEN);


        wsRaspayIntegration.createOrder(dto);
//        65866cfd934d63253d8fcd62 65866776934d63253d8fcd5f
    }

    @Test
    void processPaymentWhenDtoOk() {

        CreditCardDto creditCardDto = new CreditCardDto("1234123412341234", 545L,
                07L, 2025L, "09966556036", 2);

        PaymentDto dto = new PaymentDto("65873934784f5324ec695ce3",
                "65873ded784f5324ec695ce7", creditCardDto);

        wsRaspayIntegration.processPayment(dto);

    }
}
