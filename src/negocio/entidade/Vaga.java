package negocio.entidade;

public class Vaga{
    private String numeroID;
    private boolean ocupada;
    private boolean PCD;



    //construtor da class

    public Vaga(String numeroID){
        this.numeroID = numeroID;
        this.ocupada = false;

    }

    //getters

    public String getNumeroID(){
        return numeroID;
    }

    public boolean isOcupada(){
        return ocupada;
    }

    public boolean isPCD(){
        return PCD;
    }

    //setter

    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }

    public void setNumeroID(String numeroID){this.numeroID = numeroID;}

    public void setPCD(boolean PCD){this.PCD = PCD;}


}