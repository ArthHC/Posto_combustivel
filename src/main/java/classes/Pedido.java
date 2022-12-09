package classes;

public class Pedido {
	
	private Integer idPedido;
	private String nomePedido;
	private Float valor;
	private Float qtde;
	private Integer FkPagamento;
	private Integer FkProduto;
	private Integer FkCliente; 
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public String getNomePedido() {
		return nomePedido;
	}
	public void setNomePedido(String nomePedido) {
		this.nomePedido = nomePedido;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Float getQtde() {
		return qtde;
	}
	public void setQtde(Float qtde) {
		this.qtde = qtde;
	}
	public Integer getFkPagamento() {
		return FkPagamento;
	}
	public void setFkPagamento(Integer fkPagamento) {
		FkPagamento = fkPagamento;
	}
	public Integer getFkProduto() {
		return FkProduto;
	}
	public void setFkProduto(Integer fkProduto) {
		FkProduto = fkProduto;
	}
	public Integer getFkCliente() {
		return FkCliente;
	}
	public void setFkCliente(Integer fkCliente) {
		FkCliente = fkCliente;
	}
	
	

}
