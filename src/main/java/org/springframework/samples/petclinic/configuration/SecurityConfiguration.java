package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","welcome","/oups","/home","/instrucciones", "/diaHistorico").permitAll()
				.antMatchers("/jugadores/new/**").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/session/**").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/owners/**").hasAnyAuthority("owner","admin")				
				.antMatchers("/vets/**").authenticated()
				.antMatchers("/partidas").hasAuthority("admin")
				.antMatchers("/logros/**").authenticated()
				.antMatchers("/estadisticas/**").permitAll()
				.antMatchers("/jugadores/searchFilter/**").authenticated()
				.antMatchers("/jugadores/perfil/**").authenticated()
				.antMatchers("/jugadores/logros/**").authenticated()
				.antMatchers("/jugadores/partidas/**").authenticated()
				.antMatchers("/jugadores/editPerfil/**").hasAuthority("jugador")
				.antMatchers("/jugadores/search/**").authenticated()
				.antMatchers("/jugadores/espectar/**").authenticated()
				.antMatchers("/jugadores/**").hasAuthority("admin")
				.antMatchers("/partidas/partidasActivas/**").hasAuthority("admin")
				.antMatchers("/partidas/**").hasAuthority("jugador")
				.antMatchers("/jugar/**").authenticated()
				.antMatchers("/chat/**").authenticated()
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
					.defaultSuccessUrl("/home")
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/home"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  new BCryptPasswordEncoder();
	    return encoder;
	}
}


