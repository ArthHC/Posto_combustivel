package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Funcionario;
import database.MysqlSingleton;


public class FuncionarioDao {
    private Connection connection;

    public FuncionarioDao() {
        this.connection = MysqlSingleton.getConnection();
    }

    public boolean inserir(Funcionario func) {
        String sql = "INSERT INTO  tb_funcionario (nome_func, sobrenome, cpf_func, pis_func, salario_func, departamento_func, email_func, telefone_func, endereco_func) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getSobrenome());
            stmt.setString(3, func.getCpfFunc());
            stmt.setString(4, func.getPis());
            stmt.setFloat(5, func.getSalario());
            stmt.setString(6, func.getDepartamento());
            stmt.setString(7, func.getEmail());
            stmt.setString(8, func.getTelefone());
            stmt.setString(9, func.getEndereco());
            stmt.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Funcionario> listar() {
        String sql = "SELECT * FROM  tb_funcionario";
        List<Funcionario> listaFuncioarios = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario func = new Funcionario();
                func.setIdFuncionario(resultado.getInt("id_func"));
                func.setNome(resultado.getString("nome_func"));
                func.setSobrenome(resultado.getString("sobrenome"));
                func.setCpfFunc(resultado.getString("cpf_func"));
                func.setPis(resultado.getString("pis_func"));
                func.setSalario(resultado.getFloat("salario_func"));
                func.setDepartamento(resultado.getString("departamento_func"));
                func.setEmail(resultado.getString("email_func"));
                func.setTelefone(resultado.getString("telefone_func"));
                func.setEndereco(resultado.getString("endereco_func"));
                listaFuncioarios.add(func);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFuncioarios;
    }

    public boolean alterar(Funcionario func) {
        String sql = "UPDATE tb_funcionario SET nome_func=?, sobrenome=?, cpf_func=?, pis_func=?, salario_func=?, departamento_func=?, email_func=?, telefone_func=?, endereco_func=?  WHERE id_func=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getSobrenome());
            stmt.setString(3, func.getCpfFunc());
            stmt.setString(4, func.getPis());
            stmt.setFloat(5, func.getSalario());
            stmt.setString(6, func.getDepartamento());
            stmt.setString(7, func.getEmail());
            stmt.setString(8, func.getTelefone());
            stmt.setString(9, func.getEndereco());
            stmt.setInt(10, func.getIdFuncionario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Funcionario func) {
        String sql = "DELETE FROM tb_funcionario WHERE id_func=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, func.getIdFuncionario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Funcionario buscar(Integer idFuncionario) {
        String sql = "SELECT * FROM tb_funcionario WHERE id_func=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            ResultSet resultado = stmt.executeQuery();
            Funcionario func = new Funcionario();
            if (resultado.next()) {
                func.setIdFuncionario(resultado.getInt("id_func"));
                func.setNome(resultado.getString("nome_func"));
                func.setSobrenome(resultado.getString("sobrenome_func"));
                func.setCpfFunc(resultado.getString("cpf_func"));
                func.setPis(resultado.getString("pis_func"));
                func.setSalario(resultado.getFloat("salario_func"));
                func.setDepartamento(resultado.getString("departamento_func"));
                func.setEmail(resultado.getString("email_func"));
                func.setTelefone(resultado.getString("telefone_func"));
                func.setEndereco(resultado.getString("endereco_func"));
                return func;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}