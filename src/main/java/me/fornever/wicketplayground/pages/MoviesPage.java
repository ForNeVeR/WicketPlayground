package me.fornever.wicketplayground.pages;

import me.fornever.wicketplayground.entities.Movie;
import me.fornever.wicketplayground.repositories.MovieRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MoviesPage extends WebPage {

	@SpringBean
	MovieRepository movieRepository;

	public MoviesPage() {
		Movie movie = new Movie();
		movie.setName("Movie-x");
		movieRepository.save(movie);

		Label mainDiv = new Label("mainDiv", movieRepository.list().size());
		add(mainDiv);
	}

}
