package org.gameproject.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc				// enables default Spring MVC configuration
@ComponentScan(basePackages = {
    "org.gameproject.controller"
})
// WebMvcConfigurer Defines callback methods to customize the Java-based configuration for Spring MVC enabled via @EnableWebMvc. 
public class WebMvcConfig implements WebMvcConfigurer {
	
	 private static final Charset UTF8 = Charset.forName("UTF-8");
	 
	   // Config UTF-8 Encoding.
	   @Override
	   public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	       StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
	       stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
	       converters.add(stringConverter);
	 
	       // Add other converters if needed...
	   }
	 @Bean
	  public ResourceBundleMessageSource messageSource() {
	      ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
	      // Load property in message/validator.properties
	      rb.setBasenames(new String[] { "messages/validator" });
	      return rb;
	  }

	 /*
	  * allows us to generate the final  view page URL
	  */
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /*
     * will locate all the resources needed (css, Js )
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**", "/**")
            .addResourceLocations("/resources/", "file:/");
    }
    
    // Equivalent for <mvc:default-servlet-handler/> tag
    // It tells Spring to use the container's default servlet for certain requests, like for static resources.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
   
    
    // add commentary there !
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520);   // 20MB
        multipartResolver.setMaxInMemorySize(1048576);  // 1MB
        return multipartResolver;
    }
}