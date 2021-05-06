/*
 * Copyright (c) 2021. 成都美源网络科技有限公司（https://www.meiy365.com/）
 */

package com.kixs.drool;

import com.kixs.drool.message.Message;
import com.kixs.drool.param.BaseRuleParam;
import com.kixs.drool.param.TimeRuleParam;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 规则解释工具类
 *
 * @author wangbing
 * @version v1.0.0
 * @since 2021/4/8 16:50
 */
@Slf4j
public class DrUtils {

    public static void main(String[] args) {
        TimeRuleParam<String> beginParam1 = new TimeRuleParam<>();
        beginParam1.setBeginTimeWay(1);
        DrUtils.fireRule(beginParam1);
        log.info("beginTimeTest:{}", JsonUtils.objectToJson(beginParam1));

        TimeRuleParam<String> beginParam2 = new TimeRuleParam<>();
        beginParam2.setBeginTimeWay(2);
        beginParam2.setBeginTimeWayParam("2021-04-14 00:00:00");
        DrUtils.fireRule(beginParam2);
        log.info("beginTimeTest1:{}", JsonUtils.objectToJson(beginParam2));


        TimeRuleParam<String> endParam1 = new TimeRuleParam<>();
        endParam1.setBeginTime("2021-04-14 00:00:00");
        endParam1.setEndTimeWay(2);
        endParam1.setEndTimeWayParam("2021-04-15 00:00:00");
        DrUtils.fireRule(endParam1);
        log.info("endTimeTest1:{}", JsonUtils.objectToJson(endParam1));

        fireRule(1, "aaa");
        fireRule(2, "aaa");

    }

    /**
     * 规则解释执行
     *
     * @param param 规则入参
     * @return 规则解释执行结果
     */
    public static <T> T fireRule(TimeRuleParam<T> param) {
        // load up the knowledge base
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("timeKS");
        //插入
        kieSession.insert(param);
        //执行规则
        int count = kieSession.fireAllRules();
        log.debug("本次匹配执行了{}个规则", count);
        kieSession.dispose();
        return param.getResult();
    }

    public static String fireRule(Integer status, String msg) {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("greetingKS");
        // go !
        Message message = new Message();
        message.setMessage(msg);
        message.setStatus(status);
        //插入
        kieSession.insert(message);
        //执行规则
        int count = kieSession.fireAllRules();
        log.debug("本次匹配执行了{}个规则", count);
        kieSession.dispose();
        return message.getMessage();
    }
}
