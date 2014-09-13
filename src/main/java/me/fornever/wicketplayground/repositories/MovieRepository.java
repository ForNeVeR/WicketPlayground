package me.fornever.wicketplayground.repositories;

import me.fornever.wicketplayground.entities.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository {

	public Movie get(int id);

	public void save(Movie data);

	public void delete(int id);

}
