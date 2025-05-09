package com.handong.finance.dto;


import com.handong.finance.domain.user.User;
import com.handong.finance.domain.userform.Category;
import com.handong.finance.domain.userform.UserForm;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserFormRequest {

    private Category category;
    private String job;
    private BigDecimal annualIncome;


    //예금만
    private BigDecimal depositAmount;
    private int depositPeriod;
    private String interestPaymentType;
    private String savingPurpose;

    //대출만
    private BigDecimal loanAmount;
    private int creditScore;
    private String loanPurpose;

    public UserForm toEntity(User user){
        if(this.category == Category.DEPOSIT){
            return new UserForm(
                    user,
                    this.category,
                    this.job,
                    this.annualIncome,
                    this.creditScore,
                    this.depositAmount,
                    this.depositPeriod,
                    this.interestPaymentType,
                    this.savingPurpose
            );
        }else if(this.category == Category.LOAN){
            return new UserForm(
                    user,
                    this.category,
                    this.job,
                    this.annualIncome,
                    this.loanAmount,
                    this.creditScore,
                    this.loanPurpose
            );
        }else{
            throw new IllegalArgumentException("잘못된 카테고리입니다.");
        }
    }
}
