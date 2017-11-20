package br.com.casadocodigo.configuracao.seguranca.basic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.casadocodigo.configuracao.web.RequestMappingPaths;

@EnableWebSecurity
public class ConfiguracaoDeSeguranca {

	@Configuration
	@Order(1)
	public static class ConfiguracaoParaAPI extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.anyRequest().authenticated().and()
				.antMatcher(RequestMappingPaths.API_LIVROS)
					.httpBasic().and()
				// Não é necessário proteção contra ataques CSRF pois as APIs da aplicação 
				// são stateless, ou seja, não utilizam o conceito de sessão.
				.csrf().disable();
		}
	}
	
	@Configuration
	public static class ConfiguracaoParaUsuario extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			String[] caminhosPermitidos = new String[] {
				"/", "/home", "/usuarios",
				"/webjars/**", "/static/**", "/jquery*",
				"/h2/**"
			};

			http
				.authorizeRequests()
					.antMatchers(caminhosPermitidos).permitAll()
					.anyRequest().authenticated().and()
				.formLogin()
					.permitAll()
					.loginPage("/login")
					.and()
				.logout()
					.permitAll()
					.and()
				.csrf().disable();

			// Para funcionar página H2
			http
				.headers().frameOptions().sameOrigin();
		}
	}
}