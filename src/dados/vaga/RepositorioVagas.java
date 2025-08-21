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
    public ArrayList consultarAtivas(){
        ArrayList<Vaga> arrayOcupadas = null;
        for (Vaga vaga : array) {
            if(vaga.isOcupada()){
                arrayOcupadas.add(vaga);
            }
        }
        return arrayOcupadas;
    }

    @Override
    public ArrayList listar() {
        return array;
    }

    @Override
    public boolean isPCD(Vaga vaga) {
        return vaga.isPCD();
    }

    @Override
    public boolean existeID(String id) {
        for (Vaga vaga : array) {
            if(vaga.getNumeroID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vaga consultar(String id) {
        for (Vaga vaga : array) {
            if (vaga.getNumeroID().equals(id)) {
                return vaga;
            }
        }
        return null;
    }

}