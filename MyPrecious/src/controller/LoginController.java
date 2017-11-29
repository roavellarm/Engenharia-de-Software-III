package controller;

import dao.impl_DB.UsuarioDaoBD;
import dominio.Categoria;
import dominio.Usuario;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.UsuarioNegocio;
import negocio.NegocioException;
import view.PrintUtil;

/**
 *
 * @author rodrigo
 */
public class LoginController implements Initializable  {

    @FXML
    private AnchorPane telaMain;
    @FXML
    private TextField loginText;
    @FXML
    private PasswordField senhaText;
    @FXML
    private TextField nome;
    @FXML
    private TextField sobrenome;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    
    private UsuarioNegocio movimentacaoNegocio;
    private UsuarioNegocio usuarioNegocio;
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSelecionado;
    
    // Controller de Menu Usuario
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioNegocio = new UsuarioNegocio();
    }
    
    @FXML
    private void entrar(ActionEvent event) throws IOException {
        String login = loginText.getText();
        String senha = senhaText.getText();
        
        try {
            Usuario user = usuarioNegocio.validarCamposObrigatoriosLogin(login, senha);
            Stage stage = (Stage) telaMain.getScene().getWindow();
            stage.setTitle("MENU MOVIMENTAÇÕES");
            Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Movimentacao.fxml"));
            stage.setScene(new Scene(painelTelaPrincipal));
        } catch (NegocioException ex) {
             PrintUtil.mostrarMensagemErro(ex.getMessage());
        }
    }

    @FXML
    private void cadastrar(ActionEvent event) {
        try {
            Stage stage = (Stage) telaMain.getScene().getWindow();
            stage.setTitle("Formulario de Cadastro");
            Parent painelTelaPrincipal;
            painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/FormularioUsuario.fxml"));
            stage.setScene(new Scene(painelTelaPrincipal));     
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        Stage stage = (Stage) telaMain.getScene().getWindow();
        stage.setTitle("MY MONEY - Login");
        Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"));
        stage.setScene(new Scene(painelTelaPrincipal));
    }
    
    @FXML
    private void salvar(ActionEvent event) throws IOException {
        try {
            Usuario user = new Usuario();
            user.setNome(nome.getText());
            user.setSobrenome(sobrenome.getText());
            user.setEmail(email.getText());
            user.setSenha(senha.getText());
            usuarioNegocio.salvar(user);
            PrintUtil.mostrarMenssagemSucesso("Cadastro realizado com sucesso!");
            limparCampos(); 
        } catch (NegocioException ex) {
            PrintUtil.mostrarMensagemErro("Problema ao adicionar Usuário");
        }
    }
    
    @FXML
    private AnchorPane painelFormularioUsuario;
        
    private UsuarioDaoBD userDAO;
    private UsuarioNegocio movNegocio;
    private UsuarioNegocio catNegocio;

//    private void carregarCamposParaEditar(Usuario movSelecionada){
//        this.usuarioSelecionado = movSelecionada;
//        loginText.setText(Double.toString(usuarioSelecionado.getValor())); //Carrega campo valor
//        datePickerData.setValue(usuarioSelecionado.getData()); // Carrega campo data
//        textFieldHora.setText(usuarioSelecionado.getHora().toString().substring(0, 5)); // Carrega campo hora
//        if (usuarioSelecionado.isTipo()){   // Carrega btRadio receita ou despesa
//            tipogroup.selectToggle(radReceita);
//        } else {
//            tipogroup.selectToggle(radDespesa);
//        }
//        ComboCategoria.setValue(usuarioSelecionado.getCategoria().getTitulo()); // Carrega combo categoria
//        textAreaDescricao.setText(usuarioSelecionado.getDescricao()); // Carrega campo descrição
//    }

//    @FXML
//    private void accionSalvar(ActionEvent event) throws IOException {
//        Stage stage = (Stage) painelFormularioUsuario.getScene().getWindow();
//        if (usuarioSelecionado == null) //Se for cadastrar
//        {
//            try {
//                double valor;
//                
//                if(loginText.getText().isEmpty() || !(validateValor(loginText.getText()))){
//                    valor = 0;
//                } else {
//                    valor = Double.parseDouble(loginText.getText());
//                }
//                
//                LocalDate data;
//                if(datePickerData.getValue() != null){
//                    data = datePickerData.getValue();
//                } else {
//                    data = null;
//                }
//                
//                LocalTime hora;
//                if(textFieldHora.getText().isEmpty()){
//                    hora = null;
//                } else if(validateHora(textFieldHora.getText())){
//                    hora = LocalTime.parse(textFieldHora.getText(), DateTimeFormatter.ISO_TIME);
//                } else {
//                        hora = null;                    
//                }
//                
//                if(ComboCategoria.getValue() == null){
//                    categoriaSelecionada = null;
//                } else{
//                    String catEscolhida = ComboCategoria.getValue().toString();
//                    categoriaSelecionada = catNegocio.procurarPorNome(catEscolhida);
//                }
//                String descricao = textAreaDescricao.getText();
//                Usuario mov = new Usuario(
//                        valor,
//                        data,
//                        hora,
//                        categoriaSelecionada,
//                        descricao,
//                        tipo//Cat
//                );
//                movNegocio.salvar(mov);
//                PrintUtil.mostrarMenssagemSucesso("Cadastro realizado com sucesso!");
//                voltar(event);
//            } catch (NegocioException ex) {
//                PrintUtil.mostrarMensagemErro(ex.getMessage());
//            }
//
//        }
//        else {// Se for clicar em editar
//            try {
//                double valor;
//                if(loginText.getText().isEmpty() || !(validateValor(loginText.getText()))){
//                    usuarioSelecionado.setValor(0);
//                } else {
//                    usuarioSelecionado.setValor(Double.parseDouble(loginText.getText()));
//                }
//                
//                
//                if(datePickerData.getValue() == null){
//                    usuarioSelecionado.setData(null);
//                } else {
//                    usuarioSelecionado.setData(datePickerData.getValue());
//                }
//                
//                
//                if(textFieldHora.getText().isEmpty()){
//                    usuarioSelecionado.setHora(null);
//                } else if(validateHora(textFieldHora.getText())){
//                    usuarioSelecionado.setHora(LocalTime.parse(textFieldHora.getText()));
//                } else {
//                    usuarioSelecionado.setHora(null);
//                }                
//                
//                if(tipogroup.getSelectedToggle()== radReceita){
//                    usuarioSelecionado.setTipo(true);
//                } else {
//                    usuarioSelecionado.setTipo(false);
//                }
//                
//                if(ComboCategoria.getValue() == null){
//                    usuarioSelecionado.setCategoria(null);
//                } else{
//                    Categoria categoriaEscolhida = usuarioNegocio.procurarPorNome(ComboCategoria.getValue());
//                    usuarioSelecionado.setCategoria(categoriaEscolhida);
//                }
//                usuarioSelecionado.setDescricao(textAreaDescricao.getText());
//                movimentacaoNegocio.atualizar(usuarioSelecionado);
//                PrintUtil.mostrarMenssagemSucesso("Cadastro atualizado com sucesso!");
//                voltar(event);
//            } catch (NegocioException ex) {
//                PrintUtil.mostrarMensagemErro(ex.getMessage());
//            }
//        }
//    }
//
//    private void carregaComboBox() {
//        try {
//            listaCategorias = catNegocio.listarPorTipo(this.tipo);
//            for (Categoria x : listaCategorias){
//                ComboCategoria.getItems().addAll(x.getTitulo());
//            }
//        } catch (NegocioException ex) {
//            PrintUtil.mostrarMensagemErro(ex.getMessage());
//        }
//    }
//    
//    @FXML
//    private void accionReceita(ActionEvent event) throws IOException {
//        ComboCategoria.getItems().removeAll(ComboCategoria.getItems());
//        tipogroup.selectToggle(radReceita);
//        this.tipo = true;
//        carregaComboBox();        
//    }
//    
//    @FXML
//    private void accionDespesa(ActionEvent event) throws IOException {
//        ComboCategoria.getItems().removeAll(ComboCategoria.getItems());
//        tipogroup.selectToggle(radDespesa);
//        this.tipo = false;
//        carregaComboBox();
//    }
//    
//    @FXML
//    private void accionVoltar(ActionEvent event) throws IOException {
//        voltar(event);
//    }
//    
//    @FXML
//    private void accionCancelar(ActionEvent event) throws IOException {
//        voltar(event);
//    }
//    
    // MÉTODOS AUXILIARES
    private void limparCampos() {
        loginText.clear();
        nome.clear();
        sobrenome.clear();
        email.clear();
        senha.clear();
    } 
//    
//    private void voltar(ActionEvent event) throws IOException{
//        Stage stage = (Stage) painelFormularioUsuario.getScene().getWindow();
//        stage.setTitle("MENU MOVIMENTAÇÕES");
//        Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Usuario.fxml"));
//        stage.setScene(new Scene(painelTelaPrincipal));  
//    }
//
//    private boolean validateValor(String text) {
//        return text.matches("[0-9-.]*");
//    }
//    
//    private boolean validateHora(String text) {
//        return text.matches("[0-9-:]*");
//    }

}

