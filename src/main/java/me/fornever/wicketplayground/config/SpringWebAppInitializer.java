package me.fornever.wicketplayground.config;

import me.fornever.wicketplayground.wicket.WicketApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;
import java.util.HashMap;

public class SpringWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(ApplicationConfiguration.class);

		servletContext.addListener(new ContextLoaderListener(appContext));

		FilterRegistration.Dynamic wicketFilter = servletContext.addFilter(
				"me.fornever.WicketPlayground",
				WicketFilter.class);
		wicketFilter.setInitParameters(
				new HashMap<String, String>() {{
					put("applicationClassName", WicketApplication.class.getCanonicalName());
					put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
				}});
		wicketFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");

		FilterRegistration.Dynamic osivFilter = servletContext.addFilter(
				"hibernateFilter",
				OpenSessionInViewFilter.class);
		osivFilter.setInitParameter("singleSession", "true");
		osivFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
	}

}
