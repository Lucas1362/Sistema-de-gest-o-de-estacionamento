package src.model;

public class Vaga{
    private int numero;
    private boolean ocupada;



    //construtor da class

    public Vaga(int numero){
        this.numero = numero;
        this.ocupada = false;

    }

    //getters

    public int getNumero(){
        return numero;
    }

    public boolean isOcupada(){
        return ocupada;
    }

    //setter

    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }


}