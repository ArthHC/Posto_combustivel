package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Fornecedor;
import database.MysqlSingleton;

public class FornecedorDao {
	private Connection connection;
	
	public FornecedorDao() {
		this.connection = MysqlSingleton.getConnection();
	}
	
	public boolean inserir(Fornecedor forn) {
        String sql = "INSERT INTO  tb_fornecedor (nome_forn, cnpj, email_forn, telefone_forn, endereco_forn) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, forn.getNomeForn());
            stmt.setString(2, forn.getCnpj());
            stmt.setString(3, forn.getEmailForn());
            stmt.setString(4, forn.getTelefoneForn());
            stmt.setString(5, forn.getEndereco());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
	public List<Fornecedor> listar() {
        String sql = "SELECT * FROM  tb_fornecedor";
        List<Fornecedor> listaFornecedor = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	Fornecedor forn = new Fornecedor();
            	forn.setIdFornecedor(resultado.getInt("id_forn"));
                forn.setNomeForn(resultado.getString("nome_forn"));
                forn.setCnpj(resultado.getString("cnpj"));
                forn.setEmailForn(resultado.getString("email_forn"));
                forn.setTelefoneForn(resultado.getString("telefone_forn"));
                forn.setEndereco(resultado.getString("endereco_forn"));
                listaFornecedor.add(forn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFornecedor;
    }

    public boolean alterar(Fornecedor forn) {
        String sql = "UPDATE tb_fornecedor SET nome_forn=?, cnpj=?, email_forn=?, telefone_forn=?, endereco_forn=?  WHERE id_forn=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, forn.getNomeForn());
            stmt.setString(2, forn.getCnpj());
            stmt.setString(3, forn.getEmailForn());
            stmt.setString(4, forn.getTelefoneForn());
            stmt.setString(5, forn.getEndereco());
            stmt.setInt(6, forn.getIdFornecedor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Fornecedor forn) {
        String sql = "DELETE FROM tb_fornecedor WHERE id_forn=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, forn.getIdFornecedor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Fornecedor buscar(Integer idFornecedor) {
        String sql = "SELECT * FROM tb_fornecedor WHERE id_forn=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idFornecedor);
            ResultSet resultado = stmt.executeQuery();
            Fornecedor forn = new Fornecedor();
            if (resultado.next()) {
                forn.setIdFornecedor(resultado.getInt("id_forn"));
                forn.setNomeForn(resultado.getString("nome_forn"));
                forn.setCnpj(resultado.getString("cnpj"));
                forn.setEmailForn(resultado.getString("email_forn"));
                forn.setTelefoneForn(resultado.getString("telefone_forn"));
                forn.setEndereco(resultado.getString("endereco_forn"));
                return forn;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
	
