package dados.veiculo;

import negocio.entidade.Veiculo;

import java.util.ArrayList;

public class RepositorioVeiculo implements IRepositorioVeiculo {

    private ArrayList<Veiculo> array;

    public RepositorioVeiculos() {
        array = new ArrayList<Veiculo>();
    }

    @Override
    public void adicionar(Veiculo veiculo) {
        array.add(veiculo);
    }

    @Override
    public void remover(Veiculo veiculo) {
        int indice = array.indexOf(veiculo);
        if (indice != -1) {
            array.remove(veiculo);
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