
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
}
        // Classes auxiliares (dentro do mesmo arquivo ou em arquivos separados)
        class Ticket {
            private Veiculo veiculo;
            private Vaga vaga;
            private LocalDateTime entrada;
            private LocalDateTime saida;

            // Construtor, getters e setters
            // Método finalizar()
        }
        class EstacionamentoException extends Exception {
            public EstacionamentoException(String message) {
                super(message);
            }
        }