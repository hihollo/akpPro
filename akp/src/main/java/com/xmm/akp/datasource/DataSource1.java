/*
package com.xmm.akp.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration//注册到Spring容器中
@MapperScan(basePackages="com.qianfeng.db1.mapper",sqlSessionFactoryRef="db1SqlSessionFactory")
public class DataSource1 {
	
	//private DataSource1(){}

	*/
/**
     * 配置db1数据库
     * @return
     *//*

    @Bean(name="db1Datasource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.db1") //在全局配置文件中找前缀为spring.datasource.db1，生成数据源对象
    public DataSource testDatasource() {
        return DataSourceBuilder.create().build();
    }
    */
/**
     * 创建SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     *//*

    @Bean(name="db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db1Datasource")DataSource dataSource) 
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
    */
/**
     * 配置事务管理
     * @param dataSource
     * @return
     *//*

    @Bean(name="db1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(
            @Qualifier("db1Datasource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
 
    @Bean(name="db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db1SqlSessionFactory")
    SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}


*/
