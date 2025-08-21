package negocio;

import dados.cliente.IRepositorioClientes;
import negocio.entidade.Cliente;
import negocio.excecao.cliente.ClienteExistenteException;
import negocio.excecao.cliente.ClienteNaoExisteException;

public class NegocioCliente {

    private IRepositorioClientes repositorio;

    public NegocioCliente(IRepositorioClientes repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionar(Cliente cliente) throws ClienteJaExisteException {
        boolean existe = repositorio.existeCPF(cliente.getCpf());
        if (existe) {
            throw new ClienteJaExisteException();
        } else {
            repositorio.adicionar(cliente);
        }
    }

    public void remover(String cpf) throws ClienteNaoExisteException {
        Cliente cliente = repositorio.consultarCPF(cpf);
        if (cliente != null) {
            repositorio.remover(cliente);
        } else {
            throw new ClienteNaoExisteException();
        }
    }

    public Cliente consultarCPF(String cpf) throws ClienteNaoExisteException {
        Cliente cliente = repositorio.consultarCPF(cpf);
        if (cliente == null) {
            throw new ClienteNaoExisteException();
        } else {
            return cliente;
        }
    }

    public Cliente consultarPlaca(String placa) throws ClienteNaoExisteException {
        Cliente cliente = repositorio.consultarPlaca(placa);
        if (cliente == null) {
            throw new ClienteNaoExisteException();
        } else {
            return cliente;
        }
    }

}