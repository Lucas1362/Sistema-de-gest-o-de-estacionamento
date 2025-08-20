package dados.gerente;

import negocio.entidade.Gerente;

public interface IRepositorioGerente{

    void adicionar(Gerente gerente);

    void remover(Gerente gerente);

    Gerente consultarCPF(String cpf);

    void listar();

    boolean existeCPF(String cpf);

}