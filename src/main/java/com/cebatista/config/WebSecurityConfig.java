package com.cebatista.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cebatista.service.UserDetailsServiceImpl;



@Configuration
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;



	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	//  @Bean
	//  @Override
	//  public AuthenticationManager authenticationManagerBean() throws Exception {
	//    return super.authenticationManagerBean();
	//  }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//  @Override
	//  protected void configure(HttpSecurity http) throws Exception {
	//    http.cors().and().csrf().disable()
	//      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	//      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	//      .antMatchers("/api/test/**").permitAll()
	//      .anyRequest().authenticated();
	//
	//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	//  }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/templates/**").permitAll()
		.and()
		.authorizeRequests()
			.antMatchers("/projetos").hasAnyRole("USER")
			.antMatchers("/relatorio-equipe").hasAnyRole("MODERATOR")
			.antMatchers("/relatorio-custos").hasAnyRole("ADMIN")
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/entrar")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/entrar?logout")
			.permitAll()
		.and()
			.rememberMe()
			.userDetailsService(userDetailsService);
		return http.build();
	}
}
