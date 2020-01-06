package org.gameproject.config;

import org.gameproject.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // Enable Spring Security; It allows Spring to find and automatically apply the class to the global WebSecurity.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;
	
	
		
	/*@SuppressWarnings("deprecation")		// like it say, suppress the warnings of deprecation, since i don't find any better way to do it yet
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}*/
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		// Users in memory
		
		auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
		auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER" ,"ADMIN");
		
		
		// For User in database
		auth.userDetailsService(myDBAauthenticationService);
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myDBAauthenticationService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	/*
	 * the method "configure" define who access to which pages
	 * which parameter is required for the different page
	 * what happen if a entry is wrong
	 * This is part of the Framework Spring Security
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		// The pages does not require login
		http.authorizeRequests().antMatchers("/","/welcome", "/login", "/logout", "/createSuccessful", "/showForm").permitAll();
		
		// /userInfo page requires login as USER or ADMIN
		// If no login, it will redirect to /login page
		http.authorizeRequests().antMatchers("/userInfo/**", "/updateForm/**", "/updateImgForm/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		
		// For ADMIN only
		 http.authorizeRequests().antMatchers("/admin", "/list").access("hasRole('ROLE_ADMIN')");
		 http.authorizeRequests().antMatchers("/userInfo/parameters/{id}").access("hasRole('ROLE_ADMIN')");
	
		// When the user has logged in as XX
		// But access a page that requires role YY
		// AccessDeniedException will throw
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		// Config for Login Form
		http.authorizeRequests().and().formLogin()//
        // Submit URL of login page.
        .loginProcessingUrl("/j_spring_security_check") // Submit URL
        .loginPage("/login")//
        .defaultSuccessUrl("/userInfo")//
        .failureUrl("/login?error=true")//
        .usernameParameter("identifiant")//
        .passwordParameter("mot_de_passe")
        // Config for Logout Page
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}
}
