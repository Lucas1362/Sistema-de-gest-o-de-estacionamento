package negocio.entidade;
import java.io.Serializable;
import java.time.LocalDate;

public class Gerente extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;

    // Construtor mantido igual
    public Gerente(String cpf) {
        super(cpf);
    }

    /**
     * Gera relatório mensal simplificado
     * @param mes 1-12
     * @return String com dados formatados
     */
    public String gerarRelatorio(int mes) {
        if(mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês inválido");
        }

        return String.format("""
            RELATÓRIO MENSAL - %02d/%d
            Total de vagas: %d
            Ocupação média: %.1f%%
            """,
                mes,
                LocalDate.now().getYear(),
                50,  // Valor exemplo
                75.5 // Valor exemplo
        );
    }

    /**
     * Atualiza dados do cliente
     */
    public void modificarCliente(Cliente c, String novoNome, boolean novoStatus) {
        if(c == null) throw new IllegalArgumentException("Cliente não pode ser nulo");

        if(novoNome != null) {
            c.setNome(novoNome);
        }
        c.setPreferencial(novoStatus);
    }

    /**
     * Modifica status da vaga
     */
    public void modificarVaga(Vaga v, StatusVaga novoStatus) {
        if(v == null) throw new IllegalArgumentException("Vaga não pode ser nula");
        v.setStatus(novoStatus);
    }
}