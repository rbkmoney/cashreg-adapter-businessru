package com.rbkmoney.adapter.businessru.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.businessru.configuration.properties.BusinessRuProperties;
import com.rbkmoney.adapter.businessru.service.businessru.BusinessRuApi;
import com.rbkmoney.adapter.businessru.service.businessru.BusinessRuClient;
import com.rbkmoney.adapter.businessru.utils.ConverterIp;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(BusinessRuProperties.class)
public class BusinessRuClientconfiguration {

    @Bean
    //@RequestScope
    BusinessRuClient businessRuClient(
            ObjectMapper objectMapper,
            RestTemplate restTemplate,
            BusinessRuProperties businessRuProperties
    ) {
        BusinessRuApi businessRuApi = new BusinessRuApi(restTemplate, objectMapper, new ConverterIp(), businessRuProperties);
        return new BusinessRuClient(businessRuApi, businessRuProperties);
    }


}
