package dominio;

/**
 * 
 * @author Francke
 * 
 */
public class Categoria {
    private int id;
    private int user_id;
    private String titulo;
    private boolean tipo;

    public Categoria(String titulo, boolean tipo) {
        this.titulo = titulo;
        this.tipo = tipo;
    }
   
    public Categoria(int id,int user_id, String titulo, boolean tipo) {
        this.id = id;
        this.user_id = user_id;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    public boolean getTipoBoolean(){
        return this.tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
