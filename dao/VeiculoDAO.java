package LocadoraVeiculo.dao;

import LocadoraVeiculo.Carro;
import LocadoraVeiculo.Cliente;
import LocadoraVeiculo.Moto;
import LocadoraVeiculo.TipoVeiculo;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import java.util.List;

/**
 * Created by amazingd on 13/11/16.
 */
public class VeiculoDAO {
    EntityManager manager = new JPAUtil().getEntityManager();

    public void createMoto() {
        manager.getTransaction().begin();
        Moto moto = new Moto();
        String value, partidaEletrica;
        int choice = 0;

        moto.setTipoVeiculo(TipoVeiculo.Moto);

        while (true) {
            value = JOptionPane.showInputDialog("Valor de locação referente a um dia:");
            try {
                moto.setValorLocacao(Double.parseDouble(value));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }

            if (moto.getValorLocacao() != 0) {
                break;
            }
        }

        moto.setDescricaoVeiculo(JOptionPane.showInputDialog("Descricão do veículo:"));

        moto.setPlacaVeiculo(JOptionPane.showInputDialog("Placa:"));

        while (true) {
            partidaEletrica = JOptionPane.showInputDialog("Partida elétrica? (1- SIM/ 2- NãO)");
            try {
                choice = Integer.parseInt(partidaEletrica);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }

            if (choice != 0) {
                break;
            }
        }

        if (choice == 1) {
            moto.setPartidaEletrica();
        }

        manager.persist(moto);
        manager.getTransaction().commit();
        manager.close();
    }

    public void createCarro() {
        manager.getTransaction().begin();
        Carro carro = new Carro();
        carro.setTipoVeiculo(TipoVeiculo.Carro);


        while (true) {
            String value = JOptionPane.showInputDialog("Valor de locação referente a um dia:");
            try {
                carro.setValorLocacao(Double.parseDouble(value));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }

            if (carro.getValorLocacao() != 0) {
                break;
            }
        }


        carro.setDescricaoVeiculo(JOptionPane.showInputDialog("Descrição do veículo:"));

        carro.setPlacaVeiculo(JOptionPane.showInputDialog("Placa:"));

        String n_passageiros;


        while (true) {
            n_passageiros = JOptionPane.showInputDialog("Número de passageiros: ");
            try {
                carro.setNumeroPassageiros(Integer.parseInt(n_passageiros));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }

            if (carro.getNumeroPassageiros() != 0) {
                break;
            }
        }
        manager.persist(carro);
        manager.getTransaction().commit();
        manager.close();
    }

    public void listaVeiculos() {
        manager.getTransaction().begin();

        // Pegar uma lista com todos os carros
        Query query = manager.createQuery("select c from Carro c");
        List<Carro> carros = query.getResultList();

        // Pegar uma lista com todas as motos
        Query query1 = manager.createQuery("select m from Moto m");
        List<Moto> motos = query1.getResultList();

        for (Carro c :
                carros) {
            JOptionPane.showMessageDialog(null,
                    "CARRO\n" +
                            "Descrição: " + c.getDescricaoVeiculo() + "\n" +
                            "Diária: " + c.getValorLocacao() + "\n" +
                            "Placa: " + c.getPlacaVeiculo() + "\n" +
                            "Passageiros: " + c.getNumeroPassageiros() + "\n\n");
        }

        for (Moto m :
                motos) {
            if (m.isPartidaEletrica()) {
                JOptionPane.showMessageDialog(null,
                        "MOTO\n" +
                                "Descrição: " + m.getDescricaoVeiculo() + "\n" +
                                "Diária: " + m.getValorLocacao() + "\n" +
                                "Placa: " + m.getPlacaVeiculo() + "\n" +
                                "Possui partida elétrica\n\n");
            } else {
                JOptionPane.showMessageDialog(null,
                        "MOTO\n" +
                                "Descrição: " + m.getDescricaoVeiculo() + "\n" +
                                "Diária: " + m.getValorLocacao() + "\n" +
                                "Placa: " + m.getPlacaVeiculo() + "\n" +
                                "Não possui partida elétrica\n\n");
            }
        }
    }

    public void listaDisponiveisOuLocados(boolean param) {
        manager.getTransaction().begin();

        Query query = manager.createQuery("select c from Carro c where c.locado=:cloc");
        Query query1 = manager.createQuery("select m from Moto m where m.locado=:mloc");

        query.setParameter("cloc", param);
        query1.setParameter("mloc", param);

        List<Carro> carros = query.getResultList();
        List<Moto> motos = query1.getResultList();

        for (Carro y : carros) {
            JOptionPane.showMessageDialog(null,
                    "Informação: " + y.getDescricaoVeiculo() + "\n" +
                            "Placa: " + y.getPlacaVeiculo());
        }
        for (Moto z : motos) {
            JOptionPane.showMessageDialog(null,
                    "Informação: " + z.getDescricaoVeiculo() + "\n" +
                            "Placa: " + z.getPlacaVeiculo());

        }
        manager.close();
    }

    public void loca() {
        String validar = JOptionPane.showInputDialog("CPF: ");

        manager.getTransaction().begin();
        Query query = manager.createQuery("select c from Cliente c where c.cpf=:val");
        query.setParameter("val", validar);

        try {
            Cliente cliente = (Cliente) query.getSingleResult();
            System.out.println(cliente.getNome());


            String tipo;
            int ql_veiculo = 0;

            while (true) {
                tipo = JOptionPane.showInputDialog("Locar carro(1) ou moto(2)?");
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
                String validaVeiculo = JOptionPane.showInputDialog("Placa: ");
                Query query1 = manager.createQuery("select c from Carro c where c.placa=:validaC");
                query1.setParameter("validaC", validaVeiculo);

                try {
                    Carro veic = (Carro) query1.getSingleResult();
                    System.out.println(veic.getDescricaoVeiculo());
                    int dias = 0;
                    double total = 0;
                    while (true) {
                        String Dias = JOptionPane.showInputDialog("Número de dias da locaçao: ");
                        try {
                            dias = Integer.parseInt(Dias);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }

                        if (dias != 0) {
                            break;
                        }

                    }
                    String data = JOptionPane.showInputDialog("Data da locação: ");
                    int seguro = 0;
                    while (true) {
                        String Seguro = JOptionPane.showInputDialog("Seguro? (1 - SIM/ 2 - NÃO)");
                        try {
                            seguro = Integer.parseInt(Seguro);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }

                        if (seguro != 0) {
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null,
                            "Cliente: " + cliente.getNome() + "\n" +
                                    "Numero de dias da locacao: " + dias + "\n" +
                                    "Data da locação: " + data);

                    if (seguro == 1) {
                        double valorSeguro = 0.05 * veic.getValorLocacao() * (1 + (veic.getNumeroPassageiros() / 20));
                        double desconto = ((veic.getValorLocacao() * dias) + valorSeguro) * 0.12;
                        JOptionPane.showMessageDialog(null, "Desconto máximo é de R$" + desconto);
                        total = (veic.getValorLocacao() * dias) + valorSeguro - desconto;
                    } else if (seguro == 2) {
                        double desconto = (veic.getValorLocacao() * dias) * 0.12;
                        JOptionPane.showMessageDialog(null, "Desconto máximo é de R$" + desconto);
                        total = (veic.getValorLocacao() * dias) - desconto;

                    } else {
                        JOptionPane.showMessageDialog(null, "Opção Inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                    int escolha = 0;
                    while (true) {
                        String choice = JOptionPane.showInputDialog("Confirmar a locação? (1 - SIM/ 2 - NÃO)");

                        try {
                            escolha = Integer.parseInt(choice);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }

                        if (escolha != 0) {
                            break;
                        }
                    }


                    if (escolha == 1) {
                        JOptionPane.showMessageDialog(null,
                                "Valor total a pagar R$" + total +
                                        "\nObrigado por locar aqui. Volte sempre!");
                    }
                    veic.setLocado(true);
                    manager.getTransaction().commit();
                    manager.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Não existe nenhum carro com essa placa", "ERRO", JOptionPane.ERROR_MESSAGE);
                    manager.close();
                }
            } else if (ql_veiculo == 2) {
                String validaVeiculo = JOptionPane.showInputDialog("Placa: ");
                Query query2 = manager.createQuery("select m from Moto m where m.placa=:validaM");
                query2.setParameter("validaM", validaVeiculo);

                try {
                    Moto veic = (Moto) query2.getSingleResult();
//                        veic.setLocado(true);
                    System.out.println(veic.getDescricaoVeiculo());
                    int dias = 0;
                    double total = 0;
                    while (true) {
                        String Dias = JOptionPane.showInputDialog("Número de dias da locaçao: ");
                        try {
                            dias = Integer.parseInt(Dias);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }

                        if (dias != 0) {
                            break;
                        }

                    }
                    String data = JOptionPane.showInputDialog("Data da locação: ");
                    int seguro = 0;
                    while (true) {
                        String Seguro = JOptionPane.showInputDialog("Seguro? (1 - SIM/ 2 - NÃO)");
                        try {
                            seguro = Integer.parseInt(Seguro);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }

                        if (seguro != 0) {
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null,
                            "Cliente: " + cliente.getNome() + "\n" +
                                    "Numero de dias da locacao: " + dias + "\n" +
                                    "Data da locação: " + data);

                    if (seguro == 1) {
                        double valorSeguro = 0.09 * veic.getValorLocacao();
                        double desconto = ((veic.getValorLocacao() * dias) + valorSeguro) * 0.12;
                        JOptionPane.showMessageDialog(null, "Desconto m�ximo é de R$" + desconto);
                        total = (veic.getValorLocacao() * dias) + valorSeguro - desconto;
                    } else if (seguro == 2) {
                        double desconto = (veic.getValorLocacao() * dias) * 0.12;
                        JOptionPane.showMessageDialog(null, "Desconto máximo é de R$" + desconto);
                        total = (veic.getValorLocacao() * dias) - desconto;

                    } else {
                        JOptionPane.showMessageDialog(null, "Opção Inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                    int escolha = 0;
                    while (true) {
                        String choice = JOptionPane.showInputDialog("Confirmar a locação? (1 - SIM/ 2 - NÃO)");

                        try {
                            escolha = Integer.parseInt(choice);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Informação inválida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }

                        if (escolha != 0) {
                            break;
                        }
                    }


                    if (escolha == 1) {
                        JOptionPane.showMessageDialog(null,
                                "Valor total a pagar R$" + total +
                                        "\nObrigado por locar aqui. Volte sempre!");
                    }
                    veic.setLocado(true);
                    manager.getTransaction().commit();
                    manager.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Não existe nenhuma moto com essa placa", "ERRO", JOptionPane.ERROR_MESSAGE);
                    manager.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            // Fazer as outras validações e no final tratar veic.setLocado(true); (depois da confirmacao)!


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "CPF não existe no banco de dados", "ERRO", JOptionPane.ERROR_MESSAGE);
            manager.close();
        }
    }

}

