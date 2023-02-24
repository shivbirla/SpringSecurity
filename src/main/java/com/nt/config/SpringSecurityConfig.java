package com.nt.config;

import java.util.concurrent.BrokenBarrierException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nt.encrypt.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		// build Authentication manager by taking given Authentication Info
		auth.jdbcAuthentication().dataSource(ds).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USER WHERE UNAME= ?")// for authentication

				.authoritiesByUsernameQuery("SELECT ROLE,UNAME FROM USER_ROLES WHERE UNAME= ?");// for authorization
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// authorizeRequest
		http.authorizeRequests().antMatchers("/").permitAll()// not authenticated and no authorization
				.antMatchers("/offers").authenticated()// only authentication
				.antMatchers("/balance").hasAnyAuthority("CUSTOMER", "MANAGER")// authentication+authorization for mngr
																				// role and cstmr role
				.antMatchers("/loanApprove").hasAuthority("MANAGER")// authentication+authorization for manager role
																	// user
				.anyRequest().authenticated()// remaining all request URL must be authenticated

//		specify authentication mode
				.and().httpBasic()

				.and().formLogin()

//				add remember filter
				.and().rememberMe()

//		enable logout
//				.and().logout()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout"))

//		Exception /Error handling 
				.and().exceptionHandling().accessDeniedPage("/denied")

//				add SessionMax Concurrncy count
//				.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
				.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);

	}

}
