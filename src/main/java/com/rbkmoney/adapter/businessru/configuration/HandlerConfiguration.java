package com.rbkmoney.adapter.businessru.configuration;

import com.rbkmoney.adapter.businessru.handler.BusinessRuServerHandler;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.converter.CashregAdapterServiceLogDecorator;
import com.rbkmoney.damsel.cashreg.adapter.CashregAdapterSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfiguration {

    @Bean
    @Autowired
    public CashregAdapterSrv.Iface serverHandlerLogDecorator(BusinessRuServerHandler serverHandler) {
        return new CashregAdapterServiceLogDecorator(serverHandler);
    }

}
