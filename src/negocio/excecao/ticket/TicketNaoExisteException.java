package negocio.excecao.ticket;

@SuppressWarnings("serial")
public class TicketNaoExisteException extends TicketException {
    public TicketNaoExisteException() {
        super("Ticket não encontrado.");
    }
}