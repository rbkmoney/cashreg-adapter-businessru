package com.rbkmoney.adapter.businessru.configuration.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.businessru.service.businessru.BusinessRuApi;
import com.rbkmoney.adapter.businessru.service.businessru.BusinessRuClient;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.utils.converter.ip.ConverterIp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BusinessRuClientConfiguration {

    @Bean
    BusinessRuClient businessRuClient(ObjectMapper objectMapper, RestTemplate restTemplate, ConverterIp converterIp) {
        return new BusinessRuClient(new BusinessRuApi(restTemplate, objectMapper, converterIp));
    }

}
