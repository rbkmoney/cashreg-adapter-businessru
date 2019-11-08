package com.rbkmoney.adapter.businessru.service.businessru;

import com.rbkmoney.adapter.businessru.service.businessru.model.request.CommonRequest;
import com.rbkmoney.adapter.businessru.service.businessru.model.request.RequestWrapper;
import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;

public interface AdapterCashReg {

    CommonResponse debit(RequestWrapper<CommonRequest> request);

    CommonResponse credit(RequestWrapper<CommonRequest> request);

    CommonResponse refundDebit(RequestWrapper<CommonRequest> request);

    CommonResponse refundCredit(RequestWrapper<CommonRequest> request);

    CommonResponse status(RequestWrapper<CommonRequest> request);

}
