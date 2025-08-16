package model;

public class Cliente extends Usuario{
    private Veiculo veiculo;
    private boolean preferencial;
    // Construtor de cliente
    public Cliente(String nome, String cpf, Boolean preferencial){
        super(nome, cpf);
        this.preferencial = preferencial;
    }



}


