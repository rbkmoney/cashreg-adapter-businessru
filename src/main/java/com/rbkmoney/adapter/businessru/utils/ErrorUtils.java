package com.rbkmoney.adapter.businessru.utils;


import com.rbkmoney.adapter.businessru.service.businessru.constant.Status;
import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;

public class ErrorUtils {

    public static boolean hasError(CommonResponse response) {
        return response.getError() != null && Status.FAIL.getValue().equals(response.getStatus());
    }

}
