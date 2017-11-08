package dominio;

/**
 *
 * @author rodrigoavellar
 */
public class Usuario {
    //private static int codGenerator = 1;
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha; // TODO Sistema temporário de senha...

//    /**
//     *
//     * @param nome
//     * @param email
//     * @param senha
//     */
//    public Usuario(String nome, String email, String senha) {
//        this.id = codGenerator();
//        this.nome = nome;
//        this.email = email;
//        this.senha = senha;
//    }

    /**
     *
     * @param id
     * @param nome
     * @param sobrenome
     * @param email
     * @param senha
     */
    public Usuario(int id, String nome, String sobrenome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
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
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     *
     * @param sobrenome
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }
    /**
     *
     * @param novaSenha
     */
    public void setSenha(String novaSenha) {
        this.senha = novaSenha;
    }
    
//    private int codGenerator(){
//        return(codGenerator++);
//    }  
}
