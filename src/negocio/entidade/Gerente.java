package negocio.entidade;
import java.io.Serializable;
import java.time.LocalDate;


public class Gerente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;


    public Gerente(String nome, String cpf) {
        super(nome, cpf); // Chama o construtor de Usuario com os dois parâmetros necessários.
    }


    public String gerarRelatorio(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês inválido. Use um valor entre 1 e 12.");
        }

        // Exemplo de como gerar um relatório. Os valores são fixos por enquanto.
        return String.format(
                "RELATÓRIO MENSAL - %02d/%d\n" +
                        "Total de vagas: %d\n" +
                        "Ocupação média: %.1f%%",
                mes,
                LocalDate.now().getYear(),
                50,  // Valor de exemplo
                75.5 // Valor de exemplo
        );
    }


    public void modificarCliente(ClienteUsuario cliente, String novoNome, boolean novoStatus) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }

        if (novoNome != null && !novoNome.isEmpty()) {
            cliente.setNome(novoNome);
        }
        cliente.setPreferencial(novoStatus);
    }

    /**
     * Modifica o status de uma vaga.
     * (As classes 'Vaga' e 'StatusVaga' precisam ser criadas).
     *
     * @param vaga       A vaga a ser modificada.
     * @param novoStatus O novo status da vaga.
     */
    public void modificarVaga(Vaga vaga, StatusVaga novoStatus) {
        if (vaga == null) {
            throw new IllegalArgumentException("Vaga não pode ser nula.");
        }
        // Exemplo de implementação:
        // vaga.setStatus(novoStatus);
    }
}