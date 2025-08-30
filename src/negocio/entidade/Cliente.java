package negocio.entidade;

public class ClienteUsuario extends Usuario {

    // Este atributo não está no documento, mas é uma adição válida ao negócio.
    private boolean preferencial;

    // Futuramente, podemos alterar para uma lista, caso um cliente possa ter vários veículos.
    private Veiculo veiculo;

    public ClienteUsuario(String nome, String cpf, boolean preferencial) {
        super(nome, cpf); // Chama o construtor da classe pai (Usuario) para definir nome e cpf.
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

    // O getCpf() e setCpf() não são necessários aqui, pois são herdados de Usuario.
    // Podemos chamá-los diretamente num objeto ClienteUsuario. ex: meuCliente.getCpf();
}


