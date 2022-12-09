package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Produto;
import database.MysqlSingleton;

public class ProdutoDao {
    private Connection connection;

    public ProdutoDao() {
        this.connection = MysqlSingleton.getConnection();
    }

    public boolean inserir(Produto prod) { 
        String sql = "INSERT INTO  tb_produto (nome_pord, codigo_prod, quantidade_prod, valor_prod, id_combustivel) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, prod.getNomeProduto());
            stmt.setInt(2, prod.getCodigoBarras());
            stmt.setFloat(3, prod.getQtde());
            stmt.setFloat(4, prod.getValor());
            stmt.setInt(5, prod.getFkCombustivel());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Produto> listar() {
        String sql = "SELECT * FROM  tb_produto";
        List<Produto> listaProdutos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	Produto prod = new Produto();
                prod.setNomeProduto(resultado.getString("nome_prod"));
                prod.setCodigoBarras(resultado.getInt("codigo_prod"));
                prod.setQtde(resultado.getFloat("quantidade_prod"));
                prod.setValor(resultado.getFloat("valor_prod"));
                prod.setFkCombustivel(resultado.getInt("id_combustivel"));
                listaProdutos.add(prod);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProdutos;
    }

    public boolean alterar(Produto prod) {
        String sql = "UPDATE tb_produto SET nome_pord=?, codigo_prod=?, quantidade_prod=?, valor_prod=?, id_combustivel=?  WHERE id_prod=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, prod.getNomeProduto());
            stmt.setInt(2, prod.getCodigoBarras());
            stmt.setFloat(3, prod.getQtde());
            stmt.setFloat(4, prod.getValor());
            stmt.setInt(5, prod.getFkCombustivel());
            stmt.setInt(6, prod.getIdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Produto prod) {
        String sql = "DELETE FROM tb_produto WHERE id_prod=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, prod.getIdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Produto buscar(Integer id) {
        String sql = "SELECT * FROM tb_produto WHERE id_prod=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            Produto prod = new Produto();
            if (resultado.next()) {
            	prod.setNomeProduto(resultado.getString("nome_prod"));
                prod.setCodigoBarras(resultado.getInt("codigo_prod"));
                prod.setQtde(resultado.getFloat("quantidade_prod"));
                prod.setValor(resultado.getFloat("valor_prod"));
                prod.setFkCombustivel(resultado.getInt("id_combustivel"));
                return prod;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
