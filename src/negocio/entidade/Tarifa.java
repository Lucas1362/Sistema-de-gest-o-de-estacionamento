package negocio.entidade;
import java.io.Serializable;

public class Tarifa implements Serializable {
    private static final long serialVersionUID = 1L;
    private double valorPorHora;

    public Tarifa(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }
}
