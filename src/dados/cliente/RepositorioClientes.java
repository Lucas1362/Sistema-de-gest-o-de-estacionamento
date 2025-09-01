package dados.cliente;

import negocio.entidade.ClienteUsuario; // <--- MUDANÇA
import java.io.*;
import java.util.ArrayList;

public class RepositorioClientes implements IRepositorioClientes {
    private static final String  Clientes_Data = "clientes.dat";
    private ArrayList<ClienteUsuario> array; // <--- MUDANÇA

    public RepositorioClientes() {
        array = new ArrayList<ClienteUsuario>(); // <--- MUDANÇA
        carregarDados();
    }

    //Parte de Dados em arquivo
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Clientes_Data))) {
            oos.writeObject(this.array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked") // Adicionado para suprimir o warning do cast
    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Clientes_Data))) {
            this.array = (ArrayList<ClienteUsuario>) ois.readObject(); // <--- MUDANÇA
        } catch (FileNotFoundException e) {
            // Arquivo não encontrado, começa com uma lista vazia (normal na primeira execução)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //fim

    @Override
    public void adicionar(ClienteUsuario cliente) { // <--- MUDANÇA
        array.add(cliente);
        salvarDados();
    }

    @Override
    public void remover(ClienteUsuario cliente) { // <--- MUDANÇA
        int indice = array.indexOf(cliente);
        if (indice != -1) {
            array.remove(cliente);
            salvarDados();
        }
    }

    @Override
    public ClienteUsuario consultarCPF(String cpf) { // <--- MUDANÇA (retorno)
        ClienteUsuario clienteProcurado = null; // <--- MUDANÇA
        for (ClienteUsuario cliente : array) { // <--- MUDANÇA
            if (cliente.getCpf().equals(cpf)) {
                clienteProcurado = cliente;
                break;
            }
        }
        return clienteProcurado;
    }

    @Override
    public ClienteUsuario consultarPlaca(String placa) { // <--- MUDANÇA (retorno)
        ClienteUsuario clienteProcurado = null; // <--- MUDANÇA
        for (ClienteUsuario cliente : array) { // <--- MUDANÇA
            if (cliente.getVeiculo() != null && cliente.getVeiculo().getPlaca().equals(placa)) {
                clienteProcurado = cliente;
                break;
            }
        }
        return clienteProcurado;
    }

    @Override
    public void listar() {
        for (ClienteUsuario cliente : array) { // <--- MUDANÇA
            System.out.println(cliente);
        }
    }

    @Override
    public boolean existeCPF(String cpf) {
        boolean resultado = false;
        for (ClienteUsuario cliente : array) { // <--- MUDANÇA
            if (cliente.getCpf().equals(cpf)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

    @Override
    public boolean existePlaca(String placa) {
        boolean resultado = false;
        for (ClienteUsuario cliente : array) { // <--- MUDANÇA
            if (cliente.getVeiculo() != null && cliente.getVeiculo().getPlaca().equals(placa)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }
}