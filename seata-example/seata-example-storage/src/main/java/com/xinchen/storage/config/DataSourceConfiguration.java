package com.xinchen.storage.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 *
 */
@Configuration
class DataSourceConfiguration {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource druidDataSource(){
    return new DruidDataSource();
  }


  @Bean
  public DataSourceProxy dataSourceproxy(DataSource druidDataSource){
    return new DataSourceProxy(druidDataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy)throws Exception{
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSourceProxy);
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources("classpath*:/mapper/*.xml"));
    sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
    return sqlSessionFactoryBean.getObject();
  }
}
