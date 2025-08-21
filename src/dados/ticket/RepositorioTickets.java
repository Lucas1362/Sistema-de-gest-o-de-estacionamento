package dados.ticket;

import negocio.entidade.Ticket;

import java.util.ArrayList;

public class RepositorioTickets implements IRepositorioTickets {

    private ArrayList<Ticket> array;

    public RepositorioTickets() {
        array = new ArrayList<Ticket>();
    }

    @Override
    public void adicionar(Ticket ticket) {
        array.add(ticket);
    }

    @Override
    public void remover(Ticket ticket) {
        int indice = array.indexOf(ticket);
        if (indice != -1) {
            array.remove(ticket);
        }
    }

    @Override
    public Ticket consultar(String placa) {
        Ticket ticketProcurado = null;
        for (Ticket ticket : array) {
            if (ticket.getVeiculo().getPlaca().equals(placa)) {
                ticketProcurado = ticket;
                break;
            }
        }
        return ticketProcurado;
    }

    @Override
    public void listar() {
        for (Ticket ticket : array) {
            System.out.println(ticket);
        }
    }

    @Override
    public boolean existePlaca(String placa) {
        boolean resultado = false;
        for (Ticket ticket : array) {
            if (ticket.getVeiculo().getPlaca().equals(placa)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

    @Override
    public ArrayList consultarAtivos(){
        ArrayList<Ticket> ticketsAtivos = null;
        for (Ticket ticket : array) {
            if (ticket.isAtivo()) {
                ticketsAtivos.add(ticket);
            }
        }
        return ticketsAtivos;
    }

}