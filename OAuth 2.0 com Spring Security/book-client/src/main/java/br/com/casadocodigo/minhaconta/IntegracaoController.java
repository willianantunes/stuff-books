package br.com.casadocodigo.minhaconta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.configuracao.seguranca.UsuarioLogado;
import br.com.casadocodigo.integracao.bookserver.AuthorizationCodeTokenService;
import br.com.casadocodigo.integracao.bookserver.ImplicitTokenService;
import br.com.casadocodigo.integracao.bookserver.OAuth2Token;
import br.com.casadocodigo.integracao.bookserver.PasswordTokenService;
import br.com.casadocodigo.usuarios.AcessoBookserver;
import br.com.casadocodigo.usuarios.Usuario;
import br.com.casadocodigo.usuarios.UsuariosRepository;

@Controller
@RequestMapping("/integracao")
public class IntegracaoController {

    @Autowired
    private UsuariosRepository usuarios;
    
    @Autowired
    private PasswordTokenService passwordTokenService;

	@Autowired
	private AuthorizationCodeTokenService authorizationCodeTokenService;

	@Autowired
	private ImplicitTokenService implicitTokenService;	

	// Caso para quando for testar grant type Authorization Code
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView integracao() {
        // Caso para quando for testar grant type password: return new ModelAndView("minhaconta/integracao");
    	
    	String authorizationEndpoint = implicitTokenService.getAuthorizationEndpoint();    	
		return new ModelAndView("redirect:" + authorizationEndpoint);
    }	

	@RequestMapping(value = "/implicit", method = RequestMethod.GET)
	public ModelAndView implicit() {
		return new ModelAndView("minhaconta/bookserver");
	}

//	  // Caso para quando for testar grant type Authorization Code
//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView integracao() {
//        // Caso para quando for testar grant type password: return new ModelAndView("minhaconta/integracao");
//    	
//    	String authorizationEndpoint = authorizationCodeTokenService.getAuthorizationEndpoint();    	
//		return new ModelAndView("redirect:" + authorizationEndpoint);
//    }

//	  // Caso para quando for testar grant type password    
//    @RequestMapping(method = RequestMethod.POST)
//    public ModelAndView autorizar(Autorizacao autorizacao) {
//
//    	Usuario usuario = usuarioLogado();
//    	OAuth2Token token = passwordTokenService.getToken(usuario.getLogin(), usuario.getSenha());
//
//    	AcessoBookserver acessoBookserver = new AcessoBookserver();
//    	acessoBookserver.setAccessToken(token.getAccessToken());
//        usuario.setAcessoBookserver(acessoBookserver);
//
//        usuarios.save(usuario);
//
//        return new ModelAndView("redirect:/minhaconta/principal");
//    }
    
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public ModelAndView callback(String code, String state) {

        OAuth2Token token = authorizationCodeTokenService.getToken(code);

        AcessoBookserver acessoBookserver = new AcessoBookserver();
        acessoBookserver.setAccessToken(token.getAccessToken());

        Usuario usuario = usuarioLogado();
        usuario.setAcessoBookserver(acessoBookserver);

        usuarios.save(usuario);

        return new ModelAndView("redirect:/minhaconta/principal");
    } 

    private Usuario usuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioLogado usuarioLogado = (UsuarioLogado) authentication.getPrincipal();
        return usuarios.findOne(usuarioLogado.getId());
    }

}
