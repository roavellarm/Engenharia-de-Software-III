package negocio;

import dominio.Categoria;
import dao.CategoriaDAO;
import dao.impl_DB.CategoriaDaoBD;
import java.util.List;

public class CategoriaNegocio {
    
    private final CategoriaDAO categoriaDao;

    public CategoriaNegocio() {
        categoriaDao = new CategoriaDaoBD();
    }

    public void salvar(int user_id, Categoria cat) throws NegocioException {
        this.validarCamposObrigatorios(cat);
        this.validarTituloExistente(user_id, cat);
        categoriaDao.salvar(cat);
    }

    public List<Categoria> listar() {
        return (categoriaDao.listar());
    }

    public void deletar(Categoria categoria) throws NegocioException {
        if (categoria == null || categoria.getId() == 0) {
            throw new NegocioException("Categoria nao existe!");
        }
        categoriaDao.deletar(categoria);
    }

    public void atualizar(Categoria categoria) throws NegocioException {
        if (categoria == null || categoria.getId() == 0) {
            throw new NegocioException("Categoria nao existe!");
        }
        this.validarCamposObrigatorios(categoria);
        categoriaDao.atualizar(categoria);
    }

    public List<Categoria> listarPorTipo(int user_id, Boolean tipo) throws NegocioException {
        return(categoriaDao.listarPorTipo(user_id, tipo));
    }

    public boolean temAlgumaCatDespesa(int user_id) {
        return categoriaDao.temAlgumaCatDespesa(user_id);
    }
    
    public boolean temAlgumaCatReceita(int user_id) {
        return categoriaDao.temAlgumaCatReceita(user_id);
    }

    private void validarCamposObrigatorios(Categoria cat) throws NegocioException {
        if (cat.getTitulo() == null || cat.getTitulo().isEmpty()) {
            throw new NegocioException("Campo Titulo nao informado");
        }        
    }

    private void validarTituloExistente(int user_id, Categoria cat) throws NegocioException {
        if (categoriaExiste(user_id, cat.getTitulo())) {
            throw new NegocioException("Categoria ja existente");
        }        
    }
    
    public boolean categoriaExiste(int user_id, String titulo) throws NegocioException {
        if(titulo == null || titulo.isEmpty()){
            throw new NegocioException("Preencha o título da categoria no campo!");
        } else {
            Categoria categoria = categoriaDao.procurarPorNome(user_id, titulo);
            return (categoria != null);
        }
    }
    
    public Categoria procurarPorNome (int user_id, String nome)throws NegocioException {
        if (nome.isEmpty() || nome == null){
            throw new NegocioException("Preencha o campo com o nome da Categoria");
        }
        
        Categoria cat = categoriaDao.procurarPorNome(user_id, nome);
        if (cat.getId() == 0) {
            throw new NegocioException("Categoria não existe");
        }
        return cat;
    }
}

