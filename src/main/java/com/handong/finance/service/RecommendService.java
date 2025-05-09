package com.handong.finance.service;

import com.handong.finance.domain.user.User;
import com.handong.finance.domain.userform.UserForm;
import com.handong.finance.dto.DepositProduct;
import com.handong.finance.dto.JoinDeny;
import com.handong.finance.external.DepositProductApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final DepositProductApiClient api;

    public List<DepositProduct> recommendDepositProducts(UserForm userForm) {
        List<DepositProduct> all = api.fetchDepositProducts();
        return all.stream()
                .filter(p -> isEligible(userForm, p))
                .filter(p -> p.getSavePeriod() >= userForm.getDepositPeriod())
                .filter(p -> matchRateType(userForm, p))
                .sorted(Comparator.comparing(DepositProduct::getIntRate).reversed())
                .limit(5)
                .toList();
    }

    private boolean isEligible(UserForm userForm, DepositProduct product){
        return switch(product.getJoinDeny()){
            case ALLOWED -> true;
            case ONLY_LOW_INCOME -> isLowIncome(userForm);
            case NOT_ALLOWED -> true;
        };
    }

    private boolean isLowIncome(UserForm userForm) {
        return !userForm.getUser().getIsBusinessOwner()
                && (userForm.getAnnualIncome().compareTo(BigDecimal.valueOf(36000000)) <= 0);
    }

    private boolean matchRateType(UserForm userForm, DepositProduct product){
        String userRateType = userForm.getInterestPaymentType(); // 예: "단리,복리"
        String productRateType = product.getRateType();          // 예: "단리"

        if (userRateType == null || userRateType.isBlank()) return true;

        List<String> preferredTypes = Arrays.stream(userRateType.split(","))
                .map(String::trim)
                .toList();

        return preferredTypes.contains(productRateType);
    }
}
