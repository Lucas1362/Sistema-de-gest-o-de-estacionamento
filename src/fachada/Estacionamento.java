import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Estacionamento {
    private List<Vaga> vagas;
    private List<Ticket> ticketsEncerrados;
    private double valorHora;
    private double valorDiaria;

    public Estacionamento(int totalVagas, double valorHora, double valorDiaria) {
        this.vagas = new ArrayList<>();
        this.ticketsEncerrados = new ArrayList<>();
        this.valorHora = valorHora;
        this.valorDiaria = valorDiaria;

        // Inicializa as vagas
        for (int i = 1; i <= totalVagas; i++) {
            vagas.add(new Vaga("A-" + String.format("%02d", i)));
        }
    }

    // Método para registrar a entrada de um veículo
    public Ticket registrarEntrada(Veiculo veiculo) throws EstacionamentoException {
        Vaga vagaLivre = encontrarVagaLivre();
        if (vagaLivre == null) {
            throw new EstacionamentoException("Não há vagas disponíveis!");
        }

        Ticket ticket = new Ticket(veiculo, vagaLivre, LocalDateTime.now());
        vagaLivre.ocupar(veiculo);
        return ticket;
    }

    // Método para registrar a saída de um veículo
    public double registrarSaida(Ticket ticket) throws EstacionamentoException {
        if (ticket == null || ticket.getSaida() != null) {
            throw new EstacionamentoException("Ticket inválido ou já finalizado!");
        }

        ticket.setSaida(LocalDateTime.now());
        calcularValor(ticket);
        ticketsEncerrados.add(ticket);
        ticket.getVaga().liberar();
        return ticket.getValor();
    }

    // Cálculo do valor estacionado
    private void calcularValor(Ticket ticket) {
        Duration duracao = Duration.between(ticket.getEntrada(), ticket.getSaida());
        long horas = duracao.toHours();
        long dias = duracao.toDays();

        // Lógica de cobrança
        if (dias > 0) {
            ticket.setValor(dias * valorDiaria + Math.min(horas % 24 * valorHora, valorDiaria));
        } else {
            ticket.setValor(Math.max(horas, 1) * valorHora); // Mínimo 1 hora
        }
    }

    // Métodos de relatório
    public List<Vaga> getVagasLivres() {
        return vagas.stream().filter(Vaga::estaLivre).toList();
    }

    public List<Vaga> getVagasOcupadas() {
        return vagas.stream().filter(v -> !v.estaLivre()).toList();
    }

    public double getFaturamentoDiario() {
        return ticketsEncerrados.stream()
                .filter(t -> t.getSaida().toLocalDate().equals(LocalDateTime.now().toLocalDate()))
                .mapToDouble(Ticket::getValor)
                .sum();
    }

    // Método auxiliar para encontrar uma vaga livre
    private Vaga encontrarVagaLivre() {
        return vagas.stream().filter(Vaga::estaLivre).findFirst().orElse(null);
    }

    // Classes auxiliares
    class Ticket {
        private Veiculo veiculo;
        private Vaga vaga;
        private LocalDateTime entrada;
        private LocalDateTime saida;
        private double valor;

        public Ticket(Veiculo veiculo, Vaga vaga, LocalDateTime entrada) {
            this.veiculo = veiculo;
            this.vaga = vaga;
            this.entrada = entrada;
            this.saida = null; // Inicialmente, a saída é nula
        }

        public void setSaida(LocalDateTime saida) {
            this.saida = saida;
        }

        public LocalDateTime getEntrada() {
            return entrada;
        }

        public LocalDateTime getSaida() {
            return saida;
        }

        public Vaga getVaga() {
            return vaga;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }
    }

    class EstacionamentoException extends Exception {
        public EstacionamentoException(String message) {
            super(message);
        }
    }
}
