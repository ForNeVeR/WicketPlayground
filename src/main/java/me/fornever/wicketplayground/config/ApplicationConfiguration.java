package me.fornever.wicketplayground.config;

import me.fornever.wicketplayground.repositories.MovieRepository;
import me.fornever.wicketplayground.repositories.MovieRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class ApplicationConfiguration {

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() throws Exception {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan("me.fornever.wicketplayground.entities");

		sessionFactory.afterPropertiesSet();
		return sessionFactory.getObject();
	}

	@Autowired
	@Bean(name = "txManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

	@Autowired
	@Bean(name = "movieRepository")
	public MovieRepository getMovieRepository(SessionFactory sessionFactory) {
		return new MovieRepositoryImpl(sessionFactory);
	}

	private Properties getHibernateProperties() {
		return new Properties() {{
			setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
			setProperty("hibernate.connection.datasource", "jdbc/wicketPlayground");
			setProperty("hibernate.connection.autocommit", "false");
			setProperty("hibernate.hbm2ddl.auto", "update");
		}};
	}
}