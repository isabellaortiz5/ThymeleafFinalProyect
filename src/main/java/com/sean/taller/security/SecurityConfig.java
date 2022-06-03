package com.sean.taller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.sean.taller.user.UserType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		
		httpSecurity.cors().disable().csrf().disable().authorizeRequests()
		.antMatchers("/api/**").permitAll()

		//product category permissions as admin user type.
		.antMatchers("/prod-categ*").permitAll()
		.antMatchers("/prod-categ/add/**")
		.hasRole(UserType.ADMINISTRATOR.toString()).antMatchers("/prod-categ/edit/**")
		.hasRole(UserType.ADMINISTRATOR.toString())
		
		//product sub-category permissions as admin user type.
		.antMatchers("/prod-sub-categ*").permitAll()
		.antMatchers("/prod-sub-categ/add/**")
		.hasRole(UserType.ADMINISTRATOR.toString()).antMatchers("/prod-sub-categ/edit/**")
		.hasRole(UserType.ADMINISTRATOR.toString())
		
		//product permissions as operator user type.
		.antMatchers("/prod*").permitAll()
		.antMatchers("/prod/add/**")
		.hasRole(UserType.OPERATOR.toString()).antMatchers("/prod/edit/**")
		.hasRole(UserType.OPERATOR.toString())
		
		//work order permissions as operator user type.
		.antMatchers("/work-ord*").permitAll()
		.antMatchers("/work-ord/add/**")
		.hasRole(UserType.OPERATOR.toString()).antMatchers("/work-ord/edit/**")
		.hasRole(UserType.OPERATOR.toString())
		
		.antMatchers("/**")
		.authenticated().anyRequest().permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
		.failureUrl("/login?error").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll()
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
	}
	
}
