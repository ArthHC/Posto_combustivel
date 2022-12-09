package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Combustivel;
import database.MysqlSingleton;

public class CombustivelDao {
    private Connection connection;

    public CombustivelDao() {
        this.connection = MysqlSingleton.getConnection();
    }
    public boolean inserir(Combustivel c) { 
        String sql = "INSERT INTO  tb_combustivel (tipo_combustivel) VALUES (?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getTipoCombustivel());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CombustivelDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
	    public List<Combustivel> listar() {
        String sql = "SELECT * FROM  tb_combustivel";
        List<Combustivel> listaCombustivel = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	Combustivel c = new Combustivel();
            	c.setIdCombustivel(resultado.getInt("id_combustivel"));
                c.setTipoCombustivel(resultado.getString("tipo_combustivel"));
                listaCombustivel.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CombustivelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCombustivel;
    }
	    public boolean alterar(Combustivel c) {
        String sql = "UPDATE tb_combustivel SET tipo_combustivel=?  WHERE id_combustivel=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getTipoCombustivel());
            stmt.setInt(2, c.getIdCombustivel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CombustivelDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	    public boolean remover(Combustivel c) {
        String sql = "DELETE FROM tb_combustivel WHERE id_prod=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, c.getIdCombustivel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CombustivelDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	    public Combustivel buscar(Integer idCombustivel) {
        String sql = "SELECT * FROM tb_combustivel WHERE id_combustivel=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCombustivel);
            ResultSet resultado = stmt.executeQuery();
            Combustivel c = new Combustivel();
            if (resultado.next()) {
            	c.setTipoCombustivel(resultado.getString("tipo_combustivel"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CombustivelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
