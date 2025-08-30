package negocio.entidade;
import java.io.Serializable;


public class Cliente extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    private Veiculo veiculo;
    private boolean preferencial;

    // Construtor de cliente
    public Cliente(String cpf, boolean preferencial) {
        super(cpf); // Chama o construtor da classe pai (Usuario)
        this.preferencial = preferencial;
    }
    // Getters
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    // Setters
    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;

    }
}

