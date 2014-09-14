package me.fornever.wicketplayground.repositories;

import me.fornever.wicketplayground.entities.Movie;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository {

	public Collection<Movie> list();

	public Movie get(int id);

	public void save(Movie data);

	public void delete(int id);

}
