package dados.cliente;

import negocio.entidade.Cliente;

public interface IRepositorioClientes {

    void adicionar(Cliente cliente);

    void remover(Cliente cliente);

    Cliente consultarPlaca(String placa);

    Cliente consultarCPF(String cpf);

    void listar();

    boolean existeCPF(String cpf);

    boolean existePlaca(String placa);

}