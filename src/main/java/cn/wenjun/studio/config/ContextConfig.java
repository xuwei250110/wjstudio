package cn.wenjun.studio.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties
@ComponentScan(basePackages = { "cn.wenjun.studio.service", "cn.wenjun.studio.dao" })
public class ContextConfig {

  @Autowired
  DataSource dataSource;

  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(DataSource datasource) {
    return new DataSourceTransactionManager(datasource);
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource datasource) throws NamingException {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(datasource);
    sqlSessionFactoryBean.setTypeAliasesPackage("cn.wenjun.studio.bean;cn.wenjun.studio.bean");
    return sqlSessionFactoryBean;
  }

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setBasePackage("cn.wenjun.studio.dao");
    return mapperScannerConfigurer;
  }

}
