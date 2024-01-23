package com.client.wsRasmooplus.integration;

public interface EmailIntegration {

    void sendEmail(String emailTo, String subject, String message);
}
