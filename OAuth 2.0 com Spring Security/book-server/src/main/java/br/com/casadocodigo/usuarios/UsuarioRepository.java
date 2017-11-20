package br.com.casadocodigo.usuarios;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	@Query("select u from Usuario u where u.credenciais.email = ?1")
	Optional<Usuario> buscarPorEmail(String email);
}