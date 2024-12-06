package com.alex.tasktable.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
public class DatabaseConfig {
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
    public LocalSessionFactoryBean sessionFactory(BasicDataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(YamlConfig.hibernateProperties.getEntityPackagePath());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", YamlConfig.hibernateProperties.getDialect());
        properties.put("hibernate.show_sql", YamlConfig.hibernateProperties.isShowSql());
        return properties;
    }
}
