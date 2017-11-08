package dao;

import java.util.List;
import dominio.Categoria;

/**
 *
 * @author Francke
 */
public interface CategoriaDAO extends Dao<Categoria>{
    public List<Categoria> listarPorNome(String nome);
    public boolean temAlgumaCatReceita();
    public boolean temAlgumaCatDespesa();
    public List<Categoria> listarPorTipo(Boolean tipo); 
    public Categoria procurarPorNome(String nome);
    
}

