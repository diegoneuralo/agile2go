package br.com.scrum.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 7194348807404290417L;
	
	@Column(name = "CIDADE", length = 60)
	private String cidade;
	
	@Column(name = "RUA", length = 60)
	private String rua;
	
	@Column(name = "UF", length = 2)
	private String uf;
	
	public final String getCidade() {
		return cidade;
	}
	public final void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public final String getRua() {
		return rua;
	}
	public final void setRua(String rua) {
		this.rua = rua;
	}
	public final String getUf() {
		return uf;
	}
	public final void setUf(String uf) {
		this.uf = uf;
	}
	
}
