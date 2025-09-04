package negocio.entidade;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    // Adicionando uma constante para o valor da hora. Fica mais fácil de alterar depois.
    private static final double VALOR_POR_HORA = 5.00; // R$ 5,00 por hora

    private String nome;
    private List<Vaga> vagas;
    private List<Ticket> ticketsAtivos; // Tickets de carros que estão DENTRO
    private List<Ticket> historicoTickets; // Tickets de carros que já SAÍRAM

    public Estacionamento(String nome, int quantidadeVagas) {
        this.nome = nome;
        this.vagas = new ArrayList<>();
        this.ticketsAtivos = new ArrayList<>();
        this.historicoTickets = new ArrayList<>(); // Inicializa o histórico
        for (int i = 1; i <= quantidadeVagas; i++) {
            vagas.add(new Vaga("Vaga-" + i));
        }
    }

    // --- MÉTODOS EXISTENTES ---

    /**
     * Verifica se um veículo já se encontra fisicamente no estacionamento.
     */
    private boolean isVeiculoDentro(Veiculo veiculo) {
        for (Ticket ticket : ticketsAtivos) {
            if (ticket.getVeiculo().equals(veiculo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procura a primeira vaga que estiver livre.
     */
    public Vaga procurarVagaLivre() {
        for (Vaga vaga : vagas) {
            if (vaga.isLivre()) {
                return vaga;
            }
        }
        return null;
    }

    /**
     * Lógica principal para a entrada de um veículo.
     */
    public Ticket registrarEntrada(Veiculo veiculo) {
        if (isVeiculoDentro(veiculo)) {
            System.out.println("ACESSO NEGADO: Veículo " + veiculo.getPlaca() + " já está no estacionamento.");
            return null;
        }

        Vaga vagaLivre = procurarVagaLivre();
        if (vagaLivre == null) {
            System.out.println("ACESSO NEGADO: Estacionamento lotado.");
            return null;
        }

        System.out.println("ACESSO PERMITIDO: Abrindo cancela para " + veiculo.getPlaca());
        vagaLivre.ocupar();
        Ticket novoTicket = new Ticket(veiculo, vagaLivre);
        ticketsAtivos.add(novoTicket);
        return novoTicket;
    }


    // --- NOVOS MÉTODOS ---

    /**
     * Encontra um ticket ativo a partir da placa do veículo.
     * @param placa A placa do veículo a ser procurado.
     * @return O Ticket correspondente, ou null se não for encontrado.
     */
    public Ticket encontrarTicketPorPlaca(String placa) {
        for (Ticket ticket : ticketsAtivos) {
            if (ticket.getVeiculo().getPlaca().equalsIgnoreCase(placa)) {
                return ticket;
            }
        }
        return null; // Não encontrou veículo com essa placa entre os ativos
    }

    /**
     * Lógica principal para a saída de um veículo.
     * Calcula o valor a ser pago, finaliza o ticket e libera a vaga.
     * @param placa A placa do veículo que está saindo.
     * @return O valor a ser pago, ou -1 em caso de erro (veículo não encontrado).
     */
    public double registrarSaida(String placa) {
        Ticket ticket = encontrarTicketPorPlaca(placa);

        if (ticket == null) {
            System.out.println("ERRO: Veículo com a placa " + placa + " não encontrado no estacionamento.");
            return -1; // Retorna um valor de erro
        }

        LocalDateTime horarioSaida = LocalDateTime.now();
        LocalDateTime horarioEntrada = ticket.getHorarioEntrada();

        // 1. Calcula a duração que o veículo ficou estacionado
        Duration duracao = Duration.between(horarioEntrada, horarioSaida);
        long minutosTotais = duracao.toMinutes();

        // 2. Calcula o número de horas a cobrar (arredondando para cima)
        // Ex: 61 minutos vira 1.016, Math.ceil arredonda para 2 horas.
        long horasACobrar = (long) Math.ceil(minutosTotais / 60.0);

        // Se ficou menos de uma hora, cobra 1 hora
        if (horasACobrar == 0) {
            horasACobrar = 1;
        }

        // 3. Calcula o valor final
        double valorFinal = horasACobrar * VALOR_POR_HORA;

        // 4. Registra a saída no objeto Ticket (isso também libera a vaga)
        ticket.registrarSaida(valorFinal, horarioSaida);

        // 5. Move o ticket da lista de ativos para o histórico
        this.ticketsAtivos.remove(ticket);
        this.historicoTickets.add(ticket);

        System.out.println("Saída registrada para " + placa + ". Valor: R$" + String.format("%.2f", valorFinal));

        return valorFinal;
    }


    // --- GETTERS ---

    /**
     * Retorna o nome do estacionamento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a lista de todas as vagas do estacionamento.
     */
    public List<Vaga> getVagas() {
        return vagas;
    }

    /**
     * Retorna a lista de tickets dos veículos que estão atualmente no estacionamento.
     */
    public List<Ticket> getTicketsAtivos() {
        return ticketsAtivos;
    }

    /**
     * Retorna o histórico de todos os tickets já finalizados.
     */
    public List<Ticket> getHistoricoTickets() {
        return historicoTickets;
    }
}