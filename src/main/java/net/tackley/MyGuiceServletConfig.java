package net.tackley;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import net.tackley.weretwit.controller.MainController;

public class MyGuiceServletConfig extends GuiceServletContextListener {

	public MyGuiceServletConfig() {
		GuiceDebug.enable();
	}

	@Override
  protected Injector getInjector() {
    return Guice.createInjector(new MyMainModule(), new ServletModule() {
	    @Override
	    protected void configureServlets() {
		    serve("/", "*.hello.html").with(MainController.class);
	    }
    });
  }
}