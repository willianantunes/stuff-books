package br.com.casadocodigo.livros;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.casadocodigo.usuarios.Usuario;
import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@ToString
public class Estante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Setter
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "estante")	
	private Usuario usuario;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "estante_id")
	private List<Livro> livros = new ArrayList<>();

	public boolean temLivros() {
		return livros.size() > 0;
	}

	public Collection<Livro> todosLivros() {
		return Collections.unmodifiableCollection(livros);
	}

	public void adicionar(Livro livro) {
		livros.add(livro);
	}
}