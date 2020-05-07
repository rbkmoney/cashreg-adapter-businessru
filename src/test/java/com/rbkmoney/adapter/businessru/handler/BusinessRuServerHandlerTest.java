package com.rbkmoney.adapter.businessru.handler;

import com.rbkmoney.adapter.businessru.IntegrationTest;
import com.rbkmoney.adapter.businessru.MockUtils;
import com.rbkmoney.adapter.businessru.service.businessru.BusinessRuClient;
import com.rbkmoney.damsel.cashreg.adapter.CashregContext;
import com.rbkmoney.damsel.cashreg.adapter.CashregResult;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertTrue;

public class BusinessRuServerHandlerTest extends IntegrationTest {

    @MockBean
    public BusinessRuClient client;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MockUtils.mockClient(client);
    }

    @Test
    public void testBusinessRuServerHandler() throws TException {
        CashregContext context = makeCashRegContext();
        CashregResult result = handler.register(context);
        assertTrue(result.getIntent().isSetSleep());

        context.getSession().setState(result.getState());

        result = handler.register(context);
        assertTrue(result.getIntent().isSetFinish());
    }

}