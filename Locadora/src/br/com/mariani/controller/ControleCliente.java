package br.com.mariani.controller;

import br.com.mariani.dao.DaoCliente;
import br.com.mariani.models.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author maryucha
 */
public class ControleCliente {

    private static List<Cliente> listaClientes = new ArrayList<>();
    private static Scanner ENTRADA = new Scanner(System.in);

    /*----------------------------------------------------------*/
    public static Cliente addClienteNovo() throws SQLException {
        DaoCliente DAO = new DaoCliente();
        Cliente clienteNovo = new Cliente();
        
        System.out.println("Digite o nome do Cliente: ");
        clienteNovo.setNome(ENTRADA.nextLine());
        
        DAO.addClienteNobanco(clienteNovo);
        listaClientes.add(clienteNovo);
        return clienteNovo;
    }

    /*----------------------------------------------------------*/
    public static void listarClientes() throws SQLException {
        DaoCliente DAO = new DaoCliente();
       listaClientes = DAO.listarClientes();
       for(int i=0;i<listaClientes.size();i++){
           listaClientes.get(i).imprimeCliente();
       }
    }

    /*----------------------------------------------------------*/
    public static Cliente buscarCliente() throws SQLException {
        DaoCliente DAO = new DaoCliente();
        System.out.print("Digite o id do cliente: ");
        Cliente clienteBuscado = DAO.buscarCliente(ENTRADA.nextInt());
        ENTRADA.nextLine();
        System.out.println("NOME [" + clienteBuscado.getNome() + "]");
        return clienteBuscado;
    }

    /*----------------------------------------------------------*/
    public static Cliente atualizarCliente() throws SQLException {
        DaoCliente DAO = new DaoCliente();
        
        Cliente clienteBuscado = buscarCliente();
        System.out.print("Digite o nome do cliente: ");
        clienteBuscado.setNome(ENTRADA.nextLine());      
        DAO.atualizarCliente(clienteBuscado.getId(), clienteBuscado);
        System.out.println("NOME ATUALIZADO PARA [" + clienteBuscado.getNome() + "]");
        return clienteBuscado;
    }
    /*----------------------------------------------------------*/
    
    public static Cliente deletarCliente() throws SQLException{
        DaoCliente DAO = new DaoCliente();
        Cliente clienteBuscado = buscarCliente();
        
        DAO.excluirdoBanco(clienteBuscado.getId()); 
        return null;
    }
    
}
