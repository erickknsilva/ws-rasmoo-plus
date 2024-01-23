package com.rasmoo.client.raspay.controller.impl;

import com.rasmoo.client.raspay.controller.CustomerController;
import com.rasmoo.client.raspay.dto.CustomerDto;
import com.rasmoo.client.raspay.model.CustomerModel;
import com.rasmoo.client.raspay.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/customer")
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService;

    @Autowired
    CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.create(customerDto));
    }


}
