package com.handong.finance.dto;

import com.handong.finance.domain.user.User;
import com.handong.finance.domain.userform.Category;
import com.handong.finance.domain.userform.UserForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDepositFormRequest {
    private String job;
    private long annualIncome;
    private long depositAmount;
    private int depositPeriod;
    private String interestPaymentType;
    private String savingPurpose;


    //User를 필드에 두지 말고(위험) 파라미터로만 받아서 엔티티로 넘기기만 하기
    public UserForm toEntity(User user){
        return new UserForm(
                user,
                Category.DEPOSIT,   //어차피 예금으로 고정이니 엔티티로 넘길 때 설정
                job,
                annualIncome,
                depositAmount,
                depositPeriod,
                interestPaymentType,
                savingPurpose);
    }
}
