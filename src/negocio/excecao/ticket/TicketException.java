package negocio.excecao.ticket;

@SuppressWarnings("serial")
public class TicketException extends Exception {
    public TicketException(String msg) {
        super(msg);
    }
}