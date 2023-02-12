/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import conexao.ConectaFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Fornecedor;

/**
 *
 * @author EDIVANIA CAPEL
 */
public class FornecedorDAO {
    private Connection con;
    
    public FornecedorDAO(){
       this.con = new ConectaFactory().getConection();
    }
    
    
    public List<Fornecedor> listarFornecedores(){
        try{
            List<Fornecedor> lista = new ArrayList<>();
            
            String sql = "select * from fornecedor";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedor obj = new Fornecedor();
                
                obj.setNome(rs.getString("Nome"));
                obj.setCodFornecedor(rs.getInt("codigo"));
                obj.setTelefone(rs.getString("Telefone"));
                
                lista.add(obj);
            }
            
            return lista;
        }
        
       catch(SQLException erro){
           JOptionPane.showMessageDialog(null, "Erro ao listar os Dados  " +erro);
           return null;
       }
    }
    
      public void cadastrarFornecedor(Fornecedor obj){
        try{
            String sql = "insert into fornecedor(Nome, Codigo, Telefone)"
                    + "values (?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setInt(2, obj.getCodFornecedor());
                stmt.setString(3, obj.getTelefone());
                
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
      
      public void alterarFornecedor(Fornecedor obj){
        
        try{
            String sql = "update fornecedor set Nome=?, Telefone=? where Codigo=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getTelefone());
                //stmt.setString(5, obj.getNascimento());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
      
      public void excluirFornecedor(Fornecedor obj){
        try{
            String sql = "delete from medico where Codigo=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCodFornecedor());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro " +erro);
        }
    }
      
       public List<Fornecedor> buscaFornecedorPorNome(String nome){
        try{
            List<Fornecedor> lista= new ArrayList<>();
            
            String sql = "select * from fornecedor where Nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fornecedor obj = new Fornecedor();
                obj.setNome(rs.getString("Nome"));
                obj.setCodFornecedor(rs.getInt("Codigo"));
                obj.setTelefone(rs.getString("Telefone"));
                //obj.setNascimento(rs.getString("Nacismento"));

                
                lista.add(obj);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar!  " +erro);
            return null;
        }
    }
}
