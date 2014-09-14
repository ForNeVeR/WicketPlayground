package me.fornever.wicketplayground.repositories;

import me.fornever.wicketplayground.entities.Movie;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public class MovieRepositoryImpl implements MovieRepository {

	private SessionFactory sessionFactory;

	public MovieRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<Movie> list() {
		return sessionFactory.getCurrentSession().createCriteria(Movie.class).list();
	}

	@Override
	@Transactional
	public Movie get(int id) {
		return (Movie) sessionFactory.getCurrentSession().get(Movie.class, id);
	}

	@Override
	@Transactional
	public void save(Movie data) {
		sessionFactory.getCurrentSession().save(data);
	}

	@Override
	@Transactional
	public void delete(int id) {
		sessionFactory.getCurrentSession().delete(id);
	}

}
