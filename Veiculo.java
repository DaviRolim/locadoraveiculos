package LocadoraVeiculo;

public interface Veiculo {
	
	public boolean isLocado();
	public void setLocado(boolean locado);
	public double getValorLocacao();
	public void setValorLocacao(double valorLocacao);
	public String getDescricaoVeiculo();
	public void setDescricaoVeiculo(String descricaoVeiculo);
	public String getPlacaVeiculo();
	public void setPlacaVeiculo(String placaVeiculo);
	public TipoVeiculo getTipoVeiculo();
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo);




}
