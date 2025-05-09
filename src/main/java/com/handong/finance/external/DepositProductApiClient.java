package com.handong.finance.external;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.handong.finance.dto.DepositProduct;
import com.handong.finance.dto.DepositProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DepositProductApiClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String apiKey = "77af6e68e10b76c0830f341f8aee176b";
    private final List<String> finGrpCodes = List.of("020000", "030200", "030300", "050000", "060000");

    public List<DepositProduct> fetchDepositProducts(){
        List<DepositProduct> result = new ArrayList<>();
        for( String code : finGrpCodes){
            String url = "http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json?"
                    + "auth=" + apiKey
                    + "&topFinGrpNo=" + code
                    + "&pageNo=1";
            try {
                String json = restTemplate.getForObject(url, String.class);
                DepositProductResponse response = objectMapper.readValue(json, DepositProductResponse.class);
                result.addAll(response.toDepositProductList());
            }catch (Exception e){
                System.out.println("API호출실패 : (code = " + code + ") : " + e.getMessage());
            }
        }
        return result;
    }
}
