/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 631520174
 */
public class MovimentacaoTest {
    
    public MovimentacaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getId method, of class Movimentacao.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Movimentacao instance = new Movimentacao();
        instance.setId(2);
        int expResult = 2;
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getValor method, of class Movimentacao.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        Movimentacao instance = new Movimentacao();
        instance.setValor(1500.00);
        double expResult = 1500.00;
        double result = instance.getValor();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getData method, of class Movimentacao.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        Movimentacao instance = new Movimentacao();
        instance.setData(LocalDate.MAX);
        LocalDate expResult = LocalDate.MAX;
        LocalDate result = instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora method, of class Movimentacao.
     */
    @Test
    public void testGetHora() {
        System.out.println("getHora");
        Movimentacao instance = new Movimentacao();
        instance.setHora(LocalTime.NOON);
        LocalTime expResult = LocalTime.NOON;
        LocalTime result = instance.getHora();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategoria method, of class Movimentacao.
     */
    @Test
    public void testGetCategoria() {
        System.out.println("getCategoria");
        Movimentacao instance = new Movimentacao();
        Categoria cat = new Categoria("Teste de categoria", true);
        instance.setCategoria(cat);
        Categoria expResult = cat;
        Categoria result = instance.getCategoria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescricao method, of class Movimentacao.
     */
    @Test
    public void testGetDescricao() {
        System.out.println("getDescricao");
        Movimentacao instance = new Movimentacao();
        instance.setDescricao("Teste de descrição");
        String expResult = "Teste de descrição";
        String result = instance.getDescricao();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTipoString method, of class Movimentacao.
     */
    @Test
    public void testGetTipoString() {
        System.out.println("getTipoString");
        Movimentacao instance = new Movimentacao();
//        instance.setTipo(true);
        instance.setTipo(false);
//        String expResult = "Receita";
        String expResult = "Despesa";
        String result = instance.getTipoString();
        assertEquals(expResult, result);
    }

    /**
     * Test of isTipo method, of class Movimentacao.
     */
    @Test
    public void testIsTipo() {
        System.out.println("isTipo");
        Movimentacao instance = new Movimentacao();
        instance.setTipo(true);
        boolean expResult = true;
        boolean result = instance.isTipo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Movimentacao.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        Movimentacao instance = new Movimentacao();
        instance.setId(id);
    }

    /**
     * Test of setValor method, of class Movimentacao.
     */
    @Test
    public void testSetValor() {
        System.out.println("setValor");
        double valor = 1200.55;
        Movimentacao instance = new Movimentacao();
        instance.setValor(valor);
    }

    /**
     * Test of setData method, of class Movimentacao.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        LocalDate data = LocalDate.MAX;
        Movimentacao instance = new Movimentacao();
        instance.setData(data);
    }

    /**
     * Test of setDataDeHoje method, of class Movimentacao.
     */
    @Test
    public void testSetDataDeHoje() {
        System.out.println("setDataDeHoje");
        Movimentacao instance = new Movimentacao();
        instance.setDataDeHoje();
    }

    /**
     * Test of setHora method, of class Movimentacao.
     */
    @Test
    public void testSetHora() {
        System.out.println("setHora");
        LocalTime hora = LocalTime.NOON;
        Movimentacao instance = new Movimentacao();
        instance.setHora(hora);
    }

    /**
     * Test of setCategoria method, of class Movimentacao.
     */
    @Test
    public void testSetCategoria() {
        System.out.println("setCategoria");
        Categoria categoria = new Categoria("Teste Categoria", true);
        Movimentacao instance = new Movimentacao();
        instance.setCategoria(categoria);
    }

    /**
     * Test of setDescricao method, of class Movimentacao.
     */
    @Test
    public void testSetDescricao() {
        System.out.println("setDescricao");
        String descricao = "Teste Set Descrição";
        Movimentacao instance = new Movimentacao();
        instance.setDescricao(descricao);
    }

    /**
     * Test of setTipo method, of class Movimentacao.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        boolean tipo = false;
        Movimentacao instance = new Movimentacao();
        instance.setTipo(tipo);
    }
    
}
