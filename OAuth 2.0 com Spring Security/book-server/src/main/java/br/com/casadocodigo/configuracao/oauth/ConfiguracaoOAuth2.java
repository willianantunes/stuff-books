package br.com.casadocodigo.configuracao.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import br.com.casadocodigo.configuracao.web.RequestMappingPaths;

@Configuration
public class ConfiguracaoOAuth2 {
	public static final String RESOURCE_ID = "books";

	/**
	 * Responsabilidades e alguns detalhes do Resource Server:
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
			http
				.authorizeRequests()
					.anyRequest().authenticated().and()
				.requestMatchers()
					.antMatchers(RequestMappingPaths.API_V2_LIVROS, RequestMappingPaths.API_V2_ADMIN_LIVROS + "/**").and()
				/** 
				 * Como a aplicação client executa chamadas Ajax a partir de outro domínio, é 
				 * necessário habilitar CORS pois é justamente um exemplo de requisição cross domain.
				 * https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS 
				 */					
				.cors();
		}
	}

	/**
	 * Responsabilidades de um Authorization Server:
	 * <ul>
	 * <li>Permitir que o usuário autorize o acesso aos seus recursos sem precisar passar suas credenciais para o Client.</li>
	 * <li>Permitir a geração do token de acesso através do fluxo de obtenção do token de acesso (grant type).</li>
	 * </ul>
	 * A anotação <i>EnableAuthorizationServer</i> cria os seguintes endpoints:
	 * <ul>
	 * <li><strong>AuthorizationEndpoint:</strong> esse bean disponibiliza o endpoint <i>/oauth/authorize</i> e é usado para obter a autorização do usuário.</li>
	 * <li><strong>TokenEndpoint:</strong> esse	bean disponibiliza o endpoint <i>/oauth/token</i> para permitir que o Client faça a solicitação do token de acesso.</li> 
	 */
	@EnableAuthorizationServer
	protected static class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
		@Autowired
		private	AuthenticationManager authenticationManager;
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// Registro manual do Client para fins de teste
			clients.inMemory()
				.withClient("cliente-app")
				.secret("123456")
				// Indica que estamos adicionando suporte para o grant type Resource Owner Password Credential e Authorization Code e Implicit
				// Note que para o caso do implicit a especificação diz que é obrigatório registrar URI de redirecionamento, em um sistema real é mais que obrigatório
				.authorizedGrantTypes("password", "authorization_code", "implicit")
				// Indica que o Client solicita acesso de leitura e escrita nos recursos do usuário
				.scopes("read",	"write")
				.resourceIds(RESOURCE_ID)
			.and()
				.withClient("cliente-admin")
				.secret("123abc")
				.authorizedGrantTypes("client_credentials")
				.scopes("read")
				.resourceIds(RESOURCE_ID);
		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager);
		}		
	}
}