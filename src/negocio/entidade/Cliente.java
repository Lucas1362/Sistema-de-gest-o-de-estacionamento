package negocio.entidade;
import java.io.Serializable;

public class Cliente extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    private Veiculo veiculo;
    private boolean preferencial;
    // Construtor de cliente
    public Cliente(String cpf, Boolean preferencial){
        super(cpf);
        this.preferencial = preferencial;
    }

    //getters


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    //setters

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

    public void setVeiculo(Veiculo carro){
        this.veiculo = carro;
    }


}


