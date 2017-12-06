package controller;

import dao.impl_DB.UsuarioDaoBD;
import dominio.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private AnchorPane painelFormularioUsuario;
    @FXML
    private TextField nome;
    @FXML
    private TextField sobrenome;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
        
    private UsuarioDaoBD userDAO;
    private UsuarioNegocio movNegocio;
    private UsuarioNegocio catNegocio;
    private UsuarioNegocio movimentacaoNegocio;
    private UsuarioNegocio usuarioNegocio;
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSelecionado;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usuarioNegocio = new UsuarioNegocio();
    }
    
    @FXML
    private void entrar(ActionEvent event) throws IOException {
        String valorlogin = loginText.getText();
        String valorsenha = senhaText.getText();
        
        try {
            Usuario user = usuarioNegocio.validarCamposObrigatoriosLogin(valorlogin, valorsenha);
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
    private void voltar2(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioUsuario.getScene().getWindow();
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
            limparCampos2();
            Stage stage = (Stage) painelFormularioUsuario.getScene().getWindow();
            stage.setTitle("MY MONEY - Login");
            Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"));
            stage.setScene(new Scene(painelTelaPrincipal));
        } catch (NegocioException ex) {
            PrintUtil.mostrarMensagemErro("Problema ao adicionar Usuário");
        }
    }
     
    private void limparCampos2() {
        nome.clear();
        sobrenome.clear();
        email.clear();
        senha.clear();
    } 
     
    private void limparCampos() {
        loginText.clear();
        senhaText.clear();
    } 
}

