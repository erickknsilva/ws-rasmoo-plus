package com.client.wsRasmooplus.service;

import com.client.wsRasmooplus.dto.PaymentProcessDto;
import javax.validation.Valid;

public interface PaymentInfoService {

    Boolean process(@Valid PaymentProcessDto dto);

}
