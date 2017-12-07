package dao;

import java.util.List;
import dominio.Categoria;

/**
 *
 * @author rodrigoavellar
 */
public interface CategoriaDAO extends Dao<Categoria>{
    public List<Categoria> listarPorNome(int user_id, String nome);
    public boolean temAlgumaCatReceita(int user_id);
    public boolean temAlgumaCatDespesa(int user_id);
    public List<Categoria> listarPorTipo(int user_id, Boolean tipo); 
    public Categoria procurarPorNome(int user_id, String nome);
}
