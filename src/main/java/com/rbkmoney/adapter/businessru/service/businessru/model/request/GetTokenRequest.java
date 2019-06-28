package com.rbkmoney.adapter.businessru.service.businessru.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetTokenRequest {

    /**
     * логин пользователя для отправки данных.
     * Логин для отправки данных можно получить из файла настроек для CMS в личном кабинете пользователя
     */
    @JsonProperty("login")
    private String login;

    /**
     * Пароль пользователя для отправки данных
     * Пароль для отправки данных можно получить из файла настроек для CMS в личном кабинете пользователя.
     */
    @JsonProperty("pass")
    private String pass;

}
