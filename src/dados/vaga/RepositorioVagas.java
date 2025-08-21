package dados.vaga;

import negocio.entidade.Vaga;

import java.util.ArrayList;

public class RepositorioVagas implements IRepositorioVagas {

    private ArrayList<Vaga> array;

    public RepositorioVagas() {
        array = new ArrayList<Vaga>();
    }

    @Override
    public void adicionar(Vaga vaga) {
        array.add(vaga);}

    @Override
    public void remover(Vaga vaga) {
        int indice = array.indexOf(vaga);
        if (indice != -1) {
            array.remove(vaga);
        }
    }

    @Override
    public boolean existeID(int id) {
        for (Vaga vaga : array) {
            if(vaga.getNumeroID().equals(id)){
                return true;
        }
        return false;
    }

    @Override
    public ArrayList consultarAtivas(){
        ArrayList<Vaga> arrayOcupadas;
        for (Vaga vaga : array) {
            array.add(vaga);
        }
        return arrayOcupadas;
    }

    @Override
    public void listar() {
        for (Vaga vaga : array) {
            System.out.println(vaga);
        }
    }
        return resultado;
    }

}