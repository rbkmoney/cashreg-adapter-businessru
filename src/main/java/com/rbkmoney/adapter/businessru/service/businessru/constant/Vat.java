package com.rbkmoney.adapter.businessru.service.businessru.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Vat {

    VAT_0("0%", Tax.VAT0.getCode(), "0% НДС", 0),
    VAT_10("10%", Tax.VAT10.getCode(), "10% НДС", 10),
    VAT_18("18%", Tax.VAT18.getCode(), "18% НДС", 18),
    VAT_20("20%", Tax.VAT20.getCode(), "20% НДС", 20),
    NO_VAT("null", Tax.NONE.getCode(), "Без НДС", 0),
    VAT_10_110("10/110", Tax.VAT110.getCode(), "10/110 НДС", 10),
    VAT_18_118("18/118", Tax.VAT118.getCode(), "18/118 НДС", 18),
    VAT_20_120("20/120", Tax.VAT120.getCode(), "20/120 НДС", 20);

    private final String codeText;
    private final String code;
    private final String message;
    private final int rate;

    public static Vat codeTextOf(String codeText) {
        for (Vat vat : values()) {
            if (vat.codeText.equals(codeText)) {
                return vat;
            }
        }
        throw new IllegalArgumentException("No matching for [" + codeText + "]");
    }

}
