package com.rbkmoney.adapter.businessru.processor;

import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;
import com.rbkmoney.adapter.businessru.utils.ErrorUtils;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.AdapterState;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.EntryStateModel;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.ExitStateModel;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.processor.Processor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

import static com.rbkmoney.adapter.cashreg.spring.boot.starter.constant.Error.SLEEP_TIMEOUT;
import static com.rbkmoney.adapter.cashreg.spring.boot.starter.constant.Error.UNKNOWN;

@Slf4j
@RequiredArgsConstructor
public class ErrorProcessor implements Processor<ExitStateModel, EntryStateModel, CommonResponse> {

    @Override
    public ExitStateModel process(CommonResponse response, EntryStateModel entryStateModel) {
        Instant currentTime = Instant.now();
        AdapterState adapterState = new AdapterState();
        if (entryStateModel.getState().getAdapterContext() != null) {
            adapterState = entryStateModel.getState().getAdapterContext();
        }

        ExitStateModel exitStateModel = new ExitStateModel();
        if (ErrorUtils.hasError(response)) {
            exitStateModel.setEntryStateModel(entryStateModel);
        } else if (adapterState.getMaxDateTimePolling().getEpochSecond() < currentTime.getEpochSecond()) {
            log.error("Sleep Timeout for response: {}!", response);
            exitStateModel.setErrorCode(SLEEP_TIMEOUT.getCode());
            exitStateModel.setErrorMessage(SLEEP_TIMEOUT.getMessage());
        } else {
            log.error("Unknown result code for response: {}!", response);
            exitStateModel.setErrorCode(UNKNOWN.getCode());
            exitStateModel.setErrorMessage(UNKNOWN.getMessage());
        }
        return exitStateModel;
    }

}
