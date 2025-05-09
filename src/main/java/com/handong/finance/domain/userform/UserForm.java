package com.handong.finance.domain.userform;

import com.handong.finance.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_form")
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
    private BigDecimal annualIncome;
    private int creditScore;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    //예금만
    private BigDecimal depositAmount;
    private int depositPeriod;
    private String interestPaymentType;
    private String savingPurpose;

    //대출만
    private BigDecimal loanAmount;
    private String loanPurpose;

    // 예금 생성자
    public UserForm(User user, Category category, String job,
                    BigDecimal annualIncome,int creditScore, BigDecimal depositAmount,
                    int depositPeriod, String interestPaymentType, String savingPurpose) {
        setUser(user);
        this.category = category;
        this.job = job;
        this.annualIncome = annualIncome;
        this.creditScore = creditScore;
        this.depositAmount = depositAmount;
        this.depositPeriod = depositPeriod;
        this.interestPaymentType = interestPaymentType;
        this.savingPurpose = savingPurpose;
    }

    // 대출 생성자
    public UserForm(User user, Category category, String job,
                    BigDecimal annualIncome, BigDecimal loanAmount,
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
