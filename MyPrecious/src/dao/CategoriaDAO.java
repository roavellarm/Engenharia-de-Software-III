package dao;

import java.util.List;
import dominio.Categoria;

/**
 *
 * @author rodrigoavellar
 */
public interface CategoriaDAO extends Dao<Categoria>{
    public List<Categoria> listarPorNome(String nome);
    public boolean temAlgumaCatReceita();
    public boolean temAlgumaCatDespesa();
    public List<Categoria> listarPorTipo(int user_id, Boolean tipo); 
    public Categoria procurarPorNome(String nome);
}
