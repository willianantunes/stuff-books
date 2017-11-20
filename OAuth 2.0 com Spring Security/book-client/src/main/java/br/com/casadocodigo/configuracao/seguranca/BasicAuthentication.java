package br.com.casadocodigo.configuracao.seguranca;

import java.util.Base64;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasicAuthentication {
    private String login;
    private String senha;

    public String getCredenciaisBase64() {
        String credenciais = login + ":" + senha;
        String credenciaisCodificadasComBase64 = 
        		new String(Base64.getEncoder().encode(credenciais.getBytes()));

        return credenciaisCodificadasComBase64;
    }
}