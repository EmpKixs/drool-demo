package com.kixs.drool;

import com.kixs.drool.service.DroolsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class DroolDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroolDemoApplication.class, args);
    }

    @Resource
    private DroolsService droolsService;

    @GetMapping("/drool")
    public String drool(@RequestParam("msg") String msg, @RequestParam(name = "status", defaultValue = "1") Integer status) {
        return droolsService.fireRule(status, msg);
    }

}
