
package dominio;

/**
 *
 * @author Rodrigo Avellar
 */
public class Usuario {
    private int ID;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    
    public Usuario() { }

    public Usuario(int ID, String nome, String sobrenome, String email, String senha) {
        this.nome = nome;
        this.ID = ID;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
