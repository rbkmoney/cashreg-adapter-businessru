package com.rbkmoney.adapter.businessru.handler;

import com.rbkmoney.adapter.businessru.service.businessru.constant.Vat;
import com.rbkmoney.adapter.businessru.utils.OptionsFields;
import com.rbkmoney.cashreg.proto.base.Cash;
import com.rbkmoney.cashreg.proto.base.ContactInfo;
import com.rbkmoney.cashreg.proto.base.CurrencyRef;
import com.rbkmoney.cashreg.proto.provider.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT

)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Ignore
public class BusinessRuServerHandlerTest {

    @Autowired
    private BusinessRuServerHandler handler;

    @Test
    public void debit() throws TException, InterruptedException {
        CashRegContext context = makeCashRegContext();

        CashRegResult result;
        // debit
        result = handler.debit(context);
        assertTrue(result.getIntent().getFinish().getStatus().getSuccess().isSetUuid());

        context.setRequestId(result.getIntent().getFinish().getStatus().getSuccess().getUuid());
//        Thread.sleep(60000);
        // status
        result = handler.getStatus(context);
        assertTrue(result.getIntent().getFinish().getStatus().isSetSuccess());
    }

    private CashRegContext makeCashRegContext() {
        CashRegContext context = new CashRegContext();
        AccountInfo accountInfo = new AccountInfo();
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setAddress("г. Москва, ул. Оранжевая, д.22 к.11");
        companyInfo.setInn("112233445573");
        companyInfo.setTaxMode(TaxMode.osn);
        accountInfo.setCompanyInfo(companyInfo);
        context.setAccountInfo(accountInfo);

        Map<String, String> options = new HashMap<>();
        options.put(OptionsFields.URL, "https://check-dev.business.ru/api-rbkmoney/v4/");
        options.put(OptionsFields.GROUP, "1");
        options.put(OptionsFields.LOGIN, "rbk@business.ru");
        options.put(OptionsFields.PASS, "rbk@business.ru");
        context.setOptions(options);

        context.setRequestId("request_id8");

        PaymentInfo paymentInfo = new PaymentInfo();

        Cash cash = new Cash();
        cash.setAmount(1000L);
        paymentInfo.setCash(cash);

        Payer payer = new Payer();
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail("a.cherkasov@rbkmoney.com");
        payer.setContactInfo(contactInfo);
        paymentInfo.setPayer(payer);


        Cart prepareCart = new Cart();
        List<ItemsLine> itemsLines = new ArrayList<>();


        ItemsLine itemLine = new ItemsLine();
        Cash cash1 = new Cash();
        cash1.setAmount(1000L);

        CurrencyRef currency = new CurrencyRef();
        currency.setSymbolicCode("RUB");

        cash.setCurrency(currency);

        itemLine.setPrice(cash);
        itemLine.setProduct("test");
        itemLine.setQuantity(1);
        itemLine.setTax(Vat.VAT_20.getCodeText());
        itemsLines.add(itemLine);


        prepareCart.setLines(itemsLines);


        paymentInfo.setCart(prepareCart);

        context.setPaymentInfo(paymentInfo);

        return context;
    }

}