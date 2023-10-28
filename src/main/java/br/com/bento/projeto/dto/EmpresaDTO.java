package br.com.bento.projeto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmpresaDTO {

	@JsonProperty("cnpj")
	private String cnpj;

	@JsonProperty("razao_social")
	private String razaoSocial;

	@JsonProperty("nome_fantasia")
	private String nomeFantasia;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public String toString() {
		return "EmpresaDTO [cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + "]";
	}

}
