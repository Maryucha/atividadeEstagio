package br.com.mariani.controller;

import br.com.mariani.dao.DaoAluguel;
import br.com.mariani.dao.DaoCliente;
import br.com.mariani.models.Aluguel;
import br.com.mariani.models.Cliente;
import br.com.mariani.util.ConversorData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author maryucha
 */
public class ControleAluguel {

    private static Scanner ENTRADA = new Scanner(System.in);
    private static Set<Aluguel> conjuntoAluguel = new HashSet<>();
    static SimpleDateFormat formata = new SimpleDateFormat("yyyy/MM/dd");

    /*----------------------------------------------------------*/
    public static Aluguel addNovaLocacao() throws ParseException, SQLException {
        DaoAluguel dao = new DaoAluguel();
        DaoCliente dCliente = new DaoCliente();
        Aluguel aluguel = new Aluguel();

        System.out.print("Digite o id do cliente: ");
        Cliente clienteAluguel = dCliente.buscarCliente(ENTRADA.nextInt());
        aluguel.setClienteId(clienteAluguel.getId());
        ENTRADA.nextLine();

        System.out.print("Digite a data do aluguel: ");
        String dataLocacao = ENTRADA.nextLine();
        Date dataFormatada = formata.parse(dataLocacao);
        aluguel.setDataAluguel(ConversorData.converterUtilToSql(dataFormatada));

        System.out.print("Digite o valor da Locação: ");
        aluguel.setValor(ENTRADA.nextDouble());

        dao.addLocacao(clienteAluguel.getId(), aluguel);
        conjuntoAluguel.add(aluguel);
        return aluguel;

    }

    /*----------------------------------------------------------*/
    public static void listarLocacoes() throws SQLException {
        DaoAluguel dao = new DaoAluguel();
        DaoCliente dCliente = new DaoCliente();
        Aluguel aluguel = new Aluguel();

        conjuntoAluguel = dao.listarLocacao();
        for (int i = 0; i < conjuntoAluguel.size(); i++) {
            conjuntoAluguel.iterator().next().imprimeAluguel();
        }
    }

    /*----------------------------------------------------------*/
    public static Aluguel buscarLocacao() throws SQLException {
        DaoAluguel dao = new DaoAluguel();
        
        System.out.println("Digite o ID da Locação: ");
        Aluguel aluguel = dao.buscarAluguel(ENTRADA.nextInt());
        ENTRADA.nextLine();
        return aluguel;
    }

    /*----------------------------------------------------------*/
    public static void entregarLocacao() throws SQLException {
        DaoAluguel dao = new DaoAluguel();
       Aluguel aluguel = buscarLocacao();
        
        if(aluguel!=null){
            conjuntoAluguel.remove(aluguel);
            dao.removerAluguel(aluguel.getId());
             System.out.println("Devolução feita.");
        }else{
            System.out.println("Locação não existe.");
        }

    }

    /*----------------------------------------------------------*/
    public static void gerarMulta() {
        /*
            se a data atual for maior que 7 dias gerar multa de 3,00
        */
    }
    /*----------------------------------------------------------*/
}
