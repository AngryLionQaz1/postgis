package com.snow.postgis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * postgresql data source config
 * @author fiend 2018/12/5
 */
@Configuration
public class DataSourceConfig {
    // @Primary
    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

}
