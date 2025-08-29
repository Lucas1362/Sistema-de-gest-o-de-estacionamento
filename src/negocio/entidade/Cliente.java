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
=======
public class Cliente extends Usuario {
    private Veiculo veiculo;
    private boolean preferencial;
    private String cpf; // CPF servirá para fazer o login


    // Construtor de cliente


    public Cliente(String nome, String cpf, boolean preferencial) {
        super(nome, cpf); // Chama o construtor da classe pai (Usuario)
        this.preferencial = preferencial;
        this.cpf = cpf; // Inicializa o CPF
    }
    // Getters
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean isPreferencial() {
        return preferencial;
    }


    //setters
=======
    public String getCpf() {
        return cpf;
    }




    // Setters
    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }


    public void setVeiculo(Veiculo carro){
        this.veiculo = carro;
=======
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;

    }
}

