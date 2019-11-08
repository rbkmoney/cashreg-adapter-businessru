package com.rbkmoney.adapter.businessru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class CashRegBusinessRuApplication extends SpringApplication{
    public static void main(String[] args) {
        SpringApplication.run(CashRegBusinessRuApplication.class, args);
    }
}
