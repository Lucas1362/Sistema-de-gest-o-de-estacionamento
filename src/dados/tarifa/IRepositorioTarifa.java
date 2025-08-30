package dados.tarifa;

public interface IRepositorioTarifa {

    void carregarDados();

    double getValorAtual();

    void setNovoValor(double novoValor);

}
