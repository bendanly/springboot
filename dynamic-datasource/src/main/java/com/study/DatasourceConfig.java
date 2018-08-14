package com.study;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang on 2018/8/11.
 */
@Configuration
public class DatasourceConfig {
    @Bean(name = "defaultsource")
    @ConfigurationProperties(prefix = "spring.datasource") // application.properteis中对应属性的前缀
    public DataSource defaultsource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.datasource1") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "datasource2")
    @ConfigurationProperties(prefix = "spring.datasource.datasource2") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        DataSource dataSource = DataSourceBuilder.create().build();

        return dataSource;
    }

    @Bean
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(defaultsource());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put(DatabaseType.datasource1, dataSource1());
        dsMap.put(DatabaseType.datasource2, dataSource2());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
}
