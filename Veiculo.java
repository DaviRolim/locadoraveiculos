package LocadoraVeiculo;

public class Veiculo {
	
	private String tipoVeiculo;
	private double valorLocacao;
	private String descricaoVeiculo;
	private String placaVeiculo;
	
	
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public double getValorLocacao() {
		return valorLocacao;
	}
	public void setValorLocacao(double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}
	public String getDescricaoVeiculo() {
		return descricaoVeiculo;
	}
	public void setDescricaoVeiculo(String descricaoVeiculo) {
		this.descricaoVeiculo = descricaoVeiculo;
	}
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	public int getNumeroPassageiros() {
		return (Integer) null;
	}
	public boolean isPartidaEletrica() {
		return false;
	}
	

}
