package negocio;

import dao.UsuarioDAO;
import dao.impl_DB.UsuarioDaoBD;
import dominio.Usuario;

/**
 *
 * @author Rodrigo
 */
public class UsuarioNegocio {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioNegocio(){
        usuarioDAO = new UsuarioDaoBD();
    }
    
    public void salvar(Usuario user) throws NegocioException {
        this.validarCamposObrigatorios(user);
        usuarioDAO.salvar(user);
    }
    
    public void deletar(Usuario usuario) throws NegocioException {
        if (usuario == null || usuario.getID() == 0) {
            throw new NegocioException("Usuário não existe!");
        }
        usuarioDAO.deletar(usuario);
    }
    
    public Usuario validarCamposObrigatoriosLogin(String login, String senha) throws NegocioException {
        Usuario user = usuarioDAO.autenticar(login, senha);
        
        if(user == null) {
            throw new NegocioException("Autenticação invalida!");
        } else {
            return user;
        }
    }
   
    private void validarCamposObrigatorios(Usuario usuario) throws NegocioException {
        if(usuario.getNome().isEmpty()) {
            throw new NegocioException("Campo nome não informado");
        }
        if(usuario.getSobrenome().isEmpty()) {
            throw new NegocioException("Campo sobrenome não informado");
        }
        if(usuario.getEmail().isEmpty()) {
            throw new NegocioException("Campo email não informado");
        }
        if(usuario.getSenha().isEmpty()) {
            throw new NegocioException("Campo senha não informado");
        }
    }

}
