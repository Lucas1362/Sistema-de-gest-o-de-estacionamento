package dados.ticket;

import negocio.entidade.Cliente;
import negocio.entidade.Ticket;
import java.io.*;
import java.util.ArrayList;

public class RepositorioTickets implements IRepositorioTickets {
    private static final String  Tickets_Data = "tickets.dat";
    private ArrayList<Ticket> array;

    public RepositorioTickets() {
        array = new ArrayList<Ticket>();
        carregarDados();
    }

    //Parte de Dados em arquivo
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Tickets_Data))) {
            oos.writeObject(this.array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Tickets_Data))) {
            this.array = (ArrayList<Ticket>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //fim

    @Override
    public void adicionar(Ticket ticket) {
        array.add(ticket);
        salvarDados();
    }

    @Override
    public void remover(Ticket ticket) {
        int indice = array.indexOf(ticket);
        if (indice != -1) {
            array.remove(ticket);
            salvarDados();
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