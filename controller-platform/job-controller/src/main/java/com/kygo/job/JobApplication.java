package com.kygo.job;


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
		"com.kygo.job",
		"com.kygo.service",
		"com.kygo.mybatis",
		"com.kygo.redis.manger",
        "com.kygo.mongo"})
public class JobApplication {

    protected final static Logger logger = LoggerFactory.getLogger(JobApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JobApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        logger.info("jobApplication is success!");
        System.err.println("jobApplication started.");
    }

}