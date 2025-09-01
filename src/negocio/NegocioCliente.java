package negocio;

import dados.cliente.IRepositorioClientes;
import negocio.entidade.Cliente;
import negocio.excecao.usuario.UsuarioExistenteException;
import negocio.excecao.usuario.UsuarioNaoExisteException;
import negocio.excecao.cpf.CpfApenasNumerosException;
import negocio.excecao.cpf.CpfTamanhoException;

public class NegocioCliente {

    private IRepositorioClientes repositorio;

    public NegocioCliente(IRepositorioClientes repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionar(Cliente cliente) throws UsuarioExistenteException, CpfTamanhoException, CpfApenasNumerosException {
        String cpf = cliente.getCpf();
        // REGRA 1: Verifica se o CPF contém apenas números.
        if (!cpf.matches("[0-9]+")) {
            throw new CpfApenasNumerosException();
        }
        // REGRA 2: Verifica se o CPF tem exatamente 11 dígitos.
        if (cpf.length() != 11) {
            throw new CpfTamanhoException();
        }
        boolean existe = repositorio.existeCPF(cpf);
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