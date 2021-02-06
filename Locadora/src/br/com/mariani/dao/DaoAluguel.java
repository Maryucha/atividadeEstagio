package br.com.mariani.dao;

import br.com.mariani.connection.ConnectionFactory;
import br.com.mariani.models.Aluguel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author maryucha
 */
public class DaoAluguel {

    private final Connection con = ConnectionFactory.getConnection();

    /*----------------------------------------------------------*/
    public void addLocacao(int id, Aluguel aluguel) throws SQLException {
        String sql = "insert into en_aluguel (id_cliente,data_aluguel,valor) values(?,?,?)";

        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, aluguel.getClienteId());
            stm.setDate(2, aluguel.getDataAluguel());
            stm.setDouble(3, aluguel.getValor());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco. " + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    /*----------------------------------------------------------*/
    public Set<Aluguel> listarLocacao() throws SQLException {
        String sql = "select * from en_aluguel";
        Set<Aluguel> list = new HashSet<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setId(rs.getInt("id"));
                aluguel.setClienteId(rs.getInt("id_cliente"));
                aluguel.setDataAluguel(rs.getDate("data_aluguel"));
                aluguel.setValor(rs.getDouble("valor"));
                list.add(aluguel);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar do banco. " + e);
            return null;
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return list;
    }

    /*----------------------------------------------------------*/
    public Aluguel buscarAluguel(int id) throws SQLException {
        String sql = "Select * from en_aluguel where id =?";

        PreparedStatement stm = null;
        ResultSet rs = null;
        Aluguel aluguelBuscado = new Aluguel();

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                aluguelBuscado.setId(rs.getInt("id"));
                aluguelBuscado.setClienteId(rs.getInt("id_cliente"));
                aluguelBuscado.setDataAluguel(rs.getDate("data_aluguel"));
                aluguelBuscado.setValor(rs.getDouble("valor"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar do banco. " + e);
            return null;
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return aluguelBuscado;
    }

    /*----------------------------------------------------------*/
    public void removerAluguel(int id) throws SQLException {
         String sql = "DELETE FROM en_aluguel WHERE id = ?";

        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Deu erro em excluir do banco." + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }
}
