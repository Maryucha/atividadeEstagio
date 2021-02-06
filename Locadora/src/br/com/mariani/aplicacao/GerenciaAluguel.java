package br.com.mariani.aplicacao;


import br.com.mariani.controller.ControleAluguel;
import br.com.mariani.controller.ControleFilmesAlugados;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class GerenciaAluguel {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static Integer MENU = 0;

    public void menuAluguel() throws ParseException, SQLException {
        do {
            try {
                System.out.println("---------------MENU---------------"
                        + "\n1 Novo aluguel: "
                        + "\n2 Lista de Locações: "
                        + "\n3 Buscar Locação: "
                        + "\n4 Entrega de Filme: "
                        + "\n5 Gerar multa: "
                        + "\n6 Voltar: "
                        + "\n----------------------------------\n");
                MENU = ENTRADA.nextInt();
            } catch (Exception e) {
                MENU = null;
            }
            if (MENU != null) {
                 switch (MENU) {
                    case 1:
                        ControleAluguel.addNovaLocacao();
                        break;
                    case 2:
                        ControleAluguel.listarLocacoes();
                        break;
                    case 3:
                        ControleAluguel.buscarLocacao();
                        break;
                    case 4:
                        ControleAluguel.entregarLocacao(ControleAluguel.buscarLocacao());
                        break;
                    case 5:
                        
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
