package com.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name ="cat_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "c_categoria")
	private Integer codigoCategoria;
	
	@Size(max = 50, message = "El campo sobrepasa la cantidad de 50 caracteres.")
	@NotEmpty(message ="El campo no puede estar vacio.")
	@Column(name = "s_categoria")
	private String titulo;
	
	@OneToMany(mappedBy = "cat_categoria", fetch = FetchType.EAGER)
	private List<Libro> libros;

	public Categoria() {
		super();
	}

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
}
