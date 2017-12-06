package dominio;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Francke
 */
public class Movimentacao {
    private int id;
    private double valor;
    private LocalDate data;
    private LocalTime hora;
    private Categoria categoria;
    private String descricao;
    private boolean tipo;
    
     /**
     * @param tipo
     */
    public Movimentacao(boolean tipo) {
        this.tipo = tipo;
    }
    
    public Movimentacao() {
    }

    public Movimentacao(double valor, Categoria categoria, String descricao, boolean tipo) {
        this.valor = valor;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;        
    }
    
    public Movimentacao(int id, double valor, LocalDate data, LocalTime hora, Categoria categoria, String descricao, boolean tipo) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.hora = hora;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;  
    }
    
    public Movimentacao(double valor, LocalDate data, LocalTime hora, Categoria categoria, String descricao, boolean tipo) {
        this.valor = valor;
        this.data = data;
        this.hora = hora;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;  
    }
    
    public int getId() {
        return id;
    }
    
    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
    
    public LocalTime getHora() {
        return hora;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }
    
    public String getDescricao() {
        return descricao;
    }   
     
    public String getTipoString(){
        if(this.tipo){
            return "Receita";
        } else {
            return "Despesa";
        }
    }
    

    public boolean isTipo(){
        return this.tipo;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public void setDataDeHoje(){
        this.data = LocalDate.now();
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public void setHoraAgora(){
        this.hora = LocalTime.now();
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(boolean tipo){
        this.tipo = tipo;
    }

}
