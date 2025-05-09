package com.handong.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DepositProduct {
    private String bankName;    //은행명
    private String productName; //상품명
    private String joinWay; //가입방법
    private String interestRateDue; //만기시 금리
    private String specialCondition;    //우대조건
    private JoinDeny joinDeny;  //가입제한(1:x / 2:서민 / 3:제한)
    private String joinMember;
    private String etcNote; //기타 유의사항
    private BigDecimal maxLimit;  //최고 한도
    private String rateType;    //저축금리유형명(단,복리)
    private int savePeriod; //저축기간
    private BigDecimal intRate; //저축금리

}
