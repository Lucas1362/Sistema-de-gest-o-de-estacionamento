package negocio;

import dados.cliente.IRepositorioClientes;
import negocio.entidade.Cliente;
import negocio.excecao.usuario.UsuarioExistenteException;
import negocio.excecao.usuario.UsuarioNaoExisteException;

public class NegocioCliente {

    private IRepositorioClientes repositorio;

    public NegocioCliente(IRepositorioClientes repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionar(Cliente cliente) throws UsuarioExistenteException {
        boolean existe = repositorio.existeCPF(cliente.getCpf());
        if (existe) {
            throw new UsuarioExistenteException();
        } else {
            repositorio.adicionar(cliente);
        }
    }

    public void remover(String cpf) throws UsuarioNaoExisteException {
        Cliente cliente = repositorio.consultarCPF(cpf);
        if (cliente != null) {
            repositorio.remover(cliente);
        } else {
            throw new UsuarioNaoExisteException();
        }
    }

    public Cliente consultarCPF(String cpf) throws UsuarioNaoExisteException {
        Cliente cliente = repositorio.consultarCPF(cpf);
        if (cliente == null) {
            throw new UsuarioNaoExisteException();
        } else {
            return cliente;
        }
    }

    public Cliente consultarPlaca(String placa) throws UsuarioNaoExisteException {
        Cliente cliente = repositorio.consultarPlaca(placa);
        if (cliente == null) {
            throw new UsuarioNaoExisteException();
        } else {
            return cliente;
        }
    }

    public void alterarStatusPCD(String cpf, boolean novoStatus) throws UsuarioNaoExisteException {
        Cliente cliente = this.consultarCPF(cpf);
        cliente.setPreferencial(novoStatus);
    }

}