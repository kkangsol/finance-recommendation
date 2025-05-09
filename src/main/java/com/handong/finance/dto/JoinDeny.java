package com.handong.finance.dto;

public enum JoinDeny {
    ALLOWED(1, "제한 없음"),
    ONLY_LOW_INCOME(2, "서민 전용"),
    NOT_ALLOWED(3, "가입 불가");

    private final int code;           // JSON에서 오는 숫자 값
    private final String description; // 화면에 보여줄 한글 설명

    JoinDeny(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static JoinDeny fromCode(int code) {
        for (JoinDeny value : JoinDeny.values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid joinDeny code: " + code);
    }
}
