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
public class ClientesDAO {
    private Connection con;
    
    public ClientesDAO(){
       this.con = new ConectaFactory().getConection();
    }
    
      public List<Clientes> listarClientes(){
        try{
            List<Clientes> lista = new ArrayList<>();
            
            String sql = "select c.nome, c.cpf, c.endereço, c.telefone, c.nacismento, m.CRM from cliente as c"
                    + "inner join medico as m on";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                
                obj.setNome(rs.getString("Nome"));
                obj.setCPF(rs.getInt("cpf"));
                obj.setTelefone(rs.getString("Telefone"));
                obj.setEndereco(rs.getString("Endereço"));
                //obj.setNascimento(rs.getInt("Nacismento"));
                
                lista.add(obj);
            }
            
            return lista;
        }
        
       catch(SQLException erro){
           JOptionPane.showMessageDialog(null, "Erro ao listar os Dados  " +erro);
           return null;
       }
    }
    
      public void cadastrarCliente(Clientes obj){
        try{
            String sql = "insert into cliente(Nome, CPF, Endereço, Telefone, Nacismento, Medico_CRM)"
                    + "values (?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setInt(2, obj.getCPF());
                stmt.setString(3, obj.getEndereco());
                stmt.setString(4, obj.getTelefone());
                stmt.setInt(5, obj.getNascimento());
                stmt.setInt(6, obj.getMedicor().getCRM());
                
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
      
      public void alterarCliente(Clientes obj){
        
        try{
            String sql = "update cliente set Nome=?, Endereço=?, Telefone=?, Nacismento=?, Medico_CRM where CPF=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getEndereco());
                stmt.setString(3, obj.getTelefone());
                stmt.setInt(4, obj.getCPF());
                stmt.setInt(5, obj.getNascimento());
                stmt.setInt(6, obj.getMedicor().getCRM());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
      
      public void excluirCliente(Clientes obj){
        try{
            String sql = "delete from cliente where CPF=?";
            
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
      
       public List<Clientes> buscaClientePorNome(String nome){
        try{
            List<Clientes> lista= new ArrayList<>();
            
            String sql = "select * from cliente where Nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                obj.setNome(rs.getString("Nome"));
                obj.setCPF(rs.getInt("CPF"));
                obj.setEndereco(rs.getString("Endereço"));
                obj.setTelefone(rs.getString("Telefone"));
                obj.setNascimento(rs.getInt("Nacismento"));

                
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