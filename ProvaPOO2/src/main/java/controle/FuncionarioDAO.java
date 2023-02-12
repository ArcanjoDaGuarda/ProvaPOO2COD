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
import modelo.*;
import visao.frmLogin;
import visao.frmMenu;

/**
 *
 * @author EDIVANIA CAPEL
 */
public class FuncionarioDAO {
    private Connection con;
    
    public FuncionarioDAO(){
       this.con = new ConectaFactory().getConection();
    }
    
    
    public List<Fucionario> listarFuncionarios(){
        try{
            List<Fucionario> lista = new ArrayList<>();
            
            String sql = "select * from funcionario";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fucionario obj = new Fucionario();
                
                obj.setNome(rs.getString("Nome"));
                obj.setCPF(rs.getInt("CPF"));
                obj.setTelefone(rs.getString("Telefone"));
                obj.setEndereco(rs.getString("Endereço"));
                
                lista.add(obj);
            }
            
            return lista;
        }
        
       catch(SQLException erro){
           JOptionPane.showMessageDialog(null, "Erro ao listar os Dados  " +erro);
           return null;
       }
    }
    
      public void cadastrarFuncionarios(Fucionario obj){
        try{
            String sql = "insert into funcionario(Nome, CPF, Endereço, Telefone)"
                    + "values (?, ?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setInt(2, obj.getCPF());
                stmt.setString(3, obj.getEndereco());
                stmt.setString(4, obj.getTelefone());
                
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
      
      public void alterarFuncionarios(Fucionario obj){
        
        try{
            String sql = "update funcionario set Nome=?, Endereço=?, Telefone=? where CPF=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getEndereco());
                stmt.setString(3, obj.getTelefone());
                stmt.setInt(4, obj.getCPF());
                //stmt.setString(5, obj.getNascimento());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
      
      public void excluirFuncioanrios(Fucionario obj){
        try{
            String sql = "delete from medico where CRM=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCPF());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro " +erro);
        }
    }
      
       public List<Fucionario> buscaFuncionarioPorNome(String nome){
        try{
            List<Fucionario> lista= new ArrayList<>();
            
            String sql = "select * from funcionario where Nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Fucionario obj = new Fucionario();
                obj.setNome(rs.getString("Nome"));
                obj.setCPF(rs.getInt("CPF"));
                obj.setEndereco(rs.getString("Endereço"));
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
    
    
    
    
    public void efetuarLogin(String nome){
        try{
            
            String sql = "select * from funcionario where nome=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Seja Bem Vindo Ao Sistema!");
                frmMenu tela = new frmMenu();
                tela.usuarioLogado = rs.getString("nome");
                tela.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Dados Incorretos");
                new frmLogin().setVisible(true);
            }
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro  " +erro);
        }
    }
}
