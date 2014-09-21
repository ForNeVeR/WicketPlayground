package me.fornever.wicketplayground.repositories;

import me.fornever.wicketplayground.entities.Movie;

import java.util.Collection;

public interface MovieRepository {

	public Collection<Movie> list();

	public Movie get(int id);

	public void save(Movie data);

	public void delete(int id);

}
