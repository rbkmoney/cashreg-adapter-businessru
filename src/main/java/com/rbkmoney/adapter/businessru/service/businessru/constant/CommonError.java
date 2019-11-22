package com.rbkmoney.adapter.businessru.service.businessru.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonError {

    CHECK_STATUS_NOT_FOUND_TRY_LATER("34", "Состояние чека не найдено. Попробуйте позднее");

    private final String code;
    private final String description;

}
