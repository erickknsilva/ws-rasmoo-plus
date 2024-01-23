package com.rasmoo.client.raspay.integration.impl;

import com.rasmoo.client.raspay.dto.CustomerDto;
import com.rasmoo.client.raspay.dto.OrderDto;
import com.rasmoo.client.raspay.dto.PaymentDto;
import com.rasmoo.client.raspay.integration.WsRaspayIntegration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public WsRaspayIntegrationImpl() {
        restTemplate = new RestTemplate();
        headers = getHttpHeaders();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {

        try {

            HttpEntity<CustomerDto> request = new HttpEntity<>(dto, this.headers);
            ResponseEntity<CustomerDto> response =
                    restTemplate.exchange("http://localhost:8081/ws-raspay/v1/customer", HttpMethod.POST,
                            request, CustomerDto.class);


            return response.getBody();


        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
            e.getStackTrace();
            e.getClass();
            throw new RuntimeException("Erro ao criar cliente: " + e.getMessage());
        }

    }

    @Override
    public OrderDto createOrder(OrderDto dto) {

        try {

            HttpEntity<OrderDto> request = new HttpEntity<>(dto, this.headers);

            ResponseEntity<OrderDto> response = restTemplate.exchange("http://localhost:8081/ws-raspay/v1/order",
                    HttpMethod.POST, request, OrderDto.class);

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
            e.getStackTrace();
            e.getClass();
            throw new RuntimeException("Erro ao criar pedido: " + e.getMessage());
        }


    }

    @Override
    public Boolean processPayment(PaymentDto dto) {

        try {

            HttpEntity<PaymentDto> request = new HttpEntity<>(dto, this.headers);

            ResponseEntity<Boolean> response =
                    restTemplate.exchange("http://localhost:8081/ws-raspay/v1/payment/credit-card",
                            HttpMethod.POST, request, Boolean.class);

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
            e.getStackTrace();
            e.getClass();
            throw new RuntimeException("Erro ao criar pagamento: " + e.getMessage());
        }

    }

    private static HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();

        String credential = "rasmooplus:r@sm00";
        String basse64 = new String(Base64.encodeBase64(credential.getBytes()));
        headers.add("Authorization", "Basic " + basse64);
        return headers;
    }

}
