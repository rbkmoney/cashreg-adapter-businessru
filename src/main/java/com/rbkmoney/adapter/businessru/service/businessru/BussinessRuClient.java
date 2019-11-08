package com.rbkmoney.adapter.businessru.service.businessru;

import com.rbkmoney.adapter.businessru.service.businessru.model.request.CommonRequest;
import com.rbkmoney.adapter.businessru.service.businessru.model.request.RequestWrapper;
import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class BussinessRuClient implements AdapterCashReg {

    private final BussinessRuApi api;

    @Override
    public CommonResponse debit(RequestWrapper<CommonRequest> requestWrapper) {
        requestWrapper.setToken(extractToken(requestWrapper, api));
        ResponseEntity<CommonResponse> responseEntity = api.sell(requestWrapper);
        return responseEntity.getBody();
    }

    @Override
    public CommonResponse credit(RequestWrapper<CommonRequest> requestWrapper) {
        requestWrapper.setToken(extractToken(requestWrapper, api));
        ResponseEntity<CommonResponse> responseEntity = api.buy(requestWrapper);
        return responseEntity.getBody();
    }

    @Override
    public CommonResponse refundDebit(RequestWrapper<CommonRequest> requestWrapper) {
        requestWrapper.setToken(extractToken(requestWrapper, api));
        ResponseEntity<CommonResponse> responseEntity = api.sellRefund(requestWrapper);
        return responseEntity.getBody();
    }

    @Override
    public CommonResponse refundCredit(RequestWrapper<CommonRequest> requestWrapper) {
        requestWrapper.setToken(extractToken(requestWrapper, api));
        ResponseEntity<CommonResponse> responseEntity = api.buyCorrection(requestWrapper);
        return responseEntity.getBody();
    }

    @Override
    public CommonResponse status(RequestWrapper<CommonRequest> requestWrapper) {
        requestWrapper.setToken(extractToken(requestWrapper, api));
        ResponseEntity<CommonResponse> responseEntity = api.report(requestWrapper);
        return responseEntity.getBody();
    }

    private String extractToken(RequestWrapper<CommonRequest> requestWrapper, BussinessRuApi api) {
        return api.getToken(requestWrapper).getBody().getToken();
    }
}
