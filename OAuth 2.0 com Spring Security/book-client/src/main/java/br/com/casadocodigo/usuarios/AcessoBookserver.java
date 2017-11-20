package br.com.casadocodigo.usuarios;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class AcessoBookserver {
	@Column(name = "token_bookserver")
	private String accessToken;
}