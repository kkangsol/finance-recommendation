package com.handong.finance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DepositProductResponse {
    private Result result;

    @Data
    public static class Result{
        @JsonProperty("baseList")
        private List<BaseList> baseList;

        @Data
        public static class BaseList{

            @JsonProperty("kor_co_nm")
            private String bankName;    //은행명

            @JsonProperty("fin_prdt_nm")
            private String productName; //상품명

            @JsonProperty("join_way")
            private String joinWay; //가입방법

            @JsonProperty("mtrt_int")
            private String interestRateDue; //만기시 금리

            @JsonProperty("spcl_cnd")
            private String specialCondition;    //우대조건

            @JsonProperty("join_deny")
            private int joinDeny;  //가입제한(1:x / 2:서민 / 3:제한)

            @JsonProperty("join_member")
            private String joinMember;

            @JsonProperty("etc_note")
            private String etcNote; //기타 유의사항

            @JsonProperty("max_limit")
            private BigDecimal maxLimit;  //최고 한도

            @JsonProperty("intr_rate_type")
            private String rateType;    //저축금리유형명(단,복리)

            @JsonProperty("save_trm")
            private int savePeriod; //저축기간

            @JsonProperty("intr_rate")
            private BigDecimal intRate; //저축금리

        }

    }

    public List<DepositProduct> toDepositProductList(){
        List<DepositProduct> depositProducts = new ArrayList<>();
        for(Result.BaseList p : result.baseList){
            DepositProduct product = new DepositProduct(
                    p.getBankName(),
                    p.getProductName(),
                    p.getJoinWay(),
                    p.getInterestRateDue(),
                    p.getSpecialCondition(),
                    JoinDeny.fromCode(p.getJoinDeny()),
                    p.getJoinMember(),
                    p.getEtcNote(),
                    p.getMaxLimit(),
                    p.getRateType(),
                    p.getSavePeriod(),
                    p.getIntRate()
            );
            depositProducts.add(product);
        }
        return depositProducts;
    }
}
