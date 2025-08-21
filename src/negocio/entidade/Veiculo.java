package negocio.entidade;

public class Veiculo{
    private String placa;
    private Cliente dono;

    public Veiculo(String placa, Cliente dono){
        this.placa = placa;
        this.dono = dono;
    }

    // getters classe Veiculo


    public String getPlaca() {
        return placa;

    }

    public Cliente getDono(){
        return dono;

    }

    //setters
    public void setPlaca(String placa){
        this.placa = placa;

    }

    public void setDono(Cliente dono){
        this.dono = dono;
    }


}