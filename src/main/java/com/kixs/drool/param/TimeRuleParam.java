/*
 * Copyright (c) 2021. 成都美源网络科技有限公司（https://www.meiy365.com/）
 */

package com.kixs.drool.param;

import com.kixs.drool.enums.RuleTypeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * 时间类规则参数
 *
 * @author wangbing
 * @version v1.0.0
 * @since 2021/4/13 16:06
 */
@Data
@ToString(callSuper = true)
public class TimeRuleParam<T> extends BaseRuleParam<T> {

    @Override
    public RuleTypeEnum getRuleType() {
        return RuleTypeEnum.TIME;
    }

    private String beginTime;

    private Integer beginTimeWay;

    private String beginTimeWayParam;

    private String endTime;

    private Integer endTimeWay;

    private String endTimeWayParam;

    private String activeTime;

    private Integer activeTimeWay;

    private String expireTime;

    private Integer expireTimeWay;

    private String expireTimeWayParam;
}
