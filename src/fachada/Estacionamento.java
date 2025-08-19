package negocio.entidade;

import src.model.Vaga;
import src.model.Veiculo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.list;


public class Estacionamento {
    private List<Vaga> vagas;
    private List<Veiculo> veiculos;


//construtores

    public Estacionamento(){
        this.vagas = new ArrayList<>();
        this.veiculos = new ArrayList<>();

        // quantidade definida de vagas
        for (int i = 1; i <= 10; i++){
            vagas.add(new Vaga(i));
        }
    }

    public boolean reservarVaga(Veiculo veiculo, LocalDateTime horario) {
        for (Vaga vaga : vagas){
            if(!vaga.isOcupada()){
                vaga.setOcupada(true);
                veiculos.add(veiculo);
                return true;// em caso de reserva com sucesso
            }
        }
        return false; // vagas indisponiveis


    }
    //registro de entrada
    public void registrarEntrada(Veiculo veiculo){

    }

    // obter informações sobre as vagas disponiveis
    public List<Vaga> getVagasDisponiveis(){
        List<Vaga> vagasDisponiveis = new ArrayList<>();
        for(Vaga vaga : vagas){
            if (!vaga.isOcupada()){
                vagasDisponiveis.add(vaga);
            }
        }
        return vagasDisponiveis;
    }

    public void registrarEntrada(Veiculo veiculo) {

    }
    public List<Vaga> getVagasDisoniveis(LocalDateTime horario){



        return null
    }

}