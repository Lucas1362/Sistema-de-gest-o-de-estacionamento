package dados.ticket;

import negocio.entidade.Ticket;

public interface IRepositorioTicket{

    void adicionar(Ticket ticket);

    void remover(Ticket ticket);

    Ticket consultar(String placa);

    ArrayList consultarAtivos();

    void listar();

    boolean existePlaca(String placa);

}