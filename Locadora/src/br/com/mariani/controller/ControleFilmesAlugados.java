package br.com.mariani.controller;

import br.com.mariani.dao.DaoAluguel;
import br.com.mariani.dao.DaoFilme;
import br.com.mariani.dao.DaoFilmesAlugados;
import br.com.mariani.models.Aluguel;
import br.com.mariani.models.Filme;
import br.com.mariani.models.FilmesAlugados;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class ControleFilmesAlugados {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static List filmesAlugados = new ArrayList<>();
    /*----------------------------------------------------------*/
    public static void addNobanco() throws SQLException{
      DaoAluguel dAluguel = new DaoAluguel();
      DaoFilme dFilme = new DaoFilme();
      DaoFilmesAlugados dao = new DaoFilmesAlugados();
      FilmesAlugados filmeAlugado = new FilmesAlugados();
      
      Aluguel aluguel = dAluguel.buscarAluguel(ENTRADA.nextInt());
      filmeAlugado.setIdAluguel(aluguel.getId());
      
      
      Filme filme = dFilme.buscarFilme(ENTRADA.nextInt());
      filmeAlugado.setIdFilme(filme.getId());
      
      dao.addLocacao(filme,aluguel);
      filmesAlugados.add(filmeAlugado);
    }
}
