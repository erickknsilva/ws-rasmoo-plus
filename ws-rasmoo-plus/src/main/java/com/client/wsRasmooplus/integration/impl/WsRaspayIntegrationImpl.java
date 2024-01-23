package com.client.wsRasmooplus.integration.impl;

import com.client.wsRasmooplus.Exception.NotFoundException;
import com.client.wsRasmooplus.dto.wsraspay.CustomerDto;
import com.client.wsRasmooplus.dto.wsraspay.OrderDto;
import com.client.wsRasmooplus.dto.wsraspay.PaymentDto;
import com.client.wsRasmooplus.integration.WsRaspayIntegration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

    @Value("${webservices.raspay.host}")
    private String raspayHost;

    @Value("${webservices.raspay.v1.customer}")
    private String customerUrl;

    @Value("${webservices.raspay.v1.order}")
    private String orderUrl;

    @Value("${webservices.raspay.v1.payment}")
    private String paymentUrl;

    private final HttpHeaders headers;
    private final RestTemplate restTemplate;

    public WsRaspayIntegrationImpl() {
        this.restTemplate = new RestTemplate();
        this.headers = getHeaders();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {

        try {

            HttpEntity<CustomerDto> request = new HttpEntity<>(dto, this.headers);

            ResponseEntity<CustomerDto> response =
                    restTemplate.exchange(raspayHost + customerUrl,
                            HttpMethod.POST, request, CustomerDto.class);

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar Cliente: " + e.getMessage());
        }

    }


    @Override
    public OrderDto createOrder(OrderDto dto) {
        try {

            HttpEntity<OrderDto> request = new HttpEntity<>(dto, this.headers);

            ResponseEntity<OrderDto> response =
                    restTemplate.exchange(raspayHost + orderUrl,
                            HttpMethod.POST, request, OrderDto.class);

            return response.getBody();
        } catch (Exception e) {
            throw new NotFoundException("Erro ao criar pedido! Sigla do produto inexistente");
        }

    }

    @Override
    public Boolean processPayment(PaymentDto dto) {

        try {

            HttpEntity<PaymentDto> request = new HttpEntity<>(dto, this.headers);

            ResponseEntity<Boolean> response =
                    restTemplate.exchange(raspayHost + paymentUrl,
                            HttpMethod.POST, request, Boolean.class);

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer o pagamento: " + e.getMessage());
        }
    }

    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credential = "rasmooplus:r@sm00";
        String base64 = Base64.encodeBase64String(credential.getBytes());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + base64);
        return headers;
    }


}
