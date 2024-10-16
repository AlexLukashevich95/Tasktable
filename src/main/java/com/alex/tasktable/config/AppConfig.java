package com.alex.tasktable.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurationSupport {

    @Bean
    public BasicDataSource basicDataSourceDataSource() throws IOException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(YamlConfig.dataSourceProperties.getDriverClassName());
        basicDataSource.setUrl(YamlConfig.dataSourceProperties.getUrl());
        basicDataSource.setUsername(YamlConfig.dataSourceProperties.getUserName());
        basicDataSource.setPassword(YamlConfig.dataSourceProperties.getUserPassword());
        return basicDataSource;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver iRVR = new InternalResourceViewResolver();
        iRVR.setPrefix("/");
        iRVR.setSuffix(".jsp");
        return iRVR;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}