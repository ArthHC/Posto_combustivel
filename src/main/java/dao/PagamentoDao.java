package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Pagamento;
import database.MysqlSingleton;

public class PagamentoDao {
    private Connection connection;

    public PagamentoDao() {
        this.connection = MysqlSingleton.getConnection();
    }
    public boolean inserir(Pagamento pag) { 
        String sql = "INSERT INTO  tb_pagamento (tipo_pagamento) VALUES (?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pag.getPagamento());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
	    public List<Pagamento> listar() {
        String sql = "SELECT * FROM  tb_pagamento";
        List<Pagamento> listaPagamento = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	Pagamento pag = new Pagamento();
            	pag.setIdPagamento(resultado.getInt("id_pagamento"));
                pag.setPagamento(resultado.getString("tipo_pagamento"));
                listaPagamento.add(pag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPagamento;
    }
	    public boolean alterar(Pagamento pag) {
        String sql = "UPDATE tb_pagamento SET tipo_pagamento=? WHERE id_pagamento=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pag.getPagamento());
            stmt.setInt(2, pag.getIdPagamento());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	    public boolean remover(Pagamento pag) {
        String sql = "DELETE FROM tb_pagamento WHERE id_pagamento=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pag.getIdPagamento());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	    public Pagamento buscar(Integer idPagamento) {
        String sql = "SELECT * FROM tb_pagamento WHERE id_pagamento=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idPagamento);
            ResultSet resultado = stmt.executeQuery();
            Pagamento pag = new Pagamento();
            if (resultado.next()) {
            	pag.setPagamento(resultado.getString("tipo pagamento"));
                return pag;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

