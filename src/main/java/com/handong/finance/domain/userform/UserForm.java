package com.handong.finance.domain.userform;

import com.handong.finance.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserForm {

    //예금, 대출 공통
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //양방향 연결관계 설정
    public void setUser(User user) {
        this.user = user;
        user.getUserForms().add(this);
    }

    private Category category;
    private String job;
    private long annualIncome;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    //예금만
    private long depositAmount;
    private int depositPeriod;
    private String interestPaymentType;
    private String savingPurpose;

    //대출만
    private long loanAmount;
    private int creditScore;
    private String loanPurpose;

    // 예금 생성자
    public UserForm(User user, Category category, String job,
                    long annualIncome, long depositAmount,
                    int depositPeriod, String interestPaymentType, String savingPurpose) {
        setUser(user);
        this.category = category;
        this.job = job;
        this.annualIncome = annualIncome;
        this.depositAmount = depositAmount;
        this.depositPeriod = depositPeriod;
        this.interestPaymentType = interestPaymentType;
        this.savingPurpose = savingPurpose;
    }

    // 대출 생성자
    public UserForm(User user, Category category, String job,
                    long annualIncome, long loanAmount,
                    int creditScore, String loanPurpose) {
        setUser(user);
        this.category = category;
        this.job = job;
        this.annualIncome = annualIncome;
        this.loanAmount = loanAmount;
        this.creditScore = creditScore;
        this.loanPurpose = loanPurpose;
    }

}
