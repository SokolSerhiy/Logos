package ua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService service;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
		.userDetailsService(this.service)
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.usernameParameter("login")
			.passwordParameter("password")
			.loginPage("/login")
			.failureUrl("/login?fail=true")
		.and().logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID", "Secret_cookei")
		.and()
			.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/")
		.and()
			.sessionManagement().maximumSessions(1)
		.and().and()
			.rememberMe()
			.rememberMeParameter("rememberMe")
			.key("my super secret key")
			.rememberMeCookieName("Secret_cookei");
	}
}
