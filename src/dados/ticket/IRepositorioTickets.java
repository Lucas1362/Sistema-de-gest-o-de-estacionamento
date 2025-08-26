package dados.ticket;

import negocio.entidade.Ticket;

import java.util.ArrayList;

public interface IRepositorioTickets {

    void adicionar(Ticket ticket);

    void remover(Ticket ticket);

    Ticket consultar(String placa);

    ArrayList consultarAtivos();

    ArrayList listar();

    boolean existePlaca(String placa);

}