package LocadoraVeiculo;

import LocadoraVeiculo.dao.ClienteDAO;
import LocadoraVeiculo.dao.VeiculoDAO;

import javax.swing.*;
//import java.util.Scanner;

public class Locadora {

    public void cadastraCliente() {

        ClienteDAO c = new ClienteDAO();
        c.criaCliente();

    }

    public void listaClientes() {
        ClienteDAO c = new ClienteDAO();
        c.listaClientes();
    }

    public void cadastraVeiculo() {
        String tipo;
        int ql_veiculo = 0;

        while (true) {
            tipo = JOptionPane.showInputDialog("Cadastrar carro(1) ou moto(2)?");
            try {
                ql_veiculo = Integer.parseInt(tipo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            if (ql_veiculo != 0) {
                break;
            }
        }
        if (ql_veiculo == 1) {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.createCarro();

        } else if (ql_veiculo == 2) {
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.createMoto();

        } else {
            JOptionPane.showMessageDialog(null, "Opção inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listaVeiculos() {

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.listaVeiculos();

    }
//	}

    public void locarVeiculo() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.loca();
    }

    public void listaLocados() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.listaDisponiveisOuLocados(true);
    }


    public void listaDisponiveis () {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.listaDisponiveisOuLocados(false);
    }

}
