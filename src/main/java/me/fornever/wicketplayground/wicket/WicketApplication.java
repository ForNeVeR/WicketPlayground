package me.fornever.wicketplayground.wicket;

import me.fornever.wicketplayground.pages.MoviesPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class WicketApplication extends WebApplication {

	@Override
	public Class<MoviesPage> getHomePage() {
		return MoviesPage.class;
	}

	@Override
	protected void init() {
		super.init();
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

		mountPages();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

	private void mountPages() {
		mountPage("/movies", MoviesPage.class);
	}

}
