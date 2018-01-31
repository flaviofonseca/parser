//package com.ef.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@PropertySource(value = {"classpath:application.properties"})
//public class DataSourceConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource  dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
//        return dataSource;
//    }
//}