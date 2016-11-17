package LocadoraVeiculo;

import javax.persistence.*;

@Entity
public class Carro implements Veiculo{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipoVeiculo;

	private int numeroPassageiros;
	private double valorLocacao;
	private String descricao;
	private String placa;
	private boolean locado = false;


	@Override
	public boolean isLocado() {
		return this.locado;
	}

	@Override
	public void setLocado(boolean locado) {
		this.locado = true;
	}

	@Override
	public double getValorLocacao() {
		return this.valorLocacao;
	}

	@Override
	public void setValorLocacao(double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}

	@Override
	public String getPlacaVeiculo() {
		return this.placa;
	}

	@Override
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placa = placaVeiculo;
	}

	@Override
	public TipoVeiculo getTipoVeiculo() {
		return this.tipoVeiculo;
	}

	@Override
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public int getNumeroPassageiros() {
		return this.numeroPassageiros;
	}


	public void setNumeroPassageiros(int numeroPassageiros) {
		this.numeroPassageiros = numeroPassageiros;
	}


	@Override
	public String getDescricaoVeiculo() {
		return this.descricao;
	}

	@Override
	public void setDescricaoVeiculo(String descricaoVeiculo) {
		this.descricao = descricaoVeiculo;
	}
	
}
