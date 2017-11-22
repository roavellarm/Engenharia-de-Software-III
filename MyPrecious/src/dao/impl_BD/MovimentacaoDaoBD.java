package dao.impl_BD;

import dao.CategoriaDAO;
import dao.MovimentacaoDAO;
import dominio.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import dominio.Movimentacao;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Francke
 */
public class MovimentacaoDaoBD extends DaoBd<Movimentacao> implements MovimentacaoDAO{   
    
    
    @Override
    public void salvar(Movimentacao mov) {
        try {
            int id = 0;
            String sql = "INSERT INTO movimentacao (valor, data, hora, descricao, tipo, idcategoria) "
                        + "VALUES (?,?,?,?,?,?)";
            conectarObtendoId(sql);
            comando.setDouble(1, mov.getValor());           //carregando valor
            Date dataSql = Date.valueOf(mov.getData());     //carregando data
            comando.setDate(2, dataSql);
            Time horaSql = Time.valueOf(mov.getHora());     //carregando hora
            comando.setTime(3,horaSql);
            comando.setString(4, mov.getDescricao());       //carregando descricao
            comando.setBoolean(5, mov.isTipo());            //carregando tipo
            comando.setInt(6, mov.getCategoria().getId());  //carregando id da categoria 
            comando.executeUpdate();                        //Adicionando registro ao banco
            ResultSet resultado = comando.getGeneratedKeys(); //Para recuperar o id no banco e adicionar ao objeto
            if (resultado.next()) {
                id = resultado.getInt("id");
                mov.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar MOVIMENTAÇÃO no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Movimentacao mov) {
        try {
            String sql = "DELETE FROM movimentacao WHERE id = ?";

            conectar(sql);
            comando.setInt(1, mov.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Movimentacao mov) {
        try {
            String sql = "UPDATE movimentacao SET valor=?, data=?, hora=?, descricao=?, tipo=?, idcategoria=? "
                    + "WHERE id=?";
            conectar(sql);
            comando.setDouble(1, mov.getValor());
            Date dataSql = Date.valueOf(mov.getData());
            comando.setDate(2, dataSql);
            Time horaSql = Time.valueOf(mov.getHora());     //carregando hora
            comando.setTime(3,horaSql);
            comando.setString(4, mov.getDescricao());       //carregando descricao
            comando.setBoolean(5, mov.isTipo());            //carregando tipo
            comando.setInt(6, mov.getCategoria().getId());  //carregando id da categoria
            comando.setInt(7, mov.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }        
    }

    @Override
    public List<Movimentacao> listar() {
        List<Movimentacao> listaMovimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao";
        
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {              
                int id = resultado.getInt("id");                
                double valor = resultado.getDouble("valor");    
                Date dataSql = resultado.getDate("data");       
                LocalDate data = dataSql.toLocalDate();
                Time horaSql = resultado.getTime("hora");       
                LocalTime hora = horaSql.toLocalTime();
                String descricao = resultado.getString("descricao");
                boolean tipo = resultado.getBoolean("tipo");
                int idCategoria = resultado.getInt("idcategoria");
                Categoria categoria = new CategoriaDaoBD().procurarPorId(idCategoria);
                Movimentacao moviment = new Movimentacao(id, valor, data, hora, categoria, descricao, tipo);
                listaMovimentacoes.add(moviment);
                ordenarPorData(moviment, listaMovimentacoes);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as movimentações no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        
        return listaMovimentacoes;
    }
   
    @Override
    public List<Movimentacao> listarPorTipo(Boolean tipoEscolhido) {
        List<Movimentacao> listaMovimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao where tipo = ?";
        try {
            conectar(sql);
            comando.setBoolean(1, tipoEscolhido);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {             
                int id = resultado.getInt("id");                
                double valor = resultado.getDouble("valor");    
                Date dataSql = resultado.getDate("data");       
                LocalDate data = dataSql.toLocalDate();
                Time horaSql = resultado.getTime("hora");       
                LocalTime hora = horaSql.toLocalTime();
                String descricao = resultado.getString("descricao");
                boolean tipo = resultado.getBoolean("tipo");
                int idCategoria = resultado.getInt("idcategoria");
                Categoria categoria = new CategoriaDaoBD().procurarPorId(idCategoria);
                Movimentacao moviment = new Movimentacao(id, valor, data, hora, categoria, descricao, tipo);
                listaMovimentacoes.add(moviment);
                ordenarPorData(moviment, listaMovimentacoes);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as movimentações no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        
        return listaMovimentacoes;
    }
    

    @Override
    public Movimentacao procurarPorId(int id) {
        String sql = "SELECT * FROM movimentacao WHERE id = ?";
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idReceita = resultado.getInt("id");                //pegando id
                double valor = resultado.getDouble("valor");    //pegando valor
                Date dataSql = resultado.getDate("data");       //pegando data
                LocalDate data = dataSql.toLocalDate();
                Time horaSql = resultado.getTime("hora");       //pegando hora
                LocalTime hora = horaSql.toLocalTime();
                String descricao = resultado.getString("descricao");
                boolean tipo = resultado.getBoolean("tipo");
                int idCategoria = resultado.getInt("idcategoria");
                Categoria categoria = new CategoriaDaoBD().procurarPorId(idCategoria);           
                Movimentacao moviment = new Movimentacao(idReceita, valor, data, hora, categoria, descricao, tipo);
                return moviment;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o Receita pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Movimentacao procurarPorData(LocalDate data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movimentacao procurarPorValor(Double valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movimentacao> getListaMovimentacoes(CategoriaDAO listaCat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    // UTIL    
    private void ordenarPorData(Movimentacao Mov, List<Movimentacao> listaMovimentacoes) {
        Comparator<Movimentacao> cmp = (Movimentacao o1, Movimentacao o2) -> {
            if (o1.getData().isBefore(o2.getData())){
                return -1;
            }
            if (o1.getData().isEqual(o2.getData())){            
                return 0;
            }
            else{
                return 1;
            }
        };
        Collections.sort(listaMovimentacoes, cmp);
        ordenarPorHora(Mov, listaMovimentacoes);
    }

    private void ordenarPorHora(Movimentacao Mov, List<Movimentacao> listaMovimentacoes) {
        Comparator<Movimentacao> cmp = (Movimentacao o1, Movimentacao o2) -> {
            if (o1.getData().isEqual(o2.getData())){
                if (o1.getHora().isBefore(o2.getHora())){
                    return -1;
                }
                if (o1.getHora().isAfter(o2.getHora())){            
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        };
        Collections.sort(listaMovimentacoes, cmp);
    }
    
}

