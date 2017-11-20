package br.com.casadocodigo.integracao.model;

import lombok.Data;

@Data
public class Livro {
    private int id;
    private String titulo;
    private int nota;
}