package dados.vaga;

import negocio.entidade.Vaga;

public interface IRepositorioVaga{

    void adicionar(Vaga vaga);

    void remover(Vaga vaga);

    boolean isPCD(Vaga vaga);

    ArrayList consultarAtivas();

    void listar();

}