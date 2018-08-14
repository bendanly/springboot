package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
首先要将spring boot自带的DataSourceAutoConfiguration禁掉，因为它会读取application.properties文件的spring.datasource.
*属性并自动配置单数据源。在@SpringBootApplication注解中添加exclude属性即可
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.study")
@EnableAspectJAutoProxy(exposeProxy = true)//允许暴露代理对象
@RestController
public class DynamicDatasourceApplication {
    @Autowired
    private TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }

    @RequestMapping(value = "/getvalue")
    public String getValue() {
        return testService.getValue3();
    }

    @RequestMapping(value = "/getvalue2")
    public String getValue2(){
        String t1 = testService.getValue();
        String t2 = testService.getValue2();
        return "getvalue2->"+t1+"<<<<>>>>"+t2;
    }

    @RequestMapping(value = "/getvalue4")
    public  String getValue3(){
        String t = testService.getValue5();
        return "getvalue4->"+t;
    }

    @RequestMapping(value = "/getvalue5")
    public String getValue4(){
        String t = testService.getValue6();
        return "getvalue5->"+t;
    }
}
