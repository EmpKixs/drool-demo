package com.kixs.drool.service;

import com.kixs.drool.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO 功能描述
 *
 * @author wangbing
 * @version v1.0.0
 * @since 2021/4/7 14:14
 */
@Slf4j
@Service
public class DroolsService {

    /*public static void main(String[] args) {
        System.out.println(DroolsService.fireRule(0, "kixs"));
        // System.out.println(DroolsService.fireRule(1, "kixs"));
    }*/

    @Autowired
    private KieContainer kieContainer;

    /*@Resource
    private KieSession kieSession;*/


    public String fireRule(Integer status, String msg) {
        // load up the knowledge base
        /*KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();*/
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("log", log);
        // go !
        Message message = new Message();
        message.setMessage(msg);
        message.setStatus(status);
        //插入
        kieSession.insert(message);
        //kieSession.startProcess()
        //执行规则
        kieSession.fireAllRules();
        kieSession.dispose();
        return message.getMessage();
    }
}
