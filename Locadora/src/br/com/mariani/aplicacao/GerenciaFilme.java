package br.com.mariani.aplicacao;

import br.com.mariani.controller.ControleFilme;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class GerenciaFilme {
      private static final Scanner ENTRADA = new Scanner(System.in);
    private static Integer MENU = 0;

    
    public void menuFilme() throws SQLException, ParseException{
          do {
            try {
                System.out.println("---------------MENU---------------"
                        + "\n1 Cad Novo Filme: "
                        + "\n2 Filmes Cadastrados: "
                        + "\n3 Buscar Filme pelo Id: "
                        + "\n4 Atualizar Filme: "
                        + "\n5 Deletar Filme: "
                        + "\n6 Voltar: "
                        + "\n----------------------------------\n");
                MENU = ENTRADA.nextInt();

            } catch (Exception e) {
                MENU = null;
            }
            if (MENU != null) {
                switch (MENU) {
                    case 1:
                        ControleFilme.addFilmeNovo();
                        break;
                    case 2:
                        ControleFilme.listarFilmes();
                        break;
                    case 3:
                        ControleFilme.buscarFilme();
                        break;
                    case 4:
                        ControleFilme.atualizarFilme();
                        break;
                    case 5:
                        ControleFilme.deletarFilme();
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
