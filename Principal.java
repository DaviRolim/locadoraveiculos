package LocadoraVeiculo;
import javax.swing.JOptionPane;


public class Principal {

	public static void main(String[] args) {
		Locadora locadora = new Locadora();

		while (true) {
			int op = 0;
			String firstOp;
			
			firstOp = JOptionPane.showInputDialog(
					"Selecione uma opção: \n\n" +
					"1 - Cadastrar Cliente\n" +
					"2 - Cadastrar Veículo\n" +
					"3 - Locar Veículo\n" +
					"4 - Listar Veículos \n" +
					"5 - Listar Clientes\n" +
					"6 - Listar Locações\n" +
					"7 - Listar Veículos Disponíveis\n" +
					"8 - Sair");
			try{
				op = Integer.parseInt(firstOp);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Opção inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}

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
				
			case 8:
				System.exit(0);

			default:
				if(op != 0){
					JOptionPane.showMessageDialog(null, op + " É uma opção inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}