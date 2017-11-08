/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl_BD;

import dao.UsuarioDAO;
import dominio.Usuario;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 631520174
 */
public class UsuarioDaoBD extends DaoBd<Usuario> implements UsuarioDAO{

    @Override
    public void salvar(Usuario dominio) {
        try {
            int id = 0;
            String sql = "INSERT INTO usuario (nome, sobrenome, email, senha) "
                        + "VALUES (?,?,?,?)";
            conectarObtendoId(sql);
            comando.setString(1, dominio.getNome());
            comando.setString(4, dominio.getSobrenome());
            comando.setString(5, dominio.getEmail());
            comando.setString(6, dominio.getSenha());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt("id");
                dominio.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar USUARIO no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Usuario dominio) {
        try {
            String sql = "DELETE FROM usuario WHERE id = ?";

            conectar(sql);
            comando.setInt(1, dominio.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar Usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Usuario dominio) {
        try {
            String sql = "UPDATE usuario SET nome=?, sobrenome=?, email=?, senha=? "
                    + "VALUES (?,?,?,?)";
            conectar(sql);
            comando.setString(1, dominio.getNome());
            comando.setString(4, dominio.getSobrenome());
            comando.setString(5, dominio.getEmail());
            comando.setString(6, dominio.getSenha());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar USUARIO no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {              
                int id = resultado.getInt("id");    
                String nome = resultado.getString("nome");
                String sobrenome = resultado.getString("sobrenome");
                String email = resultado.getString("email");
                String senha = resultado.getString("senha");
                Usuario moviment = new Usuario(id, nome, sobrenome, email, senha);
                listaUsuarios.add(moviment);
//                ordenarPorData(moviment, listaUsuarios);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as usuarios no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return listaUsuarios;
    }

    @Override
    public Usuario procurarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");                //pegando id

                String nome = resultado.getString("nome");
                String sobrenome = resultado.getString("sobrenome");
                String email = resultado.getString("email");
                String senha = resultado.getString("senha");
                Usuario moviment = new Usuario(id, nome, sobrenome, email, senha);
                return moviment;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o Receita pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
}
