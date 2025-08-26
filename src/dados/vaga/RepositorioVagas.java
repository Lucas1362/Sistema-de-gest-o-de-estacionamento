package dados.vaga;

import negocio.entidade.Cliente;
import negocio.entidade.Vaga;
import java.io.*;
import java.util.ArrayList;

public class RepositorioVagas implements IRepositorioVagas {
    private static final String  Vagas_Data = "vagas.dat";
    private ArrayList<Vaga> array;

    public RepositorioVagas() {
        array = new ArrayList<Vaga>();
        carregarDados();
    }

    //Parte de Dados em arquivo
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Vagas_Data))) {
            oos.writeObject(this.array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Vagas_Data))) {
            this.array = (ArrayList<Vaga>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //fim

    @Override
    public void adicionar(Vaga vaga) {
        array.add(vaga);
        salvarDados();
    }

    @Override
    public void remover(Vaga vaga) {
        int indice = array.indexOf(vaga);
        if (indice != -1) {
            array.remove(vaga);
            salvarDados();
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