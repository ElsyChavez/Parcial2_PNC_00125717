package com.uca.capas.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name ="cat_libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "c_libro")
	private Integer codigoLibro;
	
	@Size(max = 500, message = "El campo sobrepasa la cantidad de 500 caracteres.")
	@NotEmpty(message ="El campo no puede estar vacio.")
	@Column(name = "s_titulo")
	private String titulo;
	
	@Size(max = 150, message = "El campo sobrepasa la cantidad de 150 caracteres.")
	@NotEmpty(message ="El campo no puede estar vacio.")
	@Column(name = "s_autor")
	private String autor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_categoria")
	private Categoria categoria;
	
	@Column(name = "f_ingreso")
	private LocalDateTime fechaIngreso;
	
	@Column(name = "b_estado")
	private Boolean estado;

	@Pattern(regexp = "^[0-9]{10}$", message = "El ISBN debe tener exactamente 10 digitos.")
	@NotEmpty(message ="El campo no puede estar vacio.")
	@Column(name = "s_isbn")
	private String isbn;

	public Libro() {
		super();
	}

	

	public Integer getCodigoLibro() {
		return codigoLibro;
	}



	public void setCodigoLibro(Integer codigoLibro) {
		this.codigoLibro = codigoLibro;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}



	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}



	public Boolean getEstado() {
		return estado;
	}



	public void setEstado(Boolean estado) {
		this.estado = estado;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	//Funciones delegate
	public String getEstadoDelegate() {
		if(this.estado == null) return "";
		else {
			return estado == true ?"Disponible":"No disponible";
		}
	}
	
	public String getFechaDelegate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		String fechaI = dtf.format(this.fechaIngreso);
	    return fechaI;
	}
	
}
