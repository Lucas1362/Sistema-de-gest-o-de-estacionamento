package negocio.entidade;

import src.model.Vaga;
import src.model.Veiculo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.list;


public class Estacionamento {
    private List<Vaga> vagas;
    private List<Veiculo> veiculos;
    private List<Ticket> ticketsAtivos;
    private List<Ticket> ticketsEncerrados;
    private double valorhora = 5.0;


//construtores

    public Estacionamento(int totalVagas){
        this.vagas = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.ticketsAtivos = new ArrayList<>();
        this.ticketsEncerrados = new ArrayList<>();

        // quantidade definida de vagas
        for (int i = 1; i <= 10; i++){
            vagas.add(new Vaga(i));
        }
    }

    // Registro de entrada
    public Ticket registrarEntrada(String placa) throws  EstacionamentoException {
        Vaga vagaDisponivel = vagas.stream()
                .filter(v -> v.estaLivre())
                .findFirst()
                .orElseThrow(() -> new EstacionamentoException("Desculpe, não há vagas disponiveis"))

        Veiculo veiculo = new Veiculo(placa);
        vagaDisponivel.ocupar(veiculo);

        ticket novoTicket = new Ticket(veiculo, vagaDisponivel, LocalDateTime.now());
        ticketsAtivos.add(novoTicket);

        return novoTicket;

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