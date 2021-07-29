/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.thiago;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Reação
 */
public class OperaçõesCliente {
    
    private final String url = "jdbc:mysql://localhost/dy_blacks";
    private final String usuario = "root";
    private final String senha = "";
    private Statement querys;
    private ResultSet resultados;
    private Connection conexao;
    
    //Construtor OperaçõesCliente

    public OperaçõesCliente() {
        
        this.Conectar();
    }
    
    
    
    public void Conectar() {
        
        try {
        
        //Seta a variavel da classe para este driver manager    
        Connection conexao1 = DriverManager.getConnection(url,usuario,senha);
        this.setConexao(conexao1);
        
        //Seta a variavel da classe para este Statement
        Statement stmt = this.getConexao().createStatement();
        this.setQuerys(stmt);
        
        }   catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }
    
    public DefaultTableModel Buscar(JTable tabela_entrada, String cliente){
        
        DefaultTableModel tabela_trabalhada;
        ArrayList <Clientes> array_telefones = new ArrayList<Clientes>();
        
        try {
            
            //Procura o nome digitado
            this.setResultados(this.getQuerys().executeQuery("select id_cliente,nome,tel_res,celula,tel_com from dy_cliente where nome like '%" + cliente + "%'"));
            
            //Verifica se for encontrado!
            if (this.getResultados().next()) {
                
                this.setResultados(null);
                this.setResultados(this.getQuerys().executeQuery("select id_cliente,nome,tel_res,celula,tel_com from dy_cliente where nome like '%" + cliente + "%'"));
                
                
                
                int c = 0;
                
                while(this.getResultados().next()) {
                    
                    int result_atual = this.getResultados().getRow();
                    System.out.println(result_atual);
                            
                    Clientes Local = new Clientes();
                    
                            
                    Local.setId(this.getResultados().getString(1));
                    Local.setNome(this.getResultados().getString(2));
                    Local.setTelefone1(this.getResultados().getString(3));
                    Local.setTelefone2(this.getResultados().getString(4));
                    Local.setTelefone3(this.getResultados().getString(5));
                    
                    array_telefones.add(c, Local);
                    
                    c++;
                    
                    
                }
                
                
                
                
                
            } else {
                
            JOptionPane.showMessageDialog(null, "Erro! Cliente não encontrado!");
                
            }
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
        // Instanceia a tabela de entrada para nova tabela
        
        tabela_trabalhada = (DefaultTableModel) tabela_entrada.getModel();
        
        
        //Exclui tabela anterior para não encavalar
        
        int total_tabela = tabela_trabalhada.getRowCount();
        
        for (int c = total_tabela - 1 ; c >= 0; c--) {
            
            tabela_trabalhada.removeRow(c);
                    
            
        }
        
        //Seta a arraylist com a nova tabela
        
        int total_array = array_telefones.size();
        
        for (int c = 0; c < total_array; c++) {
            
            String [] arrayobj_tostr = {array_telefones.get(c).getId(), array_telefones.get(c).getNome(), array_telefones.get(c).getTelefone1(), array_telefones.get(c).getTelefone2(), array_telefones.get(c).getTelefone3()};
            
            tabela_trabalhada.addRow(arrayobj_tostr);
           
            
        }
        
        
        
              
        this.setResultados(null);
        return tabela_trabalhada;
        
    }
    public DefaultTableModel Todos(JTable tabela_entrada){
        
        DefaultTableModel tabela_trabalhada;
        ArrayList <Clientes> array_telefones = new ArrayList<Clientes>();
        
        try {
            
            //Procura o nome digitado
            this.setResultados(this.getQuerys().executeQuery("select id_cliente,nome,tel_res,celula,tel_com from dy_cliente"));
            
            //Verifica se for encontrado!
            if (this.getResultados().next()) {
                
                this.setResultados(null);
                this.setResultados(this.getQuerys().executeQuery("select id_cliente,nome,tel_res,celula,tel_com from dy_cliente"));
                
                
                
                int c = 0;
                
                while(this.getResultados().next()) {
                    
                    int result_atual = this.getResultados().getRow();
                    System.out.println(result_atual);
                            
                    Clientes Local = new Clientes();
                    
                            
                    Local.setId(this.getResultados().getString(1));
                    Local.setNome(this.getResultados().getString(2));
                    Local.setTelefone1(this.getResultados().getString(3));
                    Local.setTelefone2(this.getResultados().getString(4));
                    Local.setTelefone3(this.getResultados().getString(5));
                    
                    array_telefones.add(c, Local);
                    
                    c++;
                    
                    
                }
                
                
                
                
                
            } else {
                
            JOptionPane.showMessageDialog(null, "Erro! Cliente não encontrado!");
                
            }
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        
        // Instanceia a tabela de entrada para nova tabela
        
        tabela_trabalhada = (DefaultTableModel) tabela_entrada.getModel();
        
        
        //Exclui tabela anterior para não encavalar
        
        int total_tabela = tabela_trabalhada.getRowCount();
        
        for (int c = total_tabela - 1 ; c >= 0; c--) {
            
            tabela_trabalhada.removeRow(c);
                    
            
        }
        
        //Seta a arraylist com a nova tabela
        
        int total_array = array_telefones.size();
        //int test = array_telefones.get(0).getId();
       //String [] arrayobj_tostr = new String[5];
        for (int c = 0; c < total_array; c++) {
            
            String [] arrayobj_tostr = {array_telefones.get(c).getId(), array_telefones.get(c).getNome(), array_telefones.get(c).getTelefone1(), array_telefones.get(c).getTelefone2(), array_telefones.get(c).getTelefone3()};
            
            tabela_trabalhada.addRow(arrayobj_tostr);
           
            
        }
        
        
        
              
        this.setResultados(null);
        return tabela_trabalhada;
        
    }
    
    
    //Getters and Setters

    public Statement getQuerys() {
        return querys;
    }

    public void setQuerys(Statement querys) {
        this.querys = querys;
    }

    public ResultSet getResultados() {
        return resultados;
    }

    public void setResultados(ResultSet resultados) {
        this.resultados = resultados;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }
    
    
    
    
}
