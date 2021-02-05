package br.com.mariani.dao;

import br.com.mariani.connection.ConnectionFactory;
import br.com.mariani.models.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maryucha
 */
public class DaoFilme {

    /*----------------------------------------------------------*/
    private final Connection con = ConnectionFactory.getConnection();
    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /*----------------------------------------------------------*/
    public void addFilmeNoBanco(Filme filmeNovo) throws SQLException {
        String sql = "insert into en_filme (data_lanc , nome , descricao) values (?,?,?)";
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            stm.setDate(1, filmeNovo.getDataLancamento());
            stm.setString(2, filmeNovo.getNome());
            stm.setString(3, filmeNovo.getDescricao());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco. " + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    /*----------------------------------------------------------*/
    public List<Filme> listarFilmes() throws SQLException {
        String sql = "select * from en_filme";
        List<Filme> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getInt("id"));
                filme.setDataLancamento(rs.getDate("data_lanc"));
                filme.setNome(rs.getString("nome"));
                filme.setDescricao(rs.getString("descricao"));
                list.add(filme);
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
    public Filme buscarFilme(int id) throws SQLException {
        String sql = "select * from en_filme where id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        Filme filmeBuscado = new Filme();

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                filmeBuscado.setId(rs.getInt("id"));
                filmeBuscado.setDataLancamento(rs.getDate("data_lanc"));
                filmeBuscado.setNome(rs.getString("nome"));
                filmeBuscado.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco. " + e);
            return null;
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return filmeBuscado;
    }

    /*----------------------------------------------------------*/
    public void atualizarFilme(int id, Filme filmeBuscado) throws SQLException {
        String sql = "update en_filme set data_lanc = ?, nome = ?, descricao = ? where id = ?";

        PreparedStatement stm = null;

        try {
            stm = con.prepareStatement(sql);
            stm.setDate(1, filmeBuscado.getDataLancamento());
            stm.setString(2, filmeBuscado.getNome());
            stm.setString(3, filmeBuscado.getDescricao());
            stm.setInt(4, filmeBuscado.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o banco. " + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    /*----------------------------------------------------------*/
    public void excluirDoBanco(int id) throws SQLException {
        String sql = "DELETE FROM en_filme WHERE id = ?";

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

/*----------------------------------------------------------*/
