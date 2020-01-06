	package org.gameproject.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration						// is a class-level annotation indicating that an object is a source of bean definitions.

@EnableTransactionManagement		// Enables Spring's annotation-driven transaction management capability; responsible for registering the necessary Spring components that power annotation-driven transaction management
@PropertySource({"classpath:database.properties"}) 			// externalize the configuration to a properties file
    
@ComponentScan({"org.gameproject"})		// scans the stereotype annotations (@Controller, @Service etc...) in a package specified by basePackages attribute
    									// Search for Spring components in all the package starting with org.gameproject (in our case, it's all of them)

@EnableJpaRepositories				// Annotation to enable JPA repositories. Will scan the package of the annotated configuration class for Spring Data repositories by default.
(basePackages = "org.gameproject.repository") 
public class PersistenceJPAConfig {

    @Autowired
    private Environment env;

    public PersistenceJPAConfig() {
        super();
    }
    /*
     *  @Bean Declare the method as a bean
     *  When JavaConfig encounters such a method, it will execute that method and register the return value as a bean within a BeanFactory. 
     *  By default, the bean name will be the same as the method name
     *
     * Those method will gives us access to the database
     *  First : we create our entityManager from our beans and we set the JpaVendorAdapter to "connect" Springs and Hibernate
     *  Second : we "connect" the properties from the database.properties files with our hibernateProperties
     *  Third : we connect to our database (here MySQL) using the driver/url/user/pass
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {
            "org.gameproject.entities"
        });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());

        return entityManagerFactoryBean;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

    /* Binds a JPA EntityManager from the specified factory to the thread
     * Used to configure the Spring Transactions
     */
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
    /*
     * Bean post-processor that automatically applies persistence exception translation to any bean marked with Spring's @Repository annotation, 
     * adding a corresponding PersistenceExceptionTranslationAdvisor to the exposed proxy
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}