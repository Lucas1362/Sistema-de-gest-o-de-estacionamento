package negocio.entidade;

public class ClienteUsuario extends Usuario {

    private boolean preferencial;
    private Veiculo veiculo;


    public ClienteUsuario(String nome, String cpf, boolean preferencial) {
        // Chama o construtor da classe pai (Usuario) para definir nome e cpf.
        super(nome, cpf);
        this.preferencial = preferencial;
    }

    // --- Getters e Setters ---

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

    // Os métodos getCpf() e setCpf() são herdados automaticamente de Usuario.
}