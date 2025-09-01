package negocio.entidade;
import java.io.Serializable;

public class Gerente extends Usuario {

    private String senha; // Um atributo de exemplo, específico do Gerente

    public Gerente(String nome, String cpf, String senha) {
        //    passando as informações que ele precisa (nome e cpf).
        super(nome, cpf);

        // 2. cuida dos atributos que são só do Gerente.
        this.senha = senha;
    }

    //Getters e Setters específicos do Gerente

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}