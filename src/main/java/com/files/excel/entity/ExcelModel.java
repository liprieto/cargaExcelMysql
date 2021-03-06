package com.files.excel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "archivos")

public class ExcelModel {

	@Id
	@Column(name = "id")
	private long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "cuit")
	private long cuit;

	public ExcelModel(long id, String nombre, String apellido, long cuit) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuit = cuit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long isCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	@Override
	public String toString() {
		return "ExcelModel [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cuit=" + cuit + "]";
	}
}
	  
	  
