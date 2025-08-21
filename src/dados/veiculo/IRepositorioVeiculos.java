package dados.veiculo;

import negocio.entidade.Veiculo;

import negocio.entidade.Cliente;

public interface IRepositorioVeiculos{

    void adicionar(Veiculo veiculo);

    void remover(Veiculo veiculo);

    Veiculo consultarPlaca(String placa);

    void listar();

    boolean existePlaca(String placa);

}