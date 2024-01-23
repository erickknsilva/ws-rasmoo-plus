package com.client.wsRasmooplus.controller;

import com.client.wsRasmooplus.dto.PaymentProcessDto;
import com.client.wsRasmooplus.service.PaymentInfoService;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public record PaymentInfoController(PaymentInfoService paymentInfoService) {

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> processPayment(@Valid @RequestBody PaymentProcessDto dto) {
        return ResponseEntity.ok(paymentInfoService.process(dto));
    }

}
