package dados.gerente;

import negocio.entidade.Gerente;

import java.util.ArrayList;

public class RepositorioGerentes implements IRepositorioGerentes {

    private ArrayList<Gerente> array;

    public RepositorioGerentes() {
        array = new ArrayList<Gerente>();
    }

    @Override
    public void adicionar(Gerente gerente) {
        array.add(gerente);
    }

    @Override
    public void remover(Gerente gerente) {
        int indice = array.indexOf(gerente);
        if (indice != -1) {
            array.remove(gerente);
        }
    }

    @Override
    public Gerente consultarCPF(String cpf) {
        Gerente gerenteProcurado = null;
        for (Gerente gerente : array) {
            if (gerente.getCpf().equals(cpf)) {
                gerenteProcurado = gerente;
                break;
            }
        }
        return gerenteProcurado;
    }

    @Override
    public void listar() {
        for (Gerente gerente : array) {
            System.out.println(gerente);
        }
    }

    @Override
    public boolean existeCPF(String cpf) {
        boolean resultado = false;
        for (Gerente gerente : array) {
            if (gerente.getCpf().equals(cpf)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

}