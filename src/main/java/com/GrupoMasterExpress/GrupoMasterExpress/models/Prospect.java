package com.GrupoMasterExpress.GrupoMasterExpress.models;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.ManyToOne;


@Entity
public class Prospect {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String cnpj;
	private String responsavel;
	private  String servico;
	private  String data;
	private  String email;
	private  String telefone;
	private  String orcamento;
	
	
	@ManyToOne
	private Prospect prospect;

	public String getCNPJ() {
		return cnpj;
	}

	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
	this.responsavel = responsavel;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
	this.servico = servico;
	}

	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public String getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(String orcamento) {
		this.orcamento = orcamento;
	}

    public void setFuncionario(Funcionario funcionario) {
    }

    public Funcionario getFuncionario() {
        return null;
    }
	
}
