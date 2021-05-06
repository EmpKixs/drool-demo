/*
 * Copyright (c) 2021. 成都美源网络科技有限公司（https://www.meiy365.com/）
 */

package com.kixs.drool.param;

import com.kixs.drool.enums.RuleTypeEnum;

/**
 * 基础规则参数类，所有规则入参需要继承
 *
 * @author wangbing
 * @version v1.0.0
 * @since 2021/4/8 16:48
 */
public abstract class BaseRuleParam<T> {

    /**
     * 规则处理返回结果
     */
    private T result;

    /**
     * 获取抽象类规则类型枚举
     *
     * @return 规则类型枚举
     */
    public abstract RuleTypeEnum getRuleType();

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
