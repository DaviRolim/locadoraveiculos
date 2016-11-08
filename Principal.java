package LocadoraVeiculo;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Locadora locadora = new Locadora();

		while (true) {
			Scanner in = new Scanner(System.in);
			int op;

			System.out.println("--------LOCADORA DE VEICULOS--------");
			System.out.println("1- Cadastrar Cliente");
			System.out.println("2- Cadastrar Veículo");
			System.out.println("3- Locar Veículo");
			System.out.println("4- Listar Veículos");
			System.out.println("5- Listar Clientes");
			System.out.println("6- Listar Locações");
			System.out.println("7- Listar Veículos Disponíveis");
			System.out.print("Opcão: ");
			op = in.nextInt();

			switch (op) {
			case 1:
				locadora.cadastraCliente();
				break;

			case 2:
				locadora.cadastraVeiculo();
				break;

			case 3:
				locadora.locarVeiculo();
				break;

			case 4:
				locadora.listaVeiculos();
				break;

			case 5:
				locadora.listaClientes();
				break;

			case 6:
				locadora.listaLocados();
				break;

			case 7:
				locadora.listaDisponiveis();
				break;

			default:
				System.out.println("Opção Inválida!");
				break;

			}

		}

	}

}
