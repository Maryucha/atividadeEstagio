package br.com.mariani.controller;

import br.com.mariani.dao.DaoFilme;
import br.com.mariani.models.Filme;
import br.com.mariani.util.ConversorData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class ControleFilme {

    private static List<Filme> listaFilmes = new ArrayList<>();
    private static final Scanner ENTRADA = new Scanner(System.in);
    static SimpleDateFormat formata = new SimpleDateFormat("yyyy/MM/dd"); 

    /*----------------------------------------------------------*/
    public static Filme addFilmeNovo() throws SQLException, ParseException {
        DaoFilme dao = new DaoFilme();
        Filme filmeNovo = new Filme();
        
        System.out.print("Digite a Data de Lançamento: ");
        String dataEntrada = ENTRADA.nextLine();
       
        
        Date dataFormatada = formata.parse(dataEntrada);       
        filmeNovo.setDataLancamento(ConversorData.converterUtilToSql(dataFormatada));
        
        
        System.out.print("Digite o nome do filme: ");
        filmeNovo.setNome(ENTRADA.nextLine());
        
        System.out.print("Descrição do Filme: ");
        filmeNovo.setDescricao(ENTRADA.nextLine());
        
        dao.addFilmeNoBanco(filmeNovo);
        listaFilmes.add(filmeNovo);
        return filmeNovo;
    }

    /*----------------------------------------------------------*/
    public static void listarFilmes() throws SQLException {
        DaoFilme dao = new DaoFilme();
        listaFilmes=dao.listarFilmes();
         for(int i=0;i<listaFilmes.size();i++){
           listaFilmes.get(i).imprimeFilme();
       }
    }

    /*----------------------------------------------------------*/
    public static Filme buscarFilme() throws SQLException {
        DaoFilme dao = new DaoFilme();
        System.out.print("Digite o id do Filme: ");
        Filme filmeBuscado = dao.buscarFilme(ENTRADA.nextInt());
        ENTRADA.nextLine();
        System.out.println("NOME [" + filmeBuscado.getNome() + "]");
        return filmeBuscado;
    }

    /*----------------------------------------------------------*/
    public static Filme atualizarFilme() throws ParseException, SQLException {
       DaoFilme dao = new DaoFilme();
              
       Filme filmeBuscado = buscarFilme();
       
        System.out.print("Digite a Data de Lançamento: ");
        String dataEntrada = ENTRADA.nextLine();
        Date dataFormatada = formata.parse(dataEntrada);       
        filmeBuscado.setDataLancamento(ConversorData.converterUtilToSql(dataFormatada));
        
        System.out.print("Digite o nome do filme: ");
        filmeBuscado.setNome(ENTRADA.nextLine());
        
        System.out.print("Descrição do Filme: ");
        filmeBuscado.setDescricao(ENTRADA.nextLine());
        dao.atualizarFilme(filmeBuscado.getId(), filmeBuscado);
        System.out.println("Filme atualizado.");
        return filmeBuscado;
        
    }

    /*----------------------------------------------------------*/
    public static Filme deletarFilme() throws SQLException {
        DaoFilme dao = new DaoFilme();
              
       Filme filmeBuscado = buscarFilme();
       dao.excluirDoBanco(filmeBuscado.getId());
       return null;
    }
    /*----------------------------------------------------------*/
}
