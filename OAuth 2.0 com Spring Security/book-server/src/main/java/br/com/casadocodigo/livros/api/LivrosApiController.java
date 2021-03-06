package br.com.casadocodigo.livros.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.configuracao.seguranca.ResourceOwner;
import br.com.casadocodigo.configuracao.web.RequestMappingPaths;
import br.com.casadocodigo.livros.Estante;
import br.com.casadocodigo.usuarios.Usuario;
import br.com.casadocodigo.usuarios.UsuarioRepository;

@RestController
@RequestMapping({ RequestMappingPaths.API_LIVROS, RequestMappingPaths.API_V2_LIVROS })
public class LivrosApiController {

    @Autowired
    private UsuarioRepository usuarios;

	@PreAuthorize(value = "#oauth2.hasScope('read')	and	hasRole('ROLE_USUARIO_COMUM')")
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<?> livros() {

        Estante estante = donoDosLivros().getEstante();

        if (estante.temLivros()) {
            return new ResponseEntity<>(estante.todosLivros(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private Usuario donoDosLivros() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResourceOwner donoDosLivros = (ResourceOwner) authentication.getPrincipal();

        return usuarios.findOne(donoDosLivros.getId());
    }
}