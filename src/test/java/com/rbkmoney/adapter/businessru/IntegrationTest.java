package com.rbkmoney.adapter.businessru;

import com.rbkmoney.adapter.cashreg.spring.boot.starter.constant.OptionalField;
import com.rbkmoney.damsel.cashreg.adapter.CashregAdapterSrv;
import com.rbkmoney.damsel.cashreg.adapter.CashregContext;
import com.rbkmoney.damsel.cashreg.adapter.Session;
import com.rbkmoney.damsel.cashreg.adapter.SourceCreation;
import com.rbkmoney.damsel.cashreg.domain.*;
import com.rbkmoney.damsel.cashreg.receipt.Cart;
import com.rbkmoney.damsel.cashreg.receipt.ItemsLine;
import com.rbkmoney.damsel.cashreg.receipt.type.Debit;
import com.rbkmoney.damsel.cashreg.receipt.type.Type;
import com.rbkmoney.damsel.domain.Cash;
import com.rbkmoney.damsel.domain.CurrencyRef;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
@ContextConfiguration(classes = CashregBusinessRuApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class IntegrationTest {

    @Autowired
    protected CashregAdapterSrv.Iface handler;

    public CashregContext makeCashRegContext() {
        CashregContext context = new CashregContext()
                .setCashregId(TestData.CASHREG_ID)
                .setSourceCreation(createSourceCreation())
                .setAccountInfo(createAccountInfo())
                .setOptions(createAdapterOptions())
                .setSession(new Session().setType(Type.debit(new Debit())));
        return context;
    }

    private Map<String, String> createAdapterOptions() {
        Map<String, String> options = new HashMap<>();
        options.put(OptionalField.LOGIN.getField(), "login");
        options.put(OptionalField.PASS.getField(), "pass");
        options.put(OptionalField.PAYMENT_METHOD.getField(), "PAYMENT_METHOD");
        options.put(OptionalField.PAYMENT_OBJECT.getField(), "PAYMENT_OBJECT");
        options.put(OptionalField.GROUP.getField(), "Main");
        options.put(OptionalField.COMPANY_NAME.getField(), "Bull");
        options.put(OptionalField.COMPANY_ADDRESS.getField(), "street Tester");
        return options;
    }

    private SourceCreation createSourceCreation() {
        SourceCreation sourceCreation = new SourceCreation();
        sourceCreation.setPayment(createPaymentInfo());
        return sourceCreation;
    }

    private AccountInfo createAccountInfo() {
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setRussianLegalEntity(new RussianLegalEntity()
                .setActualAddress("ActualAddress")
                .setInn("INN")
                .setPostAddress("PostAddress")
                .setRegisteredName("RegisteredName")
                .setRepresentativeDocument("RepresentativeDocument")
                .setRepresentativeFullName("RepresentativeFullName")
                .setRepresentativePosition("RepresentativePosition")
                .setRegisteredNumber("RegisteredNumber")
                .setRussianBankAccount(new RussianBankAccount()
                        .setAccount("Account")
                        .setBankName("BankName")
                        .setBankPostAccount("BankPostAccount")
                        .setBankBik("BankBik"))
                .setEmail(TestData.TEST_EMAIL)
                .setTaxMode(TaxMode.osn));

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setLegalEntity(legalEntity);
        return accountInfo;
    }

    public static PaymentInfo createPaymentInfo() {
        PaymentInfo paymentInfo = new PaymentInfo();

        Cash cash = new Cash();
        cash.setAmount(100L);
        cash.setCurrency(new CurrencyRef().setSymbolicCode("RUR"));
        paymentInfo.setCash(cash);

        ItemsLine itemsLine = new ItemsLine();
        itemsLine.setPrice(cash);
        itemsLine.setTax("18%");
        itemsLine.setQuantity(1);
        itemsLine.setProduct("test");
        List<ItemsLine> lines = new ArrayList<>();
        lines.add(itemsLine);
        Cart cart = new Cart();
        cart.setLines(lines);
        paymentInfo.setCart(cart);
        paymentInfo.setEmail(TestData.TEST_EMAIL);
        return paymentInfo;
    }

}
