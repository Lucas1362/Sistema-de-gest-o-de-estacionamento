package dados.ticket;

import negocio.entidade.Ticket;

import java.util.ArrayList;

public interface IRepositorioTickets {

    void adicionar(Ticket ticket);

    void remover(Ticket ticket);

    Ticket consultar(String placa);

    ArrayList consultarAtivos();

    void listar();

    boolean existePlaca(String placa);

}