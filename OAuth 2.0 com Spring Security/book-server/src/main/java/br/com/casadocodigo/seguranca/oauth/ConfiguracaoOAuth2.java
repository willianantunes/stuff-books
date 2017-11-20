package br.com.casadocodigo.seguranca.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import br.com.casadocodigo.configuracao.web.RequestMappingPaths;

@Configuration
public class ConfiguracaoOAuth2 {
	public static final String RESOURCE_ID = "books";

	/**
	 * Responsabilidades e alguns detalhes:
	 * <ul>
	 * <li>Responsável por validar se o Client que está tentando acessar os recursos do usuário tem ou não as permissões necessárias</li>
	 * <li>Para validar o token fornecido pelo Client pode interagir com o Authorization Server ao acessar um banco de dados 
	 * compartilhado ou consultar detalhes do token através de algum serviço fornecido pelo Authorization Server</li>
	 * <li>É possível utilizar tokens autocontidos que evitam que o Resource Server precise se comunicar sempre 
	 * com o Authorization Server para validar um token</li>
	 * </ul>
	 */
	@EnableResourceServer
	protected static class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(RESOURCE_ID);
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated().and().requestMatchers()
					.antMatchers(RequestMappingPaths.API_V2_LIVROS);
		}
	}

	/**
	 * Responsabilidades de um Authorization Server:
	 * <ul>
	 * <li>Permitir que o usuário autorize o acesso aos seus recursos sem precisar passar suas credenciais para o Client.</li>
	 * <li>Permitira geração do token de acesso através do fluxo de obtenção do token de acesso (grant type).</li>
	 * </ul>
	 * A anotação <i>EnableAuthorizationServer</i> cria os seguintes endpoints:
	 * <ul>
	 * <li><strong>AuthorizationEndpoint:</strong> esse bean disponibiliza o endpoint <i>/oauth/authorize</i> e é usado para obter a autorização do usuário.</li>
	 * <li><strong>TokenEndpoint:</strong> esse	bean disponibiliza o endpoint <i>/oauth/token</i> para permitir que o Client faça a solicitação do token de acesso.</li> 
	 */
	@EnableAuthorizationServer
	protected static class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
		
	}
}