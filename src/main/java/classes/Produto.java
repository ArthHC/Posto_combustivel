package classes;

public class Produto {
	
	private Integer idProduto;
	private String nomeProduto;
	private Integer codigoBarras;
	private Float qtde;
	private Float valor;
	private Integer FkCombustivel;	
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Integer getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(Integer codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Float getQtde() {
		return qtde;
	}
	public void setQtde(Float qtde) {
		this.qtde = qtde;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Integer getFkCombustivel() {
		return FkCombustivel;
	}
	public void setFkCombustivel(Integer fkCombustivel) {
		FkCombustivel = fkCombustivel;
	}
	
	
}
