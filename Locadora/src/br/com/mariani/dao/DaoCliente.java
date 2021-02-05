package br.com.mariani.dao;

import br.com.mariani.connection.ConnectionFactory;
import br.com.mariani.models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maryucha
 */
public class DaoCliente {

    /*----------------------------------------------------------*/
    private final Connection con = ConnectionFactory.getConnection();

    /*----------------------------------------------------------*/

    public boolean addClienteNobanco(Cliente clienteNovo) throws SQLException {
        String sql = "insert into en_cliente (nome) values (?)";
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, clienteNovo.getNome());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco. " + e);
            return false;
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    /*----------------------------------------------------------*/
    public Cliente buscarCliente(int id) throws SQLException {
        String sql = "select * from en_cliente where id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente clienteBuscado = new Cliente();

        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                clienteBuscado.setId(rs.getInt("id"));
                clienteBuscado.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco. " + e);
            return null;
        } finally {
            ConnectionFactory.fecharConexao(con, stm, rs);
        }
        return clienteBuscado;
    }

    /*----------------------------------------------------------*/
    public void atualizarCliente(int id, Cliente cliente) throws SQLException {
        String sql = "update en_cliente set nome = ? where id = ?";

        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o banco. " + e);
        } finally {
            ConnectionFactory.fecharConexao(con, stm);
        }
    }

    /*----------------------------------------------------------*/
    public List<Cliente> listarClientes() throws SQLException {
        String sql = "select * from en_cliente";
        List<Cliente> list = new ArrayList<>();

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Cliente clienteBuscado = new Cliente();
                clienteBuscado.setId(rs.getInt("id"));
                clienteBuscado.setNome(rs.getString("nome"));
                list.add(clienteBuscado);
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
    public void excluirdoBanco(int id) throws SQLException {

        String sql = "DELETE FROM en_cliente WHERE id = ?";

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
