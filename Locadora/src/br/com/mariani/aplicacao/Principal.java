package br.com.mariani.aplicacao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class Principal {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static Integer MENU = 0;
    private static GerenciaCliente GCLIENTE = new GerenciaCliente();
    private static GerenciaFilme GFILME = new GerenciaFilme();

    public static void main(String[] args) throws SQLException, ParseException {

        do {
            try {
                System.out.println("--------------- MENU PRINCIPAL---------------"
                        + "\n1 Clientes: "
                        + "\n2 Filmes: "
                        + "\n3 Alugueis: "
                        + "\n4 SAIR: "
                        + "\n----------------------------------\n");
                MENU = ENTRADA.nextInt();

            } catch (Exception e) {
                MENU = null;
            }
            if (MENU != null) {
                switch (MENU) {
                    case 1:
                        GCLIENTE.menuCliente();
                        break;
                    case 2:
                       GFILME.menuFilme();
                        break;
                    case 3:
                        
                        break;
                    case 4:
                       System.out.println("Até mais!");
                        break;
                    default:
                        System.out.println("Escolha uma opção válida!");
                        break;
                }
            }
        } while (MENU != 4);

        System.out.println("---------------------------------------");
    }
}
