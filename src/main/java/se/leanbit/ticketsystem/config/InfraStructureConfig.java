package se.leanbit.ticketsystem.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

@Configuration
@EnableJpaRepositories("se.leanbit.ticketsystem.repository")
@EnableTransactionManagement
public class InfraStructureConfig
{

	@Bean
	public DataSource dataSource()
	{
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost/Leanbit_Ticket_System?characterEncoding=utf8");
		config.setUsername("leanbit");
		config.setPassword("theone92");

		return new HikariDataSource(config);
	}

	@Bean
	public JpaTransactionManager transactionManager(final EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter()
	{
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setGenerateDdl(true);

		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setPackagesToScan("se.leanbit.ticketsystem.model");
		factory.setJpaProperties(entityManagerProperties());

		return factory;
	}

	Properties entityManagerProperties()
	{
		return new Properties()
		{
			{
				setProperty("hibernate.hbm2ddl.auto", "create");
			}
		};
	}
	
}
