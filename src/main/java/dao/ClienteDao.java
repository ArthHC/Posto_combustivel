package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Cliente;
import database.MysqlSingleton;


public class ClienteDao {
    private Connection connection;

    public ClienteDao() {
        this.connection = MysqlSingleton.getConnection();
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO  tb_cliente (nome_cli, sobrenome, cpf_cli, email_cli, telefone_cli, endereco_cli) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getCpfCli());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM  tb_cliente";
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultado.getInt("id_cli"));
                cliente.setNome(resultado.getString("nome_cli"));
                cliente.setSobrenome(resultado.getString("sobrenome"));
                cliente.setCpfCli(resultado.getString("cpf_cli"));
                cliente.setEmail(resultado.getString("email_cli"));
                cliente.setTelefone(resultado.getString("telefone_cli"));
                cliente.setEndereco(resultado.getString("endereco_cli"));
                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE tb_cliente SET nome_cli=?, sobrenome=?, cpf_cli=?, email=?, telefone_cli=?, endereco_cli=?  WHERE id_cli=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setString(3, cliente.getCpfCli());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco());
            stmt.setInt(7, cliente.getIdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM tb_cliente WHERE id_cli=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Cliente buscar(Integer idCliente) {
        String sql = "SELECT * FROM tb_cliente WHERE id_cli=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet resultado = stmt.executeQuery();
            Cliente cliente = new Cliente();
            if (resultado.next()) {
                cliente.setIdCliente(resultado.getInt("id_cli"));
                cliente.setNome(resultado.getString("nome_cli"));
                cliente.setSobrenome(resultado.getString("sobrenome"));
                cliente.setCpfCli(resultado.getString("cpf_cli"));
                cliente.setEmail(resultado.getString("email_cli"));
                cliente.setTelefone(resultado.getString("telefone_cli"));
                cliente.setEndereco(resultado.getString("endereco_cli"));
                return cliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}