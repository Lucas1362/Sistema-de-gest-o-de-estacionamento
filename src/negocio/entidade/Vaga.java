package negocio.entidade;

/**
 * Representa uma Vaga de estacionamento.
 * Agora a Vaga tem um status para indicar se está livre ou ocupada.
 */
public class Vaga {

    private String id; // Ex: A-10, B-12
    private StatusVaga status;

    public Vaga(String id) {
        this.id = id;
        this.status = StatusVaga.LIVRE; // Toda nova vaga começa livre.
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public StatusVaga getStatus() {
        return status;
    }

    // --- Métodos de Negócio ---

    /**
     * Ocupa a vaga, mudando seu status para OCUPADA.
     */
    public void ocupar() {
        this.status = StatusVaga.OCUPADA;
    }

    /**
     * Libera a vaga, mudando seu status para LIVRE.
     */
    public void liberar() {
        this.status = StatusVaga.LIVRE;
    }

    /**
     * Verifica se a vaga está livre.
     * @return true se o status for LIVRE, false caso contrário.
     */
    public boolean isLivre() {
        return this.status == StatusVaga.LIVRE;
    }
}