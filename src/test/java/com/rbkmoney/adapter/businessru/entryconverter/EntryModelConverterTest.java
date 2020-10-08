package com.rbkmoney.adapter.businessru.entryconverter;

import com.rbkmoney.adapter.businessru.IntegrationTest;
import com.rbkmoney.adapter.businessru.converter.entry.CtxToEntryModelConverter;
import com.rbkmoney.adapter.cashreg.spring.boot.starter.model.EntryStateModel;
import com.rbkmoney.damsel.cashreg.adapter.CashregContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class EntryModelConverterTest extends IntegrationTest {

    @Autowired
    CtxToEntryModelConverter converter;

    @Test
    public void prepareVatSumTest() {
        CashregContext context = makeCashRegContext();
        EntryStateModel entryStateModel = converter.convert(context);
        Assert.assertEquals(BigDecimal.valueOf(0.15), entryStateModel.getVats().get(0).getSum());
    }

}
