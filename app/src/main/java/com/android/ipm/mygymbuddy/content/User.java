package com.android.ipm.mygymbuddy.content;

import java.io.Serializable;

/**
 * @author Rodolfo
 *
 */
public class User implements Serializable {
    public static final String EXTRA = "com.android.ipm.mygymbuddy.content.USER_EXTRA";

    private int idade;
    private String Nome ;
    private String email ;
    private double peso;
    private double altura;
    private String sexo ;
    private String password;




    public User (String Nome , String password, String email,String sexo, int idade, double peso , double altura ){
        this.Nome = Nome;
        this.email = email;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.sexo = sexo;
        this.password = password;
    }

    public double calcIMC() {
        return (peso / (altura * altura));
    }

    public double calcGorduraCorporal(){
        int tmpSex ;
        if(sexo == "M" )
            tmpSex = 1;
        else tmpSex = 0;
        return ((1.20 * this.calcIMC()) + (0.23 * this.idade - (10.8 * tmpSex)));
    }

    public String getClasificação(){
        double tmp  = this.calcGorduraCorporal();
        String rsp ;
        if(tmp <= 19.99)
            rsp = "Magro";
        else if(tmp <= 24.99)
            rsp = "Normal";
        else if(tmp <= 29.99)
            rsp = "Excesso de Peso";
        else if (tmp <= 35)
            rsp = "Obesidade";
        else
            rsp = "Obesidade Grave";

        return rsp;
    }

    public int getIdade (){
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }
}
