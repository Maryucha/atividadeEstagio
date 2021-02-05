package br.com.mariani.aplicacao;

import br.com.mariani.controller.ControleCliente;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class GerenciaCliente {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static Integer MENU = 0;

    
    public void menuCliente() throws SQLException{
          do {
            try {
                System.out.println("---------------MENU---------------"
                        + "\n1 Cad Novo Cliente: "
                        + "\n2 Clientes Cadastrados: "
                        + "\n3 Buscar cliente: "
                        + "\n4 Atualizar cliente: "
                        + "\n5 Deletar cliente: "
                        + "\n6 Voltar: "
                        + "\n----------------------------------\n");
                MENU = ENTRADA.nextInt();

            } catch (Exception e) {
                MENU = null;
            }
            if (MENU != null) {
                switch (MENU) {
                    case 1:
                        ControleCliente.addClienteNovo();
                        break;
                    case 2:
                        ControleCliente.listarClientes();
                        break;
                    case 3:
                        ControleCliente.buscarCliente();
                        break;
                    case 4:
                        ControleCliente.atualizarCliente();
                        break;
                    case 5:
                        ControleCliente.deletarCliente();
                        break;    
                    case 6:
                        System.out.println("Voltar ao menu!");
                        break;    
                    default:
                        System.out.println("Escolha uma opção válida!");
                        break;
                }
            }
        }while(MENU!=6);

        System.out.println("---------------------------------------");
    }

}
