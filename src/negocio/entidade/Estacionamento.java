package negocio.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Estacionamento {

    private List<Vaga> vagas;
    private List<Ticket> ticketsAtivos;

    /**
     * Construtor do Estacionamento.
     * Inicializa as listas de vagas e tickets como listas vazias e seguras para concorrência.
     */
    public Estacionamento() {
        this.vagas = new ArrayList<>();
        this.ticketsAtivos = new ArrayList<>();
        // Futuramente, poderíamos inicializar o estacionamento com um número fixo de vagas.
    }

    // --- Métodos de Gerenciamento ---


    public void adicionarVaga(Vaga vaga) {
        this.vagas.add(vaga);
    }


    public void adicionarTicket(Ticket ticket) {
        this.ticketsAtivos.add(ticket);
    }

    // --- Getters ---

    /**
     * Retorna uma visão não modificável da lista de vagas.
     * Isso protege a lista original de modificações externas.
     * @return A lista de vagas.
     */
    public List<Vaga> getVagas() {
        return Collections.unmodifiableList(vagas);
    }

    /**
     * Retorna uma visão não modificável da lista de tickets ativos.
     * @return A lista de tickets ativos.
     */
    public List<Ticket> getTicketsAtivos() {
        return Collections.unmodifiableList(ticketsAtivos);
    }
}