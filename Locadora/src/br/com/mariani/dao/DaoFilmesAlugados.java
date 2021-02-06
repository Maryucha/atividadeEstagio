package br.com.mariani.dao;

import br.com.mariani.connection.ConnectionFactory;
import br.com.mariani.models.Aluguel;
import br.com.mariani.models.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author maryucha
 */
public class DaoFilmesAlugados {

    private final Connection con = ConnectionFactory.getConnection();

    /*----------------------------------------------------------*/
     public void addLocacao(Filme filme,Aluguel aluguel) throws SQLException {
        String sql = "insert into re_filme_aluguel (id_filme, id_aluguel) values(?,?)";

        PreparedStatement stm = null;

         try {
             stm = con.prepareStatement(sql);
             stm.setInt(1, filme.getId());
             stm.setInt(2, aluguel.getId());
             stm.executeUpdate();
             
         } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco. " + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }
}
