package dados.veiculo;

import negocio.entidade.Veiculo;

public interface IRepositorioVeiculo{

    void adicionar(Veiculo veiculo);

    void remover(Veiculo veiculo);

    Veiculo consultarPlaca(String placa);

    Veiculo consultarDono(Cliente dono);

    void listar();

    boolean existePlaca(String placa);

}