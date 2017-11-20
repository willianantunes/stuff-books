package br.com.casadocodigo.configuracao.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	String[] caminhosPermitidos = new String[] {
    			"/", "/home", "/usuarios", "/bookserver/*", "/webjars/**", "/static/**", "/jquery*", "/h2/**"
			};
    	
        http
            .authorizeRequests()
                .antMatchers(caminhosPermitidos).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").permitAll()
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
