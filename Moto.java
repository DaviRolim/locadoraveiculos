package LocadoraVeiculo;

import javax.persistence.*;

@Entity
public class Moto implements Veiculo{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

	private boolean partidaEletrica = false;
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
	public String getDescricaoVeiculo() {
        return this.descricao;
	}

	@Override
	public void setDescricaoVeiculo(String descricaoVeiculo) {
		this.descricao = descricaoVeiculo;
	}

	@Override
	public String getPlacaVeiculo() {
        return this.placa;
	}

	@Override
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placa = placaVeiculo;
	}


	public boolean isPartidaEletrica() {
		return this.partidaEletrica;
	}

	public void setPartidaEletrica() {
		this.partidaEletrica = true;
	}

	@Override
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	@Override
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
}
