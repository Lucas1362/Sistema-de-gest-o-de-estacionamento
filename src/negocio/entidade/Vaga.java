package negocio.entidade;

public class Vaga{
    private int numeroID;
    private boolean ocupada;



    //construtor da class

    public Vaga(int numeroID){
        this.numeroID = numeroID;
        this.ocupada = false;

    }

    //getters

    public int getNumeroID(){
        return numeroID;
    }

    public boolean isOcupada(){
        return ocupada;
    }

    //setter

    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }

    public void setNumeroID(int numeroID){this.numeroID = numeroID;}


}