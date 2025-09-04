package negocio;

import dados.veiculo.IRepositorioVeiculos;
import negocio.entidade.Veiculo;
import negocio.excecao.veiculo.VeiculoExistenteException;
import negocio.excecao.veiculo.VeiculoNaoExisteException;

public class NegocioVeiculo {

    private IRepositorioVeiculos repositorio;

    public NegocioVeiculo(IRepositorioVeiculos repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionar(Veiculo veiculo) throws VeiculoExistenteException {
        // Regra de negócio: não adicionar veículo com placa duplicada
        if (repositorio.existePlaca(veiculo.getPlaca())) {
            throw new VeiculoExistenteException();
        } else {
            repositorio.adicionar(veiculo);
        }
    }

    public void remover(String placa) throws VeiculoNaoExisteException {
        Veiculo veiculo = this.consultar(placa);
        repositorio.remover(veiculo);
    }

    public Veiculo consultar(String placa) throws VeiculoNaoExisteException {
        Veiculo veiculo = repositorio.consultarPlaca(placa);
        if (veiculo == null) {
            throw new VeiculoNaoExisteException();
        }
        return veiculo;
    }
}
