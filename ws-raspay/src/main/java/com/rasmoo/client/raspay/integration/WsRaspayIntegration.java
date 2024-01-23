package com.rasmoo.client.raspay.integration;

import com.rasmoo.client.raspay.dto.CustomerDto;
import com.rasmoo.client.raspay.dto.OrderDto;
import com.rasmoo.client.raspay.dto.PaymentDto;

public interface WsRaspayIntegration {


    CustomerDto createCustomer(CustomerDto dto);

    OrderDto createOrder(OrderDto dto);

    Boolean processPayment(PaymentDto dto);
}
