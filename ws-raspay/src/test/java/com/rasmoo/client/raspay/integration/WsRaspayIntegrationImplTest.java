package com.rasmoo.client.raspay.integration;


import com.rasmoo.client.raspay.dto.CreditCardDto;
import com.rasmoo.client.raspay.dto.CustomerDto;
import com.rasmoo.client.raspay.dto.OrderDto;
import com.rasmoo.client.raspay.dto.PaymentDto;
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
        CustomerDto dto = new CustomerDto(null, "Erick", "Silva",
                "teste@gmail.com", "38504660869");

        wsRaspayIntegration.createCustomer(dto);

    }


    @Test
    void createOrderDtoWhenDtoOk() {

        OrderDto dto = new OrderDto(null, "6584ef473529c115c5351923",
                "MONTH22", BigDecimal.ZERO);

        wsRaspayIntegration.createOrder(dto);

    }

    @Test
    void processPaymentWhenDtoOk() {

        CreditCardDto cardDto = new CreditCardDto("1234123412341234", 123L, 06L, 2025L,
                "38504660869", 0L);

        PaymentDto paymentDto = new PaymentDto("6584ef473529c115c5351923", "65851c07b5151308bfd68fa5", cardDto);

        wsRaspayIntegration.processPayment(paymentDto);

    }

}
