/*
 * Copyright (c) 2021. 成都美源网络科技有限公司（https://www.meiy365.com/）
 */

package com.kixs.drool.enums;

import lombok.Getter;

/**
 * 规则类型枚举
 *
 * @author wangbing
 * @version v1.0.0
 * @since 2021/4/8 17:23
 */
@Getter
public enum RuleTypeEnum {
    /**
     * 规则类型枚举
     */
    COMBO("combo", "comboKS"),

    DISCOUNT("discount", "discountKS"),

    LIMITATION("limitation", "limitationKS"),

    OPTIONAL("optional", "optionalKS"),

    QUOTA("quota", "quotaKS"),

    TIME("time", "timeKS");

    private final String type;

    private final String ksName;

    RuleTypeEnum(String type, String ksName) {
        this.type = type;
        this.ksName = ksName;
    }
}
