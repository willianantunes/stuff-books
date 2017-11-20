package br.com.casadocodigo.usuarios;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class DadosDeRegistro {
	@NotEmpty
	private String nome;
	@NotEmpty
	private String email;
	@NotEmpty
	private String senha;
}