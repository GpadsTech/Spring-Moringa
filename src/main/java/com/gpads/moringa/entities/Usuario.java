package com.gpads.moringa.entities;



public class Usuario {

    
    private long id;

    private String email;
    private String senha; //verificar outras estratégias para este armazenamento
    private String nome;


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


}
