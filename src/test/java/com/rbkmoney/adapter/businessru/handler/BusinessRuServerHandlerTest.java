package com.rbkmoney.adapter.businessru.handler;

import com.rbkmoney.adapter.businessru.IntegrationTest;
import com.rbkmoney.adapter.businessru.MockUtils;
import com.rbkmoney.adapter.businessru.service.businessru.BusinessRuClient;
import com.rbkmoney.damsel.cashreg.provider.CashRegContext;
import com.rbkmoney.damsel.cashreg.provider.CashRegResult;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static junit.framework.TestCase.assertTrue;


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
        CashRegContext cashRegContext = makeCashRegContext();
        CashRegResult result = handler.register(cashRegContext);
        assertTrue(result.getIntent().isSetSleep());

        cashRegContext.getSession().setState(result.getState());

        result = handler.register(cashRegContext);
        assertTrue(result.getIntent().isSetFinish());
    }

}