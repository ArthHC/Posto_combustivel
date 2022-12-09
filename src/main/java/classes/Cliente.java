package classes;

public class Cliente extends Pessoa {
	
	private Integer IdCliente;
	private String cpfCli;
	private String endereco;
	
	public Integer getIdCliente() {
		return IdCliente;
	}
	
	public void setIdCliente(Integer IdCliente) {
		this.IdCliente = IdCliente;
	}

	public String getCpfCli() {
		return cpfCli;
	}

	public void setCpfCli(String cpf) {
		this.cpfCli = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
