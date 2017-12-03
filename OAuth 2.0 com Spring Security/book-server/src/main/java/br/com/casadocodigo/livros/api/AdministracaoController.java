package br.com.casadocodigo.livros.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.casadocodigo.configuracao.web.RequestMappingPaths;
import br.com.casadocodigo.livros.LivroRepository;

@Controller
@RequestMapping(RequestMappingPaths.API_V2_ADMIN_LIVROS)
public class AdministracaoController {
	@Autowired
	private LivroRepository repositorioDeLivros;
	
	@RequestMapping(method = RequestMethod.GET, value = "/total_livros")
	public ResponseEntity<Long> getTotalDeLivros() {
		Long totalDeLivros = repositorioDeLivros.count();
		
		return ResponseEntity.ok(totalDeLivros);
	}
}