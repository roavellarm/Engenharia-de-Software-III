package controller;

import mymoney.MyMoney;
import dominio.Categoria;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.CategoriaNegocio;
import negocio.NegocioException;
import view.PrintUtil;

/**
 *
 * @author Francke
 * 
 */
public class CategoriaController implements Initializable { 
    @FXML
    private AnchorPane telaMain;
    @FXML
    private AnchorPane painelFormularioCategoria;
    @FXML
    private TextField textField;
    @FXML
    private TableView<Categoria> tabelaCat;
    @FXML
    private TableColumn<Categoria, String> tableColumnID;
    @FXML
    private TableColumn<Categoria, String> tableColumnTitulo;
    @FXML
    private TableColumn<Categoria, String> tableColumnTipo;
    @FXML
    private ToggleGroup group;
    @FXML
    private ToggleButton btReceita;
    @FXML
    private ToggleButton btDespesa;
    @FXML
    private ToggleGroup tipogroup;
    @FXML
    private RadioButton radReceita;
    @FXML
    private RadioButton radDespesa;
    
    private CategoriaNegocio categoriaNegocio;
    private List<Categoria> listaCategorias;
    private ObservableList<Categoria> observableListaCategorias;
    private Boolean tipo;
    private Categoria categoriaSelecionada;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriaNegocio = new CategoriaNegocio();
        //Selecionar o botao Receita
        group = new ToggleGroup();
        group.selectToggle(btReceita);
        this.tipo = true;
        //Carregar tabela se houver registo no banco
        if (tabelaCat != null){
            carregarListaCategorias();
        }
    } 
      
    @FXML
    private void adicionar(ActionEvent event) throws IOException {
        try {
            Categoria cat = new Categoria(textField.getText(),tipo);
            categoriaNegocio.salvar(cat);
            PrintUtil.mostrarMenssagemSucesso("Cadastro realizado com sucesso!");
            limparCampo();
            carregarListaCategorias();  
        } catch (NegocioException ex) {
            PrintUtil.mostrarMensagemErro("Digite o titulo da Categoria para Adicionar");
        }
    }
    
    @FXML
    private void deletar(ActionEvent event) throws IOException {
        Categoria catSelecionada = tabelaCat.getSelectionModel().getSelectedItem();
        if (catSelecionada != null) {
            try {
                categoriaNegocio.deletar(catSelecionada);
                this.carregarListaCategorias();
                PrintUtil.mostrarMenssagemSucesso("Categoria removida com sucesso!");
                limparCampo();
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }
        } else {
            PrintUtil.mostrarMensagemErro("Selecione uma Categoria para remover!");
        }
    }
    
    @FXML
    private void irEditar(ActionEvent event) throws IOException {
        categoriaSelecionada = tabelaCat.getSelectionModel().getSelectedItem();
        if (categoriaSelecionada != null) {
            FXMLLoader loader = new FXMLLoader(MyMoney.class.getResource("/view/FormularioCategoria.fxml"));
            Parent root = (Parent) loader.load();

            CategoriaController catController = (CategoriaController) loader.getController();
            catController.setCategoriaSelecionada(categoriaSelecionada);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(telaMain.getScene().getWindow());
            dialogStage.showAndWait();
            carregarListaCategorias();
        } else {
            PrintUtil.mostrarMensagemErro("Precisa selecionar uma categoria para esta opção");
        }
    }
    
    @FXML
    private void accionCancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioCategoria.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
    private void accionSalvar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioCategoria.getScene().getWindow();

        if (categoriaSelecionada == null) //Se for cadastrar
        {
            try {
                String titulo = textField.getText();
                Boolean tipoCat = categoriaSelecionada.getTipoBoolean();                
                Categoria cat = new Categoria(titulo, tipoCat);
                categoriaNegocio.salvar(cat);
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }

        } else //Se for editar
        {
            try {
                categoriaSelecionada.setTitulo(textField.getText());
                if (tipogroup.getSelectedToggle() == radReceita){
                    categoriaSelecionada.setTipo(true);                      
                }
                else if(tipogroup.getSelectedToggle() == radDespesa){
                    categoriaSelecionada.setTipo(false);
                }
                categoriaNegocio.atualizar(categoriaSelecionada);
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.mostrarMensagemErro(ex.getMessage());
            }
        }
    }
    
    @FXML
    private void voltarMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) telaMain.getScene().getWindow();
        stage.setTitle("MY MONEY - MENU INICIAL");
        Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(painelTelaPrincipal));
    }

    @FXML
    private void listarPorNome(ActionEvent event) throws IOException {
        try {
            Categoria cat = new Categoria(textField.getText(),tipo);
            boolean resposta = categoriaNegocio.categoriaExiste(cat.getTitulo());
            if (resposta){
                PrintUtil.mostrarMenssagemSucesso("Categoria "+cat.getTitulo()+" já existe!");
            } else {
                PrintUtil.mostrarMenssagemSucesso("Categoria "+cat.getTitulo()+" não existe!");
            }
            limparCampo();            
        } catch (NegocioException ex) {
            PrintUtil.mostrarMensagemErro(ex.getMessage());
        }
    }

    @FXML
    private void pesquisaPortitulo(ActionEvent event) throws IOException {

    }

    @FXML
    private void irBtReceita(ActionEvent event) throws IOException {
        this.tipo = true;
        limparCampo();
        carregarListaCategorias();
    }
    
    @FXML
    private void irBtDespesa(ActionEvent event) throws IOException {
        this.tipo = false;
        limparCampo();
        carregarListaCategorias();
    }
    
    private void limparCampo() {
        textField.clear();
    }  

    private void carregarListaCategorias() {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tableColumnTipo.setCellValueFactory( 
            new Callback<TableColumn.CellDataFeatures<Categoria,String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Categoria, String> linha) {
                    final Categoria cat = linha.getValue();
                    String tipo;
                    if(cat.getTipoBoolean()){
                        tipo = "Receita";
                    } else {
                        tipo = "Despesa";
                    }
                    final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(tipo);
                    return simpleObject;
                }
            }
        );
        mostrarListaPorTipo();
    }

    private void mostrarListaPorTipo() {
        try {
            listaCategorias = categoriaNegocio.listarPorTipo(this.tipo);
            observableListaCategorias = FXCollections.observableArrayList(listaCategorias);
            tabelaCat.setItems(observableListaCategorias);
        } catch (NegocioException ex) {
            PrintUtil.mostrarMensagemErro(ex.getMessage());
        }
    }

    private void setCategoriaSelecionada(Categoria catSelecionada) {
        this.categoriaSelecionada = catSelecionada;
        textField.setText(catSelecionada.getTitulo());
        if(catSelecionada.getTipoBoolean()){
           tipogroup.selectToggle(radReceita); 
        } else {
           tipogroup.selectToggle(radDespesa); 
        }
    }
}
