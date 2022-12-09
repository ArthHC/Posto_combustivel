package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Pedido;
import database.MysqlSingleton;

public class PedidoDao {
	private Connection connection;

    public PedidoDao() {
        this.connection = MysqlSingleton.getConnection();
    }
        
    public boolean inserir(Pedido ped) { 
        String sql = "INSERT INTO  tb_pedido (id_pagamento, id_prod, id_cli, nome_pedido, quantidade_pedido, valor_pedido) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, ped.getFkPagamento());
            stmt.setInt(2, ped.getFkProduto());
            stmt.setInt(3, ped.getFkCliente());
            stmt.setString(4, ped.getNomePedido());
            stmt.setFloat(5, ped.getQtde());
            stmt.setFloat(6, ped.getValor());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
	    public List<Pedido> listar() {
        String sql = "SELECT * FROM  tb_pedido";
        List<Pedido> listaPedido = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	Pedido ped = new Pedido();
            	ped.setIdPedido(resultado.getInt("id_pedido"));
                ped.setFkPagamento(resultado.getInt("id_pagamento"));
                ped.setFkProduto(resultado.getInt("id_prod"));
                ped.setFkCliente(resultado.getInt("id_cli"));
                ped.setNomePedido(resultado.getString("nome_pedido"));
                ped.setQtde(resultado.getFloat("quantidade_pedido"));
                ped.setValor(resultado.getFloat("valor_pedido"));
                listaPedido.add(ped);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPedido;
    }
	    public boolean alterar(Pedido ped) {
        String sql = "UPDATE tb_pedido SET id_pagamento=?, id_produto=?, id_cliente=?, nome_pedido=?, quatidade_pedido=?, valor_pedido=?  WHERE id_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ped.getFkPagamento());
            stmt.setInt(2, ped.getFkProduto());
            stmt.setInt(3, ped.getFkCliente());
            stmt.setString(4, ped.getNomePedido());
            stmt.setFloat(5, ped.getQtde());
            stmt.setFloat(6, ped.getValor());
            stmt.setInt(7, ped.getIdPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	    public boolean remover(Pedido ped) {
        String sql = "DELETE FROM tb_pedido WHERE id_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ped.getIdPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	    public Pedido buscar(Integer idPedido) {
        String sql = "SELECT * FROM tb_pedido WHERE id_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            ResultSet resultado = stmt.executeQuery();
            Pedido ped = new Pedido();
            if (resultado.next()) {
            	ped.setFkPagamento(resultado.getInt("id_pagamento"));
                ped.setFkProduto(resultado.getInt("id_prod"));
                ped.setFkCliente(resultado.getInt("id_cli"));
                ped.setNomePedido(resultado.getString("nome_pedido"));
                ped.setQtde(resultado.getFloat("quantidade_pedido"));
                ped.setValor(resultado.getFloat("valor_pedido"));
                return ped;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
}