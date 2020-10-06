
package com.tesisiii.config;

import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan({"com.tesisiii"})
public class HibernateConfig {
   
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceOracle());
        sessionFactory.setPackagesToScan("com.tesisiii");
        sessionFactory.setHibernateProperties(hibernatePropertiesOracle());
 
        return sessionFactory;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
    
    @Bean(name = "datasource")
    public DataSource dataSourceOracle() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:XE");
        dataSource.setUser("luc");
        dataSource.setPassword("1125");
        return dataSource;
    }
    
    private final Properties hibernatePropertiesOracle() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
 
        return hibernateProperties;
    }
}