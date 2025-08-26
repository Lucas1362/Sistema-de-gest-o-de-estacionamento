package dados.veiculo;

import negocio.entidade.Cliente;
import negocio.entidade.Veiculo;
import java.io.*;
import java.util.ArrayList;

public class RepositorioVeiculos implements IRepositorioVeiculos {
    private static final String  Veiculos_Data = "veiculos.dat";
    private ArrayList<Veiculo> array;

    public RepositorioVeiculos() {
        array = new ArrayList<Veiculo>();
        carregarDados();
    }

    //Parte de Dados em arquivo
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Veiculos_Data))) {
            oos.writeObject(this.array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Veiculos_Data))) {
            this.array = (ArrayList<Veiculo>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //fim

    @Override
    public void adicionar(Veiculo veiculo) {
        array.add(veiculo);
        salvarDados();
    }

    @Override
    public void remover(Veiculo veiculo) {
        int indice = array.indexOf(veiculo);
        if (indice != -1) {
            array.remove(veiculo);
            salvarDados();
        }
    }

    @Override
    public Veiculo consultarPlaca(String placa) {
        Veiculo veiculoProcurado = null;
        for (Veiculo veiculo : array) {
            if (veiculo.getPlaca().equals(placa)) {
                veiculoProcurado = veiculo;
                break;
            }
        }
        return veiculoProcurado;
    }

    @Override
    public void listar() {
        for (Veiculo veiculo : array) {
            System.out.println(veiculo);
        }
    }

    @Override
    public boolean existePlaca(String placa) {
        boolean resultado = false;
        for (Veiculo veiculo : array) {
            if (veiculo.getPlaca().equals(placa)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

}