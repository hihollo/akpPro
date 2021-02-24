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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages="com.qianfeng.db5.mapper",sqlSessionFactoryRef="db5SqlSessionFactory")//指明每个数据源对应的mapper
public class DataSource2 {

	*/
/**
     * 配置db5数据库
     * @return
     *//*

    @Bean(name="db5Datasource")
    @ConfigurationProperties(prefix="spring.datasource.db5")//在application.properties文件中
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

    @Bean(name="db5SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db5Datasource")DataSource dataSource) 
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果还有分页等其他事务
//      bean.setMapperLocations(new PathMatchingResourcePatternResolver().
//              getResources("classpath:mybatis/test1/*.xml"));
        return bean.getObject();
    }
    */
/**
     * 配置事务管理
     * @param dataSource
     * @return
     *//*

    @Bean(name="db5TransactionManager")
    public DataSourceTransactionManager testTransactionManager(
            @Qualifier("db5Datasource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
 
    @Bean(name="db5SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db5SqlSessionFactory")
    SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}*/
