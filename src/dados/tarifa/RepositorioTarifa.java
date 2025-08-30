package dados.tarifa;

import negocio.entidade.Tarifa;
import java.io.*;

public class RepositorioTarifa {
    private static final String TARIFA_DATA = "tarifa.dat";
    private Tarifa tarifa;

    public RepositorioTarifa() {
        carregarDados();
        if (this.tarifa == null) {
            this.tarifa = new Tarifa(5.00);
            salvarDados();
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TARIFA_DATA))) {
            oos.writeObject(this.tarifa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TARIFA_DATA))) {
            this.tarifa = (Tarifa) ois.readObject();
        } catch (Exception e) {
            this.tarifa = null;
        }
    }

    public double getValorAtual() {
        return this.tarifa.getValorPorHora();
    }

    public void setNovoValor(double novoValor) {
        this.tarifa.setValorPorHora(novoValor);
        salvarDados();
    }
}
