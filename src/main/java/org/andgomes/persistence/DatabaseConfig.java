package org.andgomes.persistence;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({"classpath:persistence-postgres.properties"})
@EnableTransactionManagement
@EnableJpaRepositories
public class DatabaseConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		return dataSource;

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean em =
				new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] {"org.andgomes.model"});
		em.setPersistenceUnitName("PersonPU");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		em.setJpaVendorAdapter(vendorAdapter);

		return em;

	}

	@Bean
	public PlatformTransactionManager transactionManager(
			LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}

}