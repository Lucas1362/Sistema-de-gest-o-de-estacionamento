package model;

public class Cliente{
    private String nome;
    private String cpf;

    // Construtor de cliente
    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    //inicio dos getters e setters

    public String getNome(){
        return nome;

    }
    public String getCpf(){
        return cpf;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}


