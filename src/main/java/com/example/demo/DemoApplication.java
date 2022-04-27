package com.example.demo;

import cn.dev33.satoken.SaManager;
import com.alipay.api.java_websocket.server.WebSocketServer;
import com.example.demo.controller.ChatController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {
    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        ChatController.setConfigurableApplicationContext(applicationContext);
//        System.out.println("启动成功：sa-token配置如下："+ SaManager.getConfig());
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
    }

}
