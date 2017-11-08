package dominio;

/**
 * Classe Abstrata de modelo para instanciar objetos do tipo Categoria
 * @author rodrigoavellar
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
    /**
     * Construtur da Classe abstrata "Categoria" passando como parâmentro os atributos "Título" em String e "Tipo" em boolean.
     * @param id
     * @param titulo parâmetro String
     * @param tipo parâmetro boolean
     */
    public Categoria(int id,String titulo, boolean tipo) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    /**
     * Método getId para retornar o Id (int) do obteto gerado pela classe Categoria
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Método getTitulo para retornar o titulo do obteto gerado pela classe Categoria
     * @return String titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método setTitulo para alterar o titulo do obteto gerado pela classe Categoria
     * @param titulo parâmetro String
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método getTipo para alterar o atributo "tipo" do obteto gerado pela classe Categoria
     * @param tipo parâmetro boolean
     */
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
