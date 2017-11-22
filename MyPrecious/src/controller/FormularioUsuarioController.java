package controller;

import dao.impl_BD.MovimentacaoDaoBD;
import dominio.Categoria;
import dominio.Movimentacao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.CategoriaNegocio;
import negocio.MovimentacaoNegocio;
import negocio.NegocioException;
import view.PrintUtil;

/**
 *
 * @author rodrigo
 */
public class FormularioUsuarioController implements Initializable  {

    @FXML
    private AnchorPane telaMain;
    @FXML
    private TextField nome;
    @FXML
    private TextField sobrenome;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    
    private MovimentacaoNegocio movimentacaoNegocio;
    private CategoriaNegocio categoriaNegocio;
    private List<Movimentacao> listaMovimentacoes;
    private List<Movimentacao> listaPraSaldo;
    private ObservableList<Movimentacao> observableListaMovimentacoes;
    private Boolean tipo;
    private Boolean listarQualTipo;
    private Movimentacao movimentacaoSelecionada;
    private boolean isPorMes;
    
    // Controller de Menu Movimentacao
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriaNegocio = new CategoriaNegocio();
        movimentacaoNegocio = new MovimentacaoNegocio();
        group = new ToggleGroup();

        //datePickerMes.setValue(LocalDate.now());
        try {
            listaPraSaldo = movimentacaoNegocio.listar();
        } catch (NegocioException ex) {
            Logger.getLogger(FormularioUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tableViewMovimentacao != null){
            carregarListaMovimentacoes();
            calcularSaldo();
            calcularReceita();
            calcularDespesa();
        }
    }

    // Funções
    private void atualizarCabecalho(){
        try {
            listaPraSaldo = movimentacaoNegocio.listar();
        } catch (NegocioException ex) {
            Logger.getLogger(FormularioUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        calcularSaldo();
        calcularReceita();
        calcularDespesa();
    }
    
    public void calcularSaldo() {
        double saldoAtual = 0;
        for (Movimentacao x : listaPraSaldo){
            if (x.isTipo()){
                saldoAtual += x.getValor();
            } else {
                saldoAtual -= x.getValor();
            }
        }
        labelSaldo.setText(Double.toString(saldoAtual));
    }
    
    public void calcularReceita() {
        double saldoReceita = 0;
        for (Movimentacao x : listaPraSaldo){
            if (x.isTipo()){
                saldoReceita += x.getValor();
            }
        }
        labelReceita.setText(Double.toString(saldoReceita));
    }
    
    public void calcularDespesa() {
        double saldoReceita = 0;
        for (Movimentacao x : listaPraSaldo){
            if (!(x.isTipo())){
                saldoReceita += x.getValor();
            }
        }
        labelDespesa.setText(Double.toString(saldoReceita));
    }
    
    @FXML
    private void adicionar(ActionEvent event) throws IOException {
        movimentacaoSelecionada = null;
        Stage stage = (Stage) telaMain.getScene().getWindow();
        stage.setTitle("FORMULARIO MOVIMENTAÇÃO");
        FXMLLoader loader = new FXMLLoader(Movimentacao.class.getResource("/view/FormularioMovimentacao.fxml"));
        Parent root = (Parent) loader.load();            
        FormularioUsuarioController controller = (FormularioUsuarioController) loader.getController();
        controller.initializarFormulario();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void deletar(ActionEvent event) {
        Movimentacao movSelecionada = tableViewMovimentacao.getSelectionModel().getSelectedItem();
        if (movSelecionada != null) {
            try {
                movimentacaoNegocio.deletar(movSelecionada);
                PrintUtil.mostrarMenssagemSucesso("Movimentação removida com sucesso!");
                observableListaMovimentacoes = null;
                carregarListaMovimentacoes();
                atualizarCabecalho();
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }
        } else {
            PrintUtil.mostrarMensagemErro("Selecione uma Movimentação para remover!");
        }       
    }

    @FXML
    private void Editar(ActionEvent event) throws IOException {
        movimentacaoSelecionada = tableViewMovimentacao.getSelectionModel().getSelectedItem();
        if(movimentacaoSelecionada != null){
            Stage stage = (Stage) telaMain.getScene().getWindow();
            stage.setTitle("FORMULARIO MOVIMENTAÇÃO");
            FXMLLoader loader = new FXMLLoader(Movimentacao.class.getResource("/view/FormularioMovimentacao.fxml"));
            Parent root = (Parent) loader.load();            
            FormularioUsuarioController controller = (FormularioUsuarioController) loader.getController();
            controller.initializarFormulario();
            controller.carregarCamposParaEditar(movimentacaoSelecionada);
            stage.setScene(new Scene(root));   
        } else {
            PrintUtil.mostrarMensagemErro("Precisa selecionar uma movimentação para esta opção");
        }
    }
    
    @FXML
    private void btListarTodosAnos (ActionEvent event) {
        isPorMes = false;
    }
    
    @FXML
    private void irBtDespesa(ActionEvent event) {
        if(btDespesa.isSelected()){
            listarQualTipo = false;
        } else {
            listarQualTipo = null;
        }
        carregarListaMovimentacoes();
    }

    @FXML
    private void voltarMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) telaMain.getScene().getWindow();
        stage.setTitle("MY MONEY - MENU INICIAL");
        Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(painelTelaPrincipal));
    }

    private void carregarListaMovimentacoes() {
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("idcategoria"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        tableColumnCategoria.setCellValueFactory( 
            new Callback<TableColumn.CellDataFeatures<Movimentacao,String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Movimentacao, String> linha) {
                    final Movimentacao mov = linha.getValue();
                    String stringCategoria = mov.getCategoria().getTitulo();
                    final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(stringCategoria);
                    return simpleObject;
                }
            }
        );
        tableColumnTipo.setCellValueFactory( 
            new Callback<TableColumn.CellDataFeatures<Movimentacao,String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Movimentacao, String> linha) {
                    final Movimentacao mov = linha.getValue();
                    String tipo;
                    if(mov.isTipo()){
                        tipo = "Receita";
                    } else {
                        tipo = "Despesa";
                    }
                    final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(tipo);
                    return simpleObject;
                }
            }
        );
        conferirTipoEPeriodoAntesDeCarregarTabela();
        mostrarMovimentacoes();
    }
    
    private void conferirTipoEPeriodoAntesDeCarregarTabela() {
        if(listarQualTipo == null){
            try {
                if(!isPorMes){
                    listaMovimentacoes = movimentacaoNegocio.listar();
                } else {
                    int ano = datePickerMes.getValue().getYear();
                    int mes = datePickerMes.getValue().getMonthValue();
                }
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }
        } else {
            try {
                listaMovimentacoes = movimentacaoNegocio.listarPorTipo(listarQualTipo);
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }
        } 
    }

    private void mostrarMovimentacoes() {
        observableListaMovimentacoes = FXCollections.observableArrayList(listaMovimentacoes);
        tableViewMovimentacao.setItems(observableListaMovimentacoes);
    }
    
    private void initializarFormulario() {
        this.tipo = true;
        listaCategorias = new ArrayList<>();
        movDAO = new MovimentacaoDaoBD();
        catNegocio = new CategoriaNegocio();
        movNegocio = new MovimentacaoNegocio();
        datePickerData.setValue(LocalDate.now());
        textFieldHora.setText(LocalTime.now().toString().substring(0, 5));
        carregaComboBox();
    }


    @FXML
    private AnchorPane painelFormularioMovimentacao;
    @FXML
    private TextField textFieldValor;
    @FXML
    private DatePicker datePickerData;
    @FXML
    private ComboBox<String> ComboCategoria;
    @FXML
    private RadioButton radReceita;
    @FXML
    private ToggleGroup tipogroup;
    @FXML
    private RadioButton radDespesa;
    @FXML
    private TextField textFieldHora;
    @FXML
    private TextArea textAreaDescricao;
        
    private MovimentacaoDaoBD movDAO;
    private MovimentacaoNegocio movNegocio;
    private CategoriaNegocio catNegocio;
    private List<Categoria> listaCategorias;
    private ObservableList<String> observableListtitulosCategorias;
    private Categoria categoriaSelecionada;

    private void carregarCamposParaEditar(Movimentacao movSelecionada){
        this.movimentacaoSelecionada = movSelecionada;
        textFieldValor.setText(Double.toString(movimentacaoSelecionada.getValor())); //Carrega campo valor
        datePickerData.setValue(movimentacaoSelecionada.getData()); // Carrega campo data
        textFieldHora.setText(movimentacaoSelecionada.getHora().toString().substring(0, 5)); // Carrega campo hora
        if (movimentacaoSelecionada.isTipo()){   // Carrega btRadio receita ou despesa
            tipogroup.selectToggle(radReceita);
        } else {
            tipogroup.selectToggle(radDespesa);
        }
        ComboCategoria.setValue(movimentacaoSelecionada.getCategoria().getTitulo()); // Carrega combo categoria
        textAreaDescricao.setText(movimentacaoSelecionada.getDescricao()); // Carrega campo descrição
    }

    @FXML
    private void accionSalvar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioMovimentacao.getScene().getWindow();
        if (movimentacaoSelecionada == null) //Se for cadastrar
        {
            try {
                double valor;
                
                if(textFieldValor.getText().isEmpty() || !(validateValor(textFieldValor.getText()))){
                    valor = 0;
                } else {
                    valor = Double.parseDouble(textFieldValor.getText());
                }
                
                LocalDate data;
                if(datePickerData.getValue() != null){
                    data = datePickerData.getValue();
                } else {
                    data = null;
                }
                
                LocalTime hora;
                if(textFieldHora.getText().isEmpty()){
                    hora = null;
                } else if(validateHora(textFieldHora.getText())){
                    hora = LocalTime.parse(textFieldHora.getText(), DateTimeFormatter.ISO_TIME);
                } else {
                        hora = null;                    
                }
                
                if(ComboCategoria.getValue() == null){
                    categoriaSelecionada = null;
                } else{
                    String catEscolhida = ComboCategoria.getValue().toString();
                    categoriaSelecionada = catNegocio.procurarPorNome(catEscolhida);
                }
                String descricao = textAreaDescricao.getText();
                Movimentacao mov = new Movimentacao(
                        valor,
                        data,
                        hora,
                        categoriaSelecionada,
                        descricao,
                        tipo//Cat
                );
                movNegocio.salvar(mov);
                PrintUtil.mostrarMenssagemSucesso("Cadastro realizado com sucesso!");
                voltar(event);
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }

        }
        else {// Se for clicar em editar
            try {
                double valor;
                if(textFieldValor.getText().isEmpty() || !(validateValor(textFieldValor.getText()))){
                    movimentacaoSelecionada.setValor(0);
                } else {
                    movimentacaoSelecionada.setValor(Double.parseDouble(textFieldValor.getText()));
                }
                
                
                if(datePickerData.getValue() == null){
                    movimentacaoSelecionada.setData(null);
                } else {
                    movimentacaoSelecionada.setData(datePickerData.getValue());
                }
                
                
                if(textFieldHora.getText().isEmpty()){
                    movimentacaoSelecionada.setHora(null);
                } else if(validateHora(textFieldHora.getText())){
                    movimentacaoSelecionada.setHora(LocalTime.parse(textFieldHora.getText()));
                } else {
                    movimentacaoSelecionada.setHora(null);
                }                
                
                if(tipogroup.getSelectedToggle()== radReceita){
                    movimentacaoSelecionada.setTipo(true);
                } else {
                    movimentacaoSelecionada.setTipo(false);
                }
                
                if(ComboCategoria.getValue() == null){
                    movimentacaoSelecionada.setCategoria(null);
                } else{
                    Categoria categoriaEscolhida = categoriaNegocio.procurarPorNome(ComboCategoria.getValue());
                    movimentacaoSelecionada.setCategoria(categoriaEscolhida);
                }
                movimentacaoSelecionada.setDescricao(textAreaDescricao.getText());
                movimentacaoNegocio.atualizar(movimentacaoSelecionada);
                PrintUtil.mostrarMenssagemSucesso("Cadastro atualizado com sucesso!");
                voltar(event);
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }
        }
    }

    private void carregaComboBox() {
        try {
            listaCategorias = catNegocio.listarPorTipo(this.tipo);
            for (Categoria x : listaCategorias){
                ComboCategoria.getItems().addAll(x.getTitulo());
            }
        } catch (NegocioException ex) {
            PrintUtil.mostrarMensagemErro(ex.getMessage());
        }
    }
    
    @FXML
    private void accionReceita(ActionEvent event) throws IOException {
        ComboCategoria.getItems().removeAll(ComboCategoria.getItems());
        tipogroup.selectToggle(radReceita);
        this.tipo = true;
        carregaComboBox();        
    }
    
    @FXML
    private void accionDespesa(ActionEvent event) throws IOException {
        ComboCategoria.getItems().removeAll(ComboCategoria.getItems());
        tipogroup.selectToggle(radDespesa);
        this.tipo = false;
        carregaComboBox();
    }
    
    @FXML
    private void accionVoltar(ActionEvent event) throws IOException {
        voltar(event);
    }
    
    @FXML
    private void accionCancelar(ActionEvent event) throws IOException {
        voltar(event);
    }
    
    // MÉTODOS AUXILIARES
    private void limparCampos() {
        textFieldValor.clear();
        textFieldHora.clear();
        textAreaDescricao.clear();
        ComboCategoria.getItems().removeAll(ComboCategoria.getItems());
    } 
    
    private void voltar(ActionEvent event) throws IOException{
        Stage stage = (Stage) painelFormularioMovimentacao.getScene().getWindow();
        stage.setTitle("MENU MOVIMENTAÇÕES");
        Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Movimentacao.fxml"));
        stage.setScene(new Scene(painelTelaPrincipal));  
    }

    private boolean validateValor(String text) {
        return text.matches("[0-9-.]*");
    }
    
    private boolean validateHora(String text) {
        return text.matches("[0-9-:]*");
    }

}

