package LocadoraVeiculo.dao;

import LocadoraVeiculo.Cliente;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import java.util.List;


/**
 * Created by amazingd on 13/11/16.
 */
public class ClienteDAO {
    EntityManager manager = new JPAUtil().getEntityManager();

    public void criaCliente() {
        Cliente c = new Cliente();
        String age;
        c.setNome(JOptionPane.showInputDialog("Nome do Cliente: "));
        c.setCpf(JOptionPane.showInputDialog("CPF: "));
        while (true) {
            age = JOptionPane.showInputDialog("Idade: ");
            try {
                c.setIdade(Integer.parseInt(age));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            if (c.getIdade() > 0) {
                break;
            }
        }
        manager.getTransaction().begin();
        manager.persist(c);
        manager.getTransaction().commit();
        manager.close();
    }

    public void listaClientes(){
        manager.getTransaction().begin();
        Query query = manager.createQuery("select c from Cliente c");
        List<Cliente> clientes = query.getResultList();

        for (Cliente cl :
                clientes) {
            JOptionPane.showMessageDialog(null,
                    "Nome: " + cl.getNome() + "\n" +
                    "CPF: " + cl.getCpf() + "\n" +
                    "Idade: " + cl.getIdade() + "\n");
        }
        manager.close();
    }
}
