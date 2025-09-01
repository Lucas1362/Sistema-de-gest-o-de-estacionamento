package negocio;

import dados.vaga.IRepositorioVagas;
import negocio.entidade.Vaga;
import negocio.excecao.vaga.VagaExistenteException;
import negocio.excecao.vaga.VagaNaoExisteException;
import negocio.excecao.vaga.VagaOcupadaException;

import java.util.ArrayList;

public class NegocioVaga {

    private IRepositorioVagas repositorio;

    public NegocioVaga(IRepositorioVagas repositorio) {
        this.repositorio = repositorio;
    }

    public IRepositorioVagas getRepositorio() {return repositorio;}

    public void adicionar(Vaga vaga) throws VagaExistenteException {
        // Regra de negócio: não adicionar vaga com ID duplicado.
        if (repositorio.existeID(vaga.getNumeroID())) {
            throw new VagaExistenteException();
        } else {
            repositorio.adicionar(vaga);
        }
    }


    public ArrayList<Vaga> consultarVagasLivres() {
        // Esta é uma regra de negócio importante: filtrar apenas as vagas livres.
        ArrayList<Vaga> vagasLivres = new ArrayList<>();
        ArrayList<Vaga> todasAsVagas = repositorio.listar();

        for (Vaga vaga : todasAsVagas) {
            if (!vaga.isOcupada()) {
                vagasLivres.add(vaga);
            }
        }
        return vagasLivres;
    }

    public void ocuparVaga(String id) throws VagaNaoExisteException, VagaOcupadaException {
        Vaga vaga = repositorio.consultar(id);
        if (vaga == null) {
            throw new VagaNaoExisteException();
        }
        if (vaga.isOcupada()) {
            throw new VagaOcupadaException();
        }
        vaga.setOcupada(true);
    }
}
