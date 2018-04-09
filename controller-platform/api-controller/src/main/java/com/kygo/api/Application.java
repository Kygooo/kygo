package com.kygo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {
		"com.kygo.api",
		"com.kygo.service",
		"com.kygo.mybatis", 
		"com.kygo.redis.manger",
        "com.kygo.mongo"})
public class Application {

    protected final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        logger.info("Application is success!");
        System.err.println("Application started.");
        System.err.println("http://localhost:8080/swagger-ui.html");
    }

}
