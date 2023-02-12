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
import modelo.Clientes;
import modelo.Medico;

/**
 *
 * @author EDIVANIA CAPEL
 */
public class MedicoDAO {
    private Connection con;
    
    public MedicoDAO(){
        
    this.con = new ConectaFactory().getConection();
    }
    
    public List<Medico> listarMedicos(){
        try{
            List<Medico> lista = new ArrayList<>();
            
            String sql = "select * from medico";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Medico obj = new Medico();
                
                obj.setNome(rs.getString("Nome"));
                obj.setCRM(rs.getInt("CRM"));
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
    
      public void cadastrarMedico(Medico obj){
        try{
            String sql = "insert into medico(Nome, CRM, Endereço, Telefone)"
                    + "values (?, ?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setInt(2, obj.getCRM());
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
      
      public void alterarMedico(Medico obj){
        
        try{
            String sql = "update medico set Nome=?, Endereço=?, Telefone=? where CRM=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getEndereco());
                stmt.setString(3, obj.getTelefone());
                stmt.setInt(4, obj.getCRM());
                //stmt.setString(5, obj.getNascimento());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
      
      public void excluirMedico(Medico obj){
        try{
            String sql = "delete from medico where CRM=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCRM());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro " +erro);
        }
    }
      
       public List<Medico> buscaMedicoPorNome(String nome){
        try{
            List<Medico> lista= new ArrayList<>();
            
            String sql = "select * from medico where Nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Medico obj = new Medico();
                obj.setNome(rs.getString("Nome"));
                obj.setCRM(rs.getInt("CRM"));
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
}
