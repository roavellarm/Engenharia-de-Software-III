package negocio;

import dominio.Categoria;
import dao.CategoriaDAO;
import dao.impl_BD.CategoriaDaoBD;
import java.util.List;

public class CategoriaNegocio {
    
    private final CategoriaDAO categoriaDao;

    public CategoriaNegocio() {
        categoriaDao = new CategoriaDaoBD();
    }

    public void salvar(Categoria cat) throws NegocioException {
        this.validarCamposObrigatorios(cat);
        this.validarTituloExistente(cat);
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

    public List<Categoria> listarPorTipo(Boolean tipo) throws NegocioException {
        return(categoriaDao.listarPorTipo(tipo));
    }

    public boolean temAlgumaCatDespesa() {
        return categoriaDao.temAlgumaCatDespesa();
    }
    
    public boolean temAlgumaCatReceita() {
        return categoriaDao.temAlgumaCatReceita();
    }

    private void validarCamposObrigatorios(Categoria cat) throws NegocioException {
        if (cat.getTitulo() == null || cat.getTitulo().isEmpty()) {
            throw new NegocioException("Campo Titulo nao informado");
        }        
    }

    private void validarTituloExistente(Categoria cat) throws NegocioException {
        if (categoriaExiste(cat.getTitulo())) {
            throw new NegocioException("Categoria ja existente");
        }        
    }
    
    public boolean categoriaExiste(String titulo) throws NegocioException {
        if(titulo == null || titulo.isEmpty()){
            throw new NegocioException("Preencha o título da categoria no campo!");
        } else {
            Categoria categoria = categoriaDao.procurarPorNome(titulo);
            return (categoria != null);
        }
    }
    
    public Categoria procurarPorNome (String nome)throws NegocioException {
        if (nome.isEmpty() || nome == null){
            throw new NegocioException("Preencha o campo com o nome da Categoria");
        }
        
        Categoria cat = categoriaDao.procurarPorNome(nome);
        if (cat.getId() == 0) {
            throw new NegocioException("Categoria não existe");
        }
        return cat;
    }
}
