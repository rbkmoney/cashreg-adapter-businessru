package com.rbkmoney.adapter.businessru.service.businessru.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rbkmoney.adapter.businessru.service.businessru.constant.ErrorType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Тип источника ошибки
 */
@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    /**
     * Уникальный идентификатор ошибки
     */
    @JsonProperty(value = "error_id")
    private String errorId;

    /**
     * Код ошибки
     */
    private Integer code;

    /**
     * Текст ошибки (кодировка utf–8).
     */
    private String text;

    /**
     * Тип источника ошибки. Возможные значения:
     * - «system» – системная ошибка.
     * {@link ErrorType}
     */
    private String type;

}
