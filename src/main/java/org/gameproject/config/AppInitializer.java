package org.gameproject.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/*
 * This class purpose is to be executed before the Spring application context gets completely created,
 * 
 * the extends is to register a DispatcherServlet and use Java-based Spring configuration 	
 * it required  2 methods :
 * getRootConfigClasses => for root application context (non-web infrastructure) configuration
 * getServletConfigClasses => for dispatcherServlet application context ( Spring MVC infrastructure) configuration
 * 
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	/*
	 * Root Config Classes are actually used to Create Beans which are Application Specific 
	 * and which needs to be available for Filters (As Filters are not part of Servlet) if needed.
	 * 
	 * 
	 * will be the Parent Context and it will create a ApplicationContext instace. 
	 * => be loaded first and then Servlet Config Classes will be loaded
	 */
    @Override
    protected Class < ? > [] getRootConfigClasses() {
        return new Class[] {
            PersistenceJPAConfig.class		
        };
        //return null;
    }

    
    /* 
     * Servlet Config Classes are actually used to Create Beans which are DispatcherServlet specific 
     * such as ViewResolvers, ArgumentResolvers, Interceptor, etc.
     * 
     * will be the Child Context of the Parent Context(= RootConfigClasses) and it will create a WebApplicationContext instance.
     */  
    @Override
    protected Class < ? > [] getServletConfigClasses() {
        return new Class[] { 
        		WebMvcConfig.class
        };
    }

    
    /* 
     * returns mappings that are used during servlet registration 
     * What's servlet mapping ? => https://javapapers.com/servlet/what-is-servlet-mapping/
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {
            "/"
        };
    }
}