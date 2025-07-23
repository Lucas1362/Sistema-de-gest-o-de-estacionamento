package model

public class SistemaCancela{
    private Estacionamento estacionamento;


    public liberarEntrada( Veiculo veiculo){
        if(verificarAutorizacao(veiculo)){
            System.out.println("Cancela aberta para: " + veiculo.getPlaca());
            estacionamento.registrarEntrada(veiculo);
        }else {
            System.out.println("Acesso negado para: " + veiculo.getPlaca());
        }

    }

    private boolean verificarAutorizacao(Veiculo veiculo){
        return true;

    }
}