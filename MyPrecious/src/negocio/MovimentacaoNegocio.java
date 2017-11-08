package negocio;

import dao.CategoriaDAO;
import dao.MovimentacaoDAO;
import dao.impl_BD.CategoriaDaoBD;
import dao.impl_BD.MovimentacaoDaoBD;
import dominio.Categoria;
import dominio.Movimentacao;
import java.util.List;

/**
 *
 * @author Francke
 */
public class MovimentacaoNegocio {
    private final MovimentacaoDAO movDAO;
    private final CategoriaDAO catDao;
    
    public MovimentacaoNegocio(){
        movDAO = new MovimentacaoDaoBD();
        catDao = new CategoriaDaoBD();  
    }
    
    public void salvar(Movimentacao mov) throws NegocioException {
        this.validarCamposObrigatorios(mov);
        movDAO.salvar(mov);
    }
    
    public void deletar(Movimentacao movimentacao) throws NegocioException {
        if (movimentacao == null || movimentacao.getId() == 0) {
            throw new NegocioException("Movimentação não existe!");
        }
        movDAO.deletar(movimentacao);
    }
    
    public void atualizar(Movimentacao mov) throws NegocioException {
        this.validarCamposObrigatorios(mov);
        movDAO.atualizar(mov);
    }
    
    public List<Movimentacao> listar() throws NegocioException {
        return movDAO.listar();
    }
    
    public List<Movimentacao> listarPorTipo(Boolean tipo) throws NegocioException {
        return movDAO.listarPorTipo(tipo);
    }    
    
    private void validarCamposObrigatorios(Movimentacao mov) throws NegocioException {
        if(mov.getValor() == 0) {
            throw new NegocioException("Campo Valor não informado ou em formato incorreto.\n\n"
                                        + "Observação:\n"
                                        + "Digitar apenas numeros.\n"
                                        + "Utilizar ponto e não vírgula.");
        }
        if(mov.getData() == null){
            throw new NegocioException("Campo Data não informado ou em formato incorreto");
        }
        if(mov.getHora() == null){
            throw new NegocioException("Campo Hora não informado ou em formato incorreto");
        }
        if(mov.getCategoria() == null){
            throw new NegocioException("Categoria não informada");
        }
    }

}
