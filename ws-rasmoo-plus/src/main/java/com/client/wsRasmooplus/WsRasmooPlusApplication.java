package com.client.wsRasmooplus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Rasmoo Plus", version = "0.0.1",
                description = "Api para atender o client Rasmoo Plus"))
@Configuration
@SpringBootApplication
public class WsRasmooPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsRasmooPlusApplication.class, args);
    }

}
