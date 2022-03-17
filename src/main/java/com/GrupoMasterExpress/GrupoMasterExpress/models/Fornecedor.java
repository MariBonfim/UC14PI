package com.GrupoMasterExpress.GrupoMasterExpress.models;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Column;

@Entity
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empid;
	
	@NotEmpty
	private String nome;
	
	@Column(unique = true)
	private String cnpj;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String telefone;
	
	@NotEmpty
	private String estoque;


	@NotEmpty
	private String insumo;

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public String getInsumo() {
	return insumo;
	
	}

	public void setInsumo(String insumo) {
	this.insumo = insumo;
	}
	
}
