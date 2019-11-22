package com.rbkmoney.adapter.businessru.configuration;


import com.rbkmoney.adapter.businessru.processor.ErrorProcessor;
import com.rbkmoney.adapter.businessru.processor.SuccessProcessor;
import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.EntryStateModel;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.ExitStateModel;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.processor.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProcessorConfiguration {

    @Bean
    public Processor<ExitStateModel, EntryStateModel, CommonResponse> responseProcessorChain() {
        ErrorProcessor errorProcessor = new ErrorProcessor();
        return new SuccessProcessor(errorProcessor);
    }

}
