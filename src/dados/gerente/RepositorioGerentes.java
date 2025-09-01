package dados.gerente;

import negocio.entidade.Cliente;
import negocio.entidade.Gerente;
import java.io.*;
import java.util.ArrayList;

public class RepositorioGerentes implements IRepositorioGerentes {
    private static final String  Gerentes_Data = "gerentes.dat";
    private ArrayList<Gerente> array;

    public RepositorioGerentes() {
        array = new ArrayList<Gerente>();
        carregarDados();
    }

    //Parte de Dados em arquivo
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Gerentes_Data))) {
            oos.writeObject(this.array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Gerentes_Data))) {
            this.array = (ArrayList<Gerente>) ois.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //fim

    @Override
    public void adicionar(Gerente gerente) {
        array.add(gerente);
        salvarDados();
    }

    @Override
    public void remover(Gerente gerente) {
        int indice = array.indexOf(gerente);
        if (indice != -1) {
            array.remove(gerente);
            salvarDados();
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