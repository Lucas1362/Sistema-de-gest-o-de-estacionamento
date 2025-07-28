package model;

public class Veiculo{
    private String placa;
    private String modelo;
    private Cliente dono;




    public Veiculo(String placa, String modelo, Cliente dono){
        this.placa = placa;
        this.modelo = modelo;
        this.dono = dono;
    }

    // getters classe Veiculo


    public String getPlaca() {
        return placa;

    }

    public String getModelo() {
        return modelo;
    }

    public String getDono(){
        return dono;

    }

    //setters
    public void setPlaca(String placa){
        this.placa = placa;

    }

    public void setModelo(String modelo){
        this.modelo = modelo;

    }
    public void setDono(Cliente dono){
        this.dono = dono;
    }


}


