package dados.vaga;

import negocio.entidade.Vaga;

import java.util.ArrayList;

public interface IRepositorioVagas{

    void adicionar(Vaga vaga);

    void remover(Vaga vaga);

    boolean isPCD(Vaga vaga);

    boolean existeID(String id);

    ArrayList consultarAtivas();

    void listar();

}