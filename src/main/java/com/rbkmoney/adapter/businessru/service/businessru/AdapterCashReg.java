package com.rbkmoney.adapter.businessru.service.businessru;

import com.rbkmoney.cashreg.proto.provider.CashRegContext;
import org.springframework.http.ResponseEntity;

public interface AdapterCashReg {

    ResponseEntity<?> debit(CashRegContext context);

    ResponseEntity<?> credit(CashRegContext context);

    ResponseEntity<?> refundDebit(CashRegContext context);

    ResponseEntity<?> refundCredit(CashRegContext context);

    ResponseEntity<?> getStatus(CashRegContext context);

}
