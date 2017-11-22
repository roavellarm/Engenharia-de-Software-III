package dominio;

/**
 * 
 * @author Francke
 * 
 */
public class Categoria {
    private int id;
    private String titulo;
    private boolean tipo;

    public Categoria(String titulo, boolean tipo) {
        this.titulo = titulo;
        this.tipo = tipo;
    }
   
    public Categoria(int id,String titulo, boolean tipo) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
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
}
