// Exemplo de como Veiculo.java precisaria ser ajustado
package negocio.entidade;

import java.util.Objects; // Importar para usar Objects.hash e Objects.equals

public class Veiculo {
    private String placa;
    private Cliente dono; // Alterado de String para Cliente, se for o caso

    public Veiculo(String placa, Cliente dono) {
        this.placa = placa;
        this.dono = dono;
    }

    // getters
    public String getPlaca() {
        return placa;
    }

    public Cliente getDono() { // Retorna Cliente, não String
        return dono;
    }

    // setters
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return placa.equalsIgnoreCase(veiculo.placa); // Comparação case-insensitive para placas
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa.toLowerCase()); // Hash baseado na placa em minúsculas
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", dono=" + (dono != null ? dono.getNome() : "N/A") +
                '}';
    }
}
