package com.example.demo.config;

import com.example.demo.service.Scala;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:my.properties")
public class AppConfig {

    @Value("${some}")
    public String configMonada;

    @Bean
    @Scope(value="prototype")
    public Scala scala() {
        return new Scala(configMonada);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
