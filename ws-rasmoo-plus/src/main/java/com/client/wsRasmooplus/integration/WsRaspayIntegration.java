package com.client.wsRasmooplus.integration;

import com.client.wsRasmooplus.dto.wsraspay.CustomerDto;
import com.client.wsRasmooplus.dto.wsraspay.OrderDto;
import com.client.wsRasmooplus.dto.wsraspay.PaymentDto;

import javax.validation.Valid;

public interface WsRaspayIntegration {

    CustomerDto createCustomer(CustomerDto dto);

    OrderDto createOrder(OrderDto dto);

    Boolean processPayment(@Valid PaymentDto dto);


}
