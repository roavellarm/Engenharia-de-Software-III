/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Usuario;

/**
 *
 * @author Rodrigo Avellar
 */
public interface UsuarioDAO extends Dao<Usuario>{
    public Usuario autenticar(String email, String senha);
}
