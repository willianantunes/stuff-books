package br.com.casadocodigo.usuarios;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DadosCadastrais {
    private String nome;
    private String login;
    private String senha;
}