package org.gameproject.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	/*
	 * The spring will detect the instance of this class during application startup, 
	 * and register the DelegatingFilterProxy to use the springSecurityFilterChain before any other registered Filter. 
	 * It also register a ContextLoaderListener.
	 * No code is needed !
	 */

}
