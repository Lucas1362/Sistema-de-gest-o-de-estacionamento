package negocio.entidade;

public class Cliente extends Usuario{
    private Veiculo veiculo;
    private boolean preferencial;
    private String cpf; //cpf servirá para fazer o login
    // Construtor de cliente
    public Cliente(String cpf, Boolean preferencial){
        super(nome, cpf);
        this.preferencial = preferencial;
    }

    //getters


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    public String getCpf() { return cpf;}

    //setters

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

    public void setCpf(String cpf) { this.cpf = cpf;}

    public void setVeiculo(Veiculo carro){
        this.veiculo = carro;
    }


}


