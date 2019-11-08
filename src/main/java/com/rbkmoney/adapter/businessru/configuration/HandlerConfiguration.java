package com.rbkmoney.adapter.businessru.configuration;

import com.rbkmoney.adapter.businessru.handler.BusinessRuServerHandler;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.converter.CashRegAdapterServiceLogDecorator;
import com.rbkmoney.damsel.cashreg.provider.CashRegProviderSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfiguration {

    @Bean
    @Autowired
    public CashRegProviderSrv.Iface serverHandlerLogDecorator(BusinessRuServerHandler serverHandler) {
        return new CashRegAdapterServiceLogDecorator(serverHandler);
    }

}
