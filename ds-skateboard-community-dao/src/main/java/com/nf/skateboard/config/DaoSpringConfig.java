package com.nf.skateboard.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;

// 代码配置
@Configuration
@PropertySource("classpath:mybatis/db.properties")  //外部文件
@MapperScan({"com.nf.skateboard.dao"})              //mybatis要用的dao
@EnableTransactionManagement(proxyTargetClass = true)    //启用事务
public class DaoSpringConfig {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driver;

    // 配置数据源
    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
//        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource());
        // 添加mapper文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mybatis/mappers/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        //添加配置,日志
        sqlSessionFactoryBean.setConfiguration(configuration());
        //添加插件,分页
        sqlSessionFactoryBean.setPlugins(pageInterceptor());
        return sqlSessionFactoryBean;
    }

    // 分页
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("supportMethodsArguments","true");
        properties.put("reasonable","true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    // 日志
    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(StdOutImpl.class);
        // 支持把_变为大写字母  user_id --> userId
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }


    // 事务
    @Bean
    public PlatformTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(druidDataSource());
        return dstm;
    }

}
