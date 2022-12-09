package classes;

public class Fornecedor {
	
	private Integer idFornecedor;
	private String nomeForn;
	private String cnpj;
	private String emailForn;
	private String telefoneForn;
	private String endereco;
	
	public Integer getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getNomeForn() {
		return nomeForn;
	}
	public void setNomeForn(String nomeForn) {
		this.nomeForn = nomeForn;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmailForn() {
		return emailForn;
	}
	public void setEmailForn(String emailForn) {
		this.emailForn = emailForn;
	}
	public String getTelefoneForn() {
		return telefoneForn;
	}
	public void setTelefoneForn(String telefoneForn) {
		this.telefoneForn = telefoneForn;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
