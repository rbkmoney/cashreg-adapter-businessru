package com.rbkmoney.adapter.businessru.handler.cashreg;

import com.rbkmoney.adapter.businessru.service.businessru.BusinessRuClient;
import com.rbkmoney.adapter.businessru.service.businessru.model.request.CommonRequest;
import com.rbkmoney.adapter.businessru.service.businessru.model.request.RequestWrapper;
import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.constant.TargetType;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.handler.CommonHandlerImpl;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.EntryStateModel;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.ExitStateModel;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.Step;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.processor.Processor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateRefundDebitCommonHandler
        extends CommonHandlerImpl<ExitStateModel, RequestWrapper<CommonRequest>, CommonResponse, EntryStateModel> {

    public CreateRefundDebitCommonHandler(
            BusinessRuClient client,
            Converter<EntryStateModel, RequestWrapper<CommonRequest>> converter,
            Processor<ExitStateModel, EntryStateModel, CommonResponse> responseProcessorChain
    ) {
        super(client::refundDebit, converter, responseProcessorChain);
    }

    @Override
    public boolean isHandler(EntryStateModel entryStateModel) {
        return Step.CREATE.equals(entryStateModel.getState().getAdapterContext().getNextStep())
                && TargetType.REFUND_DEBIT.equals(entryStateModel.getState().getTargetType());
    }

}
