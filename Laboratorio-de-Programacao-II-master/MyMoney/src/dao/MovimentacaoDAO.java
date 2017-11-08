package dao;

import java.time.LocalDate;
import dominio.Movimentacao;
import java.util.List;

/**
 *
 * @author rodrigoavellar
 */
public interface MovimentacaoDAO extends Dao<Movimentacao>{   
    public Movimentacao procurarPorData(LocalDate data);
    public Movimentacao procurarPorValor(Double valor);
    public List<Movimentacao> getListaMovimentacoes(CategoriaDAO listaCat);
    public List<Movimentacao> listarPorTipo(Boolean tipo);
}
