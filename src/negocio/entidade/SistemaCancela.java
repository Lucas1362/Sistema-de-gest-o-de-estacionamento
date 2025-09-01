package negocio.entidade;

import java.util.HashSet;
import java.util.Set;

class Veiculo {
    private String placa;

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veiculo veiculo = (Veiculo) obj;
        return placa.equals(veiculo.placa);
    }

    @Override
    public int hashCode() {
        return placa.hashCode();
    }
}

class Estacionamento {
    private int capacidade;
    private Set<Veiculo> cadastrados;
    private Set<Veiculo> dentro;

    public Estacionamento(int capacidade) {
        this.capacidade = capacidade;
        this.cadastrados = new HashSet<>();
        this.dentro = new HashSet<>();
    }

    public boolean estaCheio() {
        return dentro.size() >= capacidade;
    }

    public void cadastrarVeiculo(Veiculo v) {
        cadastrados.add(v);
    }

    public boolean isVeiculoCadastrado(Veiculo v) {
        return cadastrados.contains(v);
    }

    public boolean isVeiculoDentro(Veiculo v) {
        return dentro.contains(v);
    }

    public void registrarEntrada(Veiculo v) {
        if (!estaCheio() && isVeiculoCadastrado(v)) {
            dentro.add(v);
        }
    }

    public void registrarSaida(Veiculo v) {
        dentro.remove(v);
    }
}

public class SistemaCancela {
    private Estacionamento estacionamento;

    public SistemaCancela(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public void liberarEntrada(Veiculo veiculo) {
        if (verificarAutorizacao(veiculo)) {
            System.out.println("Cancela aberta para: " + veiculo.getPlaca());
            estacionamento.registrarEntrada(veiculo);
        } else {
            System.out.println("Acesso negado para: " + veiculo.getPlaca());
        }
    }

    private boolean verificarAutorizacao(Veiculo veiculo) {
        if (estacionamento.estaCheio()) {
            return false;
        }
        if (!estacionamento.isVeiculoCadastrado(veiculo)) {
            return false;
        }
        if (estacionamento.isVeiculoDentro(veiculo)) {
            return false;
        }
        return true;
    }

    // Método main só para teste rápido
    public static void main(String[] args) {
        Estacionamento est = new Estacionamento(2);
        Veiculo v1 = new Veiculo("ABC-1234");
        Veiculo v2 = new Veiculo("XYZ-9999");
        Veiculo v3 = new Veiculo("DEF-5678");

        est.cadastrarVeiculo(v1);
        est.cadastrarVeiculo(v2);

        SistemaCancela cancela = new SistemaCancela(est);

        cancela.liberarEntrada(v1); // deve permitir
        cancela.liberarEntrada(v1); // deve negar (já está dentro)
        cancela.liberarEntrada(v3); // deve negar (não cadastrado)
        cancela.liberarEntrada(v2); // deve permitir
        cancela.liberarEntrada(v2); // deve negar (estacionamento cheio)
    }
}
