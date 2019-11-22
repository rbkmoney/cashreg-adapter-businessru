package com.rbkmoney.adapter.businessru.service.businessru;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.businessru.service.businessru.constant.Operations;
import com.rbkmoney.adapter.businessru.service.businessru.model.request.CommonRequest;
import com.rbkmoney.adapter.businessru.service.businessru.model.request.RequestWrapper;
import com.rbkmoney.adapter.businessru.service.businessru.model.response.CommonResponse;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.utils.converter.ip.ConverterIp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class BusinessRuApi {

    private final static String PROVIDER_NAME = "BusinessRu";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ConverterIp converterIp;

    public ResponseEntity<CommonResponse> getToken(RequestWrapper<CommonRequest> requestWrapper) {
        String url = converterIp.replaceIpv4ToIpv6(requestWrapper.getUrl() + "getToken");
        log.info("{} getToken URL {}", PROVIDER_NAME, url);

        CommonRequest request = new CommonRequest();
        request.setLogin(requestWrapper.getRequest().getLogin());
        request.setPass(requestWrapper.getRequest().getPass());
        try {
            String body = objectMapper.writeValueAsString(request);
            HttpEntity httpEntity = new HttpEntity<>(body, getHttpHeaders(requestWrapper.getToken()));
            ResponseEntity<CommonResponse> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, CommonResponse.class);
            log.info("{}. {} with response: {}", PROVIDER_NAME, "getToken", response);
            return response;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Чек «Приход»
     */
    public ResponseEntity<CommonResponse> sell(RequestWrapper<CommonRequest> requestWrapper) {
        return send(requestWrapper, Operations.SELL);
    }

    /**
     * чек «Возврат прихода»
     */
    public ResponseEntity<CommonResponse> sellRefund(RequestWrapper<CommonRequest> requestWrapper) {
        return send(requestWrapper, Operations.SELL_REFUND);
    }

    /**
     * чек «Расход»
     */
    public ResponseEntity<CommonResponse> buy(RequestWrapper<CommonRequest> requestWrapper) {
        return send(requestWrapper, Operations.BUY);
    }
    
    /**
     * чек «Коррекция расхода»
     */
    public ResponseEntity<CommonResponse> buyCorrection(RequestWrapper<CommonRequest> requestWrapper) {
        return send(requestWrapper, Operations.BUY_CORRECTION);
    }

    /**
     * Получение информации о чеке по uuid-у
     */
    public ResponseEntity<CommonResponse> report(RequestWrapper<CommonRequest> requestWrapper) {
        String url = converterIp.replaceIpv4ToIpv6(requestWrapper.getUrl() + requestWrapper.getGroup() + "/report/" + requestWrapper.getRequest().getExternalId() + "?token=" + requestWrapper.getToken());
        log.info("{}. {} with url: {}", PROVIDER_NAME, "report", url);
        ResponseEntity<CommonResponse> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, CommonResponse.class);
        log.info("{}. {} with response: {}", PROVIDER_NAME, "report", response);
        return response;
    }

    private ResponseEntity<CommonResponse> send(RequestWrapper<CommonRequest> requestWrapper, Operations operation) {
        try {
            String body = objectMapper.writeValueAsString(requestWrapper.getRequest());
            log.info("{}. {} with request: {}", PROVIDER_NAME, operation, body);

            HttpEntity httpEntity = new HttpEntity<>(body, getHttpHeaders(requestWrapper.getToken()));
            String url = converterIp.replaceIpv4ToIpv6(requestWrapper.getUrl() + requestWrapper.getGroup() + "/" + operation.getOperation() + "?token=" + requestWrapper.getToken());
            log.info("{}. send URL {}", PROVIDER_NAME, url);

            ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, CommonResponse.class);
            log.info("{}. {} with response: {}", PROVIDER_NAME, operation.getOperation(), responseEntity);
            return responseEntity;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private HttpHeaders getHttpHeaders(String apiToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cache-control", "no-cache");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.set("Token", apiToken);
        return httpHeaders;
    }

}
