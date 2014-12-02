package com.ggr.spring4.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * On startup, server looks for WebApplicationInitializer. If server finds it in the application, then server starts the application using the settings defined in WebApplicationInitializer
 * implementing class.
 * 
 * @author JPatel
 * 
 */
public class WebAppInitializer implements WebApplicationInitializer
{
    public void onStartup(ServletContext servletContext) throws ServletException
    {
	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(AppConfig.class);
	ctx.setServletContext(servletContext);
	Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
	dynamic.addMapping("/");
	dynamic.setLoadOnStartup(1);
    }
}