package com.handong.finance.dto;


import com.handong.finance.domain.user.User;
import com.handong.finance.domain.userform.Category;
import com.handong.finance.domain.userform.UserForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoanFormRequest {
    private String job;
    private long annualIncome;
    private long loanAmount;
    private int creditScore;
    private String loanPurpose;

    public UserForm toEntity(User user) {
        return new UserForm(
                user,
                Category.LOAN,
                job,
                annualIncome,
                loanAmount,
                creditScore,
                loanPurpose
        );
    }
}
