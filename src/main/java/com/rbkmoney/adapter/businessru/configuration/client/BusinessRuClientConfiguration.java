package com.rbkmoney.adapter.businessru.configuration.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.businessru.service.businessru.BussinessRuApi;
import com.rbkmoney.adapter.businessru.service.businessru.BussinessRuClient;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.utils.converter.ip.ConverterIp;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class BusinessRuClientConfiguration {

    @Bean
    BussinessRuClient bussinessRuClient(ObjectMapper objectMapper, RestTemplate restTemplate, ConverterIp converterIp) {
        return new BussinessRuClient(new BussinessRuApi(restTemplate, objectMapper, converterIp));
    }

}
