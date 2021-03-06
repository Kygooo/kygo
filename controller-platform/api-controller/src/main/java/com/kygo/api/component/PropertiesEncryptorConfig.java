package com.kygo.api.component;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PropertiesEncryptorConfig {
    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        return new PropertiesEncryptor();

    }
}
