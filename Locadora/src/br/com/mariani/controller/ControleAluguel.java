package br.com.mariani.controller;

import br.com.mariani.dao.DaoAluguel;
import br.com.mariani.dao.DaoCliente;
import br.com.mariani.dao.DaoFilme;
import br.com.mariani.models.Aluguel;
import br.com.mariani.models.Cliente;
import br.com.mariani.models.Filme;
import br.com.mariani.util.ConversorData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class ControleAluguel {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static List<Aluguel> conjuntoAluguel = new ArrayList<>();
    static SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");

    /*----------------------------------------------------------*/
    public static Aluguel addNovaLocacao() throws ParseException, SQLException {
        DaoAluguel dao = new DaoAluguel();
        DaoCliente dCliente = new DaoCliente();
        DaoFilme dFilme = new DaoFilme();
        
        Aluguel aluguel = new Aluguel();

        System.out.print("Digite o id do cliente: ");
        Cliente clienteAluguel = dCliente.buscarCliente(ENTRADA.nextInt());
        aluguel.setClienteId(clienteAluguel.getId());
        ENTRADA.nextLine();

        ControleFilme.listarFilmes();
        System.out.print("Digite o cód do filme que deseja Locar: ");
        Filme filme = dFilme.buscarFilme(ENTRADA.nextInt());
        ENTRADA.nextLine();

        System.out.print("Digite a data do aluguel: ");
        String dataLocacao = ENTRADA.nextLine();
        Date dataFormatada = formata.parse(dataLocacao);
        aluguel.setDataAluguel(ConversorData.converterUtilToSql(dataFormatada));
        ENTRADA.nextLine();

        System.out.print("Digite o valor da Locação: ");
        aluguel.setValor(ENTRADA.nextDouble());
        ENTRADA.nextLine();
        
        conjuntoAluguel.add(aluguel);
        ControleFilmesAlugados.addNobanco();
        dao.addLocacao(clienteAluguel.getId(), aluguel);
        
        return aluguel;

    }

    /*----------------------------------------------------------*/
    public static void listarLocacoes() throws SQLException {
        DaoAluguel dao = new DaoAluguel();

        conjuntoAluguel = dao.listarLocacao();
        for (int i = 0; i < conjuntoAluguel.size(); i++) {
            conjuntoAluguel.get(i).imprimeAluguel();
        }
    }

    /*----------------------------------------------------------*/
    public static Aluguel buscarLocacao() throws SQLException {
        DaoAluguel dao = new DaoAluguel();

        System.out.println("Digite o ID da Locação: ");
        Aluguel aluguel = dao.buscarAluguel(ENTRADA.nextInt());
        ENTRADA.nextLine();
        System.out.println("O ID DA LOCAÇÃO É [ " + aluguel.getId() + "]");
        return aluguel;
    }

    /*----------------------------------------------------------*/
    public static Aluguel entregarLocacao(Aluguel aluguel) throws SQLException {
        DaoAluguel dao = new DaoAluguel();
        Calendar c = Calendar.getInstance();

        if (aluguel != null) {
            if (aluguel.getDataAluguel().getDay() < c.get(Calendar.DAY_OF_MONTH)) {
                conjuntoAluguel.remove(aluguel);
                dao.removerAluguel(aluguel.getId());
                System.out.println("Devolução feita.");
                return aluguel;
            } else {
                gerarMulta(aluguel);
                conjuntoAluguel.remove(aluguel);
                dao.removerAluguel(aluguel.getId());
                System.out.println("Devolução feita.");
                return aluguel;
            }

        } else {
            System.out.println("Locação não existe.");
            return null;
        }

    }

    /*----------------------------------------------------------*/
    public static Double gerarMulta(Aluguel aluquel) throws SQLException {

        Calendar c = Calendar.getInstance();
        System.out.println("Sua devolução tem uma multa de R$3,00");
        double vlrAluguel = aluquel.getValor();
        System.out.println("Você deve pagar R$" + (vlrAluguel + 3));

        if (aluquel.getDataAluguel().getDay() > c.get(Calendar.DAY_OF_MONTH) + 5) {
            aluquel.setValor(aluquel.getValor() + 3);
        }
        return aluquel.getValor();
    }
    /*----------------------------------------------------------*/
}
