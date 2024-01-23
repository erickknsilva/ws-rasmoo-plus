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
class EmailIntegrationTest {

    @Autowired
    private EmailIntegration emailIntegration;


    @Test
    void createSendEmailTest() {

        emailIntegration.sendEmail("erickk.nunes100@gmail.com",
                "Acesso Liberado!", "Testando RasmooPlus");

    }

}
