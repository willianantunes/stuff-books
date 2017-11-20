package br.com.casadocodigo.livros;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class DadosDoLivro {
    @NotEmpty
    private String titulo;
    @Range(min = 0, max = 10)
    private int nota;
}