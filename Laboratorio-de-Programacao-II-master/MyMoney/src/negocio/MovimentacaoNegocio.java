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
 * @author rodrigo
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
//
//    public List<Categoria> listar() {
//        return (categoriaDao.listar());
//    }
//
//    public void deletar(Categoria categoria) throws NegocioException {
//        if (categoria == null || categoria.getId() == 0) {
//            throw new NegocioException("Categoria nao existe!");
//        }
//        categoriaDao.deletar(categoria);
//    }
//
//    public void atualizar(Categoria categoria) throws NegocioException {
//        if (categoria == null || categoria.getId() == 0) {
//            throw new NegocioException("Categoria nao existe!");
//        }
//        this.validarCamposObrigatorios(categoria);
//        categoriaDao.atualizar(categoria);
//    }
//
//    public List<Categoria> listarPorTipo(Boolean tipo) throws NegocioException {
//        return(categoriaDao.listarPorTipo(tipo));
//    }
//
//    public boolean temAlgumaCatDespesa() {
//        return categoriaDao.temAlgumaCatDespesa();
//    }
//    
//    public boolean temAlgumaCatReceita() {
//        return categoriaDao.temAlgumaCatReceita();
//    }
//

//
//    private void validarTituloExistente(Categoria cat) throws NegocioException {
//        if (categoriaExiste(cat.getTitulo())) {
//            throw new NegocioException("Categoria ja existente");
//        }        
//    }
//    
//    public boolean categoriaExiste(String titulo) throws NegocioException {
//        if(titulo == null || titulo.isEmpty()){
//            throw new NegocioException("Preencha o título da categoria no campo!");
//        } else {
//            Categoria categoria = categoriaDao.procurarPorNome(titulo);
//            return (categoria != null);
//        }
//    }
//    
//    public Categoria procurarPorNome (String nome)throws NegocioException {
//        if (nome.isEmpty() || nome == null){
//            throw new NegocioException("Preencha o campo com o nome da Categoria");
//        }
//        
//        Categoria cat = categoriaDao.procurarPorNome(nome);
//        if (cat.getId() == 0) {
//            throw new NegocioException("Categoria não existe");
//        }
//        return cat;
//    }

}
