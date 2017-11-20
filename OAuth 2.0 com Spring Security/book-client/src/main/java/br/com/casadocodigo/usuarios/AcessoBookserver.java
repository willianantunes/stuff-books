package br.com.casadocodigo.usuarios;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class AcessoBookserver {
    @Column(name = "login_bookserver")
    private String login;
    @Column(name = "senha_bookserver")
    private String senha;
}