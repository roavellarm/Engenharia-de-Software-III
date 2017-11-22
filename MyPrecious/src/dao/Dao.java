/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
<<<<<<< HEAD
 * @author rodrigo
=======
 * @author francke
>>>>>>> b22852bd592ade8c511fe45dd179bf17158c0c96
 */
public interface Dao<T> {
    public void salvar(T dominio);
    public void deletar(T dominio);
    public void atualizar(T dominio);
    public List<T> listar();
    public T procurarPorId(int id);    
}
