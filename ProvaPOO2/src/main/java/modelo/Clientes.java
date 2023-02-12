package modelo;

import java.sql.Date;
import modelo.Medico;



public class Clientes {
   private String nome;
   private int CPF;
   private String Endereco;
   private String Telefone;
   private int Nascimento;
   
   private Medico medicor;

    public Medico getMedicor() {
        return medicor;
    }

    public void setMedicor(Medico medicor) {
        this.medicor = medicor;
    }


    public int getNascimento() {
        return Nascimento;
    }

    public void setNascimento(int Nascimento) {
        this.Nascimento = Nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    
}
