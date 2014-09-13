package me.fornever.wicketplayground.config;

import me.fornever.wicketplayground.repositories.MovieRepository;
import me.fornever.wicketplayground.repositories.MovieRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
public class ApplicationConfiguration {

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan("me.fornever.wicketplayground.entities");

		return sessionFactory.getObject();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

	@Autowired
	@Bean(name = "movieRepository")
	public MovieRepository getUserDao(SessionFactory sessionFactory) {
		return new MovieRepositoryImpl(sessionFactory);
	}

	Properties getHibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
				setProperty("hibernate.connection.datasource", "jdbc/wicketPlayground");
				setProperty("hibernate.connection.autocommit", "false");
				setProperty("hibernate.hbm2ddl.auto", "update");
			}

			;
		};
	}
}