package br.com.casadocodigo.usuarios;

import javax.persistence.Embeddable;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class Credenciais {
	private String email;
	private String senha;
}