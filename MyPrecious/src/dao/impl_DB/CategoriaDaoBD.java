package dao.impl_DB;

import dao.CategoriaDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dominio.Categoria;

/**
 *
 * @author rodrigoavellar
 */
public class CategoriaDaoBD extends DaoBd<Categoria> implements CategoriaDAO{
    @Override
    public void salvar(Categoria cat) {
        int id = 0;
        try {
            String sql = "INSERT INTO categoria (titulo, tipo) "
                        + "VALUES (?,?)";
            conectarObtendoId(sql);
            comando.setString(1, cat.getTitulo());
            comando.setBoolean(2, cat.getTipoBoolean());
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt("id");
                cat.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar CATEGORIA no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }        
    }

    @Override
    public void deletar(Categoria cat) {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try {
            conectar(sql);
            comando.setInt(1, cat.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar Categoria no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }        
    }

    @Override
    public void atualizar(Categoria cat) {
        try {
            String sql = "UPDATE categoria SET titulo=?, tipo=? WHERE id=?";
            conectar(sql);
            comando.setString(1, cat.getTitulo());
            comando.setBoolean(2, cat.getTipoBoolean());
            comando.setInt(3, cat.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar paciente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }        
    }

    @Override
    public List<Categoria> listar() {
        List<Categoria> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                boolean tipo = resultado.getBoolean("tipo");
                Categoria cat = new Categoria(id, titulo, tipo);
                listaCategorias.add(cat);      
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return listaCategorias;
    }

    @Override
    public Categoria procurarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try {
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                String titulo = resultado.getString("titulo");
                boolean tipo = resultado.getBoolean("tipo");
                Categoria cat = new Categoria(id, titulo, tipo);
                return cat;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o paciente pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
    @Override
    public Categoria procurarPorNome(String nome) {
        String sql = "SELECT * FROM categoria WHERE titulo = ?";

        try {
            conectar(sql);
            comando.setString(1, nome);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                Boolean tipo = resultado.getBoolean("tipo");
                Categoria cat = new Categoria(id, titulo, tipo);
                return cat;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o categoria pelo nome do Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);        
    }
    
    @Override
    public boolean temAlgumaCatReceita() {
        String sql = "SELECT Count(*) FROM categoria WHERE tipo = true";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                int count = resultado.getInt(1);
                return count > 0;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o categoria pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public boolean temAlgumaCatDespesa() {
                String sql = "SELECT Count(*) FROM categoria WHERE tipo = false";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                int count = resultado.getInt(1);
                return count > 0;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o categoria pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Categoria> listarPorTipo(Boolean op) {
        List<Categoria> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE tipo = ?";
        try {
            conectar(sql);
            comando.setBoolean(1,op);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                Boolean tipo = resultado.getBoolean("tipo");
                int id = resultado.getInt("id");
                Categoria cat = new Categoria(id, titulo, tipo);
                listaCategorias.add(cat);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as categorias pelo tipo no Banco de Dados!");
            throw new RuntimeException(ex);      
        } finally {
            fecharConexao();
        }
        return (listaCategorias);
        
    }

    @Override
    public List<Categoria> listarPorNome(String nome) { //TESTAR!!!!        
        List<Categoria> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE titulo like ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                Boolean tipo = resultado.getBoolean("tipo");
                int id = resultado.getInt("id");
                Categoria cat = new Categoria(id, titulo, tipo);
                listaCategorias.add(cat);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as categorias pelo titulo no Banco de Dados!");
            throw new RuntimeException(ex);      
        } finally {
            fecharConexao();
        }
        return (listaCategorias);
    }
}
