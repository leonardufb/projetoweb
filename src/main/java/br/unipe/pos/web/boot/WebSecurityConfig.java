/**
 * Projeto das trilhas de treinamento de Java básico ou avançado 
 * com foco nas certificações java e em treinamentos corporativos. 
 * Fontes disponíveis em https://github.com/rodrigofujioka
 * 
 * Professor: Rodrigo da Cruz Fujioka
 * Ano: 2016
 * http://www.rodrigofujioka.com
 * http://www.fujideia.com.br
 * http://lattes.cnpq.br/0843668802633139
 * 
 * Contato: rcf4@cin.ufpe.br 
 */
package br.unipe.pos.web.boot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Rodrigo C. Fujioka
 * @date 30 de abr de 2017
 * @time 03:51:35
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email, senha, enabled from tb_usuario where email=?")
				.authoritiesByUsernameQuery("select email, perfil from tb_perfil where email=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "/home", "/login/**").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login/form")
			//.usernameParameter("email")
			//.passwordParameter("senha")
			.defaultSuccessUrl("/contato/listar")
			.permitAll()
		.and()
			.logout()
			.permitAll();
		
		http.exceptionHandling().accessDeniedPage("/403");
	}
}