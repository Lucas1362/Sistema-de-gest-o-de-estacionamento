package dados.cliente;

import negocio.entidade.Cliente;

import java.util.ArrayList;

public class RepositorioClientes implements IRepositorioClientes {

    private ArrayList<Cliente> array;

    public RepositorioClientes() {
        array = new ArrayList<Cliente>();
    }

    @Override
    public void adicionar(Cliente cliente) {
        array.add(cliente);
    }

    @Override
    public void remover(Cliente cliente) {
        int indice = array.indexOf(cliente);
        if (indice != -1) {
            array.remove(cliente);
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
    public Cliente consultar(String placa) {
        Cliente clienteProcurado = null;
        for (Cliente cliente : array) {
            if (cliente.getVeiculo().getPlaca().equals(placa)) {
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
            if (cliente.getVeiculo().getPlaca().equals(placa)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

}