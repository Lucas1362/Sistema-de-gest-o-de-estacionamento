package negocio.entidade;


public class Usuario {
    private String nome;
    private String cpf;


    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // --- Getters e Setters ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}