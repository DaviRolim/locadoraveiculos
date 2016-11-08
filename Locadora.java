package LocadoraVeiculo;

import java.util.ArrayList;
import java.util.Scanner;

public class Locadora {
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	ArrayList<Veiculo> locados = new ArrayList<Veiculo>();
	ArrayList<Veiculo> disponiveis = new ArrayList<Veiculo>();
	double total;

	public void cadastraCliente() {

		Cliente c = new Cliente();
		Scanner in = new Scanner(System.in);

		System.out.print("Informe seu nome: ");
		String nome = in.nextLine();
		c.setNome(nome);

		System.out.print("Informe seu CPF: ");
		String cpf = in.nextLine();
		c.setCpf(cpf);

		System.out.print("Informe sua idade: ");
		int idade = in.nextInt();
		c.setIdade(idade);

		clientes.add(c);

	}

	public void listaClientes() {
		for (Cliente cl : clientes) {
			System.out.println("Nome: " + cl.getNome());
			System.out.println("CPF: " + cl.getCpf());
			System.out.println("Idade: " + cl.getIdade());
		}
	}

	public void cadastraVeiculo() {
		Scanner in = new Scanner(System.in);

		int ql_veiculo;
		System.out.print("Deseja cadastrar carro(1) ou moto(2)?");
		ql_veiculo = in.nextInt();

		if (ql_veiculo == 1) {
			Veiculo carro = new Carro();

			carro.setTipoVeiculo("Carro");

			System.out.println("Valor da locação: ");
			double valor = in.nextDouble();
			carro.setValorLocacao(valor);

			Scanner in2 = new Scanner(System.in);
			System.out.println("Descrição do veículo: ");
			String descricao = in2.nextLine();
			carro.setDescricaoVeiculo(descricao);

			System.out.println("Placa do veículo: ");
			String placa = in2.nextLine();
			carro.setPlacaVeiculo(placa);

			System.out.println("Numero de passageiros: ");
			int passageiros = in2.nextInt();
			((Carro) carro).setNumeroPassageiros(passageiros);
			veiculos.add((Carro) carro);
			disponiveis.add((Carro) carro);

		} else if (ql_veiculo == 2) {
			Veiculo moto = new Moto();

			moto.setTipoVeiculo("Moto");

			System.out.println("Valor da locação: ");
			double valor = in.nextDouble();
			moto.setValorLocacao(valor);

			Scanner in1 = new Scanner(System.in);
			System.out.println("Descrição do veículo: ");
			String descricao = in1.nextLine();
			moto.setDescricaoVeiculo(descricao);

			System.out.println("Placa do veículo: ");
			String placa = in1.nextLine();
			moto.setPlacaVeiculo(placa);

			System.out.println("Possui partida eletrica? (1-sim) (2-nao) ");
			int choice = in1.nextInt();
			if (choice == 1) {
				((Moto) moto).setPartidaEletrica();
			}
			veiculos.add((Moto) moto);
			disponiveis.add((Moto) moto);

		} else {
			System.out.println("Opção Inválida!");
		}
	}

	public void listaVeiculos() {
		for (Veiculo k : veiculos) {
			if (k.getTipoVeiculo().equals("Carro")) {
				k = (Carro) k;
				System.out.println("-----Carros-----");
				System.out.println("Informações sobre o carro: " + k.getDescricaoVeiculo());
				System.out.println("Valor pela diaria do carro: " + k.getValorLocacao());
				System.out.println("Placa do carro: " + k.getPlacaVeiculo());
				System.out.println("Numero de passageiros: " + k.getNumeroPassageiros());
			} else {
				k = (Moto) k;
				System.out.println("-----Motos-----");

				System.out.println("Informações sobre o moto: " + k.getDescricaoVeiculo());
				System.out.println("Valor pela diaria do moto: " + k.getValorLocacao());
				System.out.println("Placa do moto: " + k.getPlacaVeiculo());
				if (k.isPartidaEletrica()) {
					System.out.println("Possui partida eletrica");
				} else {
					System.out.println("Não possui partida eletrica");
				}
			}

		}

	}

	public void locarVeiculo() {
		Scanner in = new Scanner(System.in);
		System.out.println("Informe seu cpf: ");
		String validar = in.nextLine();
		for (Cliente g : clientes) {
			if (g.getCpf().equals(validar)) {
				System.out.println("Informe a placa: ");
				String validaVeiculo = in.nextLine();
				for (Veiculo v : veiculos) {
					if (v.getPlacaVeiculo().equals(validaVeiculo)) {
						System.out.println(v.getDescricaoVeiculo());
						System.out.println("Quantos dias deseja locar? ");
						int dias = in.nextInt();
						// Limpando o buffer do teclado (não sei fazer de outro
						// jeito ainda)
						Scanner in1 = new Scanner(System.in);
						System.out.println("Data de locacao: ");
						String data = in1.nextLine();
						System.out.println("Deseja adicionar seguro? (1-sim) (2-nao)");
						int seguro = in1.nextInt();
						System.out.println("Cliente: " + g.getNome());
						System.out.println("Numero de dias da locacao: " + dias);
						System.out.println("Data da locação: " + data);
						if (seguro == 1) {
							if (v.getTipoVeiculo().equals("Carro")) {
								double valorSeguro = 0.05 * v.getValorLocacao() * (1 + (v.getNumeroPassageiros() / 20));
								double desconto = ((v.getValorLocacao() * dias) + valorSeguro) * 0.12;
								System.out.println("O desconto maximo é de: " + desconto);
								total = (v.getValorLocacao() * dias) + valorSeguro - desconto;

							} else {
								double valorSeguro = 0.09 * v.getValorLocacao();
								double desconto = ((v.getValorLocacao() * dias) + valorSeguro) * 0.12;
								System.out.println("O desconto maximo é de: " + desconto);
								total = (v.getValorLocacao() * dias) + valorSeguro - desconto;

							}

						} else if (seguro == 2) {
							double desconto = (v.getValorLocacao() * dias) * 0.12;
							System.out.println("O desconto maximo é de: " + desconto);
							total = (v.getValorLocacao() * dias) - desconto;

						} else {
							System.out.println("Opcao Invalida");
						}

						System.out.println("Deseja confirmar a locação? (1-sim) (2-nao)");
						int escolha = in.nextInt();
						if (escolha == 1) {
							System.out.println("Valor total a pagar R$" + total + "\nObrigado por locar aqui. Volte sempre!");
							locados.add(v);
							disponiveis.remove(v);
						}

					}
				}

			} else {
				System.out.println("Cliente não cadastrado");
			}

		}

	}

	public void listaLocados() {
		for (Veiculo y : locados) {
			System.out.println("Informacao: " + y.getDescricaoVeiculo());
			System.out.println("Placa: " + y.getPlacaVeiculo());
		}

	}

	public void listaDisponiveis() {
		for (Veiculo d : disponiveis) {
			System.out.println("Informacao: " + d.getDescricaoVeiculo());
			System.out.println("Placa: " + d.getPlacaVeiculo());

		}

	}

}
