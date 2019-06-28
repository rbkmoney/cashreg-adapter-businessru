package com.rbkmoney.adapter.businessru.service.businessru.constant;

import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;
import com.rbkmoney.adapter.businessru.service.businessru.model.response.ReportResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Статус. Возможные значения:
 * - «done» – готово;
 * - «fail» – ошибка;
 * - «wait» – ожидание.
 */
@Getter
@RequiredArgsConstructor
public enum Status {

    /**
     * готово
     */
    DONE("done"),

    /**
     * ошибка
     */
    FAIL("fail"),

    /**
     * ожидание
     */
    WAIT("wait");

    private final String value;

    public static boolean isWait(ReportResponse reportResponse) {
        return Status.WAIT.getValue().equalsIgnoreCase(reportResponse.getStatus());
    }

    public static boolean isDone(ReportResponse reportResponse) {
        return Status.DONE.getValue().equalsIgnoreCase(reportResponse.getStatus());
    }

    public static boolean isFail(ReportResponse reportResponse) {
        return Status.FAIL.getValue().equalsIgnoreCase(reportResponse.getStatus());
    }

    public static boolean isWait(CommonResponse reportResponse) {
        return Status.WAIT.getValue().equalsIgnoreCase(reportResponse.getStatus());
    }

    public static boolean isDone(CommonResponse reportResponse) {
        return Status.DONE.getValue().equalsIgnoreCase(reportResponse.getStatus());
    }

    public static boolean isFail(CommonResponse reportResponse) {
        return Status.FAIL.getValue().equalsIgnoreCase(reportResponse.getStatus());
    }

}
