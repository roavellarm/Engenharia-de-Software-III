package dominio;

/**
 *
 * @author Francke
 */
public class Usuario {
    private static int codGenerator = 1;
    private int id;
    private String nome;
    private String email;
    private String senha; // TODO Sistema temporário de senha...

    /**
     *
     * @param nome
     * @param email
     * @param senha
     */
    public Usuario(String nome, String email, String senha) {
        this.id = codGenerator();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setName(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param novaSenha
     */
    public void setPassord(String novaSenha) {
        this.senha = novaSenha;
    }
    
    private int codGenerator(){
        return(codGenerator++);
    }  
}

