package dados.cliente;

import negocio.entidade.Cliente;
import java.io.*;
import java.util.ArrayList;

public class RepositorioClientes implements IRepositorioClientes {
    private static final String  Clientes_Data = "clientes.dat";
    private ArrayList<Cliente> array;

    public RepositorioClientes() {
        array = new ArrayList<Cliente>();
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

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Clientes_Data))) {
            this.array = (ArrayList<Cliente>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //fim

    @Override
    public void adicionar(Cliente cliente) {
        array.add(cliente);
        salvarDados();
    }

    @Override
    public void remover(Cliente cliente) {
        int indice = array.indexOf(cliente);
        if (indice != -1) {
            array.remove(cliente);
            salvarDados();
        }
    }

    @Override
    public Cliente consultarCPF(String cpf) {
        Cliente clienteProcurado = null;
        for (Cliente cliente : array) {
            if (cliente.getCpf().equals(cpf)) {
                clienteProcurado = cliente;
                break;
            }
        }
        return clienteProcurado;
    }

    @Override
    public Cliente consultarPlaca(String placa) {
        Cliente clienteProcurado = null;
        for (Cliente cliente : array) {
            if (cliente.getVeiculo() != null && cliente.getVeiculo().getPlaca().equals(placa)) {
                clienteProcurado = cliente;
                break;
            }
        }
        return clienteProcurado;
    }

    @Override
    public void listar() {
        for (Cliente cliente : array) {
            System.out.println(cliente);
        }
    }

    @Override
    public boolean existeCPF(String cpf) {
        boolean resultado = false;
        for (Cliente cliente : array) {
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
        for (Cliente cliente : array) {
            if (cliente.getVeiculo() != null && cliente.getVeiculo().getPlaca().equals(placa)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

}