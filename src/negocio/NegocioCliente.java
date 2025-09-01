package negocio;

import dados.cliente.IRepositorioClientes;
import negocio.entidade.ClienteUsuario; // <--- MUDANÇA
import negocio.excecao.usuario.UsuarioExistenteException;
import negocio.excecao.usuario.UsuarioNaoExisteException;

public class NegocioCliente {

    private IRepositorioClientes repositorio;

    public NegocioCliente(IRepositorioClientes repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionar(ClienteUsuario cliente) throws UsuarioExistenteException { // <--- MUDANÇA
        boolean existe = repositorio.existeCPF(cliente.getCpf());
        if (existe) {
            throw new UsuarioExistenteException();
        } else {
            repositorio.adicionar(cliente);
        }
    }

    public void remover(String cpf) throws UsuarioNaoExisteException {
        ClienteUsuario cliente = repositorio.consultarCPF(cpf); // <--- MUDANÇA
        if (cliente != null) {
            repositorio.remover(cliente);
        } else {
            throw new UsuarioNaoExisteException();
        }
    }

    public ClienteUsuario consultarCPF(String cpf) throws UsuarioNaoExisteException { // <--- MUDANÇA
        ClienteUsuario cliente = repositorio.consultarCPF(cpf); // <--- MUDANÇA
        if (cliente == null) {
            throw new UsuarioNaoExisteException();
        } else {
            return cliente;
        }
    }

    public ClienteUsuario consultarPlaca(String placa) throws UsuarioNaoExisteException { // <--- MUDANÇA
        ClienteUsuario cliente = repositorio.consultarPlaca(placa); // <--- MUDANÇA
        if (cliente == null) {
            throw new UsuarioNaoExisteException();
        } else {
            return cliente;
        }
    }
}