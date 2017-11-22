package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

/**
 *
 * @author Francke
 */
public class MainController  {
    @FXML
    private AnchorPane telaMain;
    @FXML
    private ImageView logo;
    
    @FXML
    private void irMenuMovimentacao(ActionEvent event) throws IOException {
        Stage stage = (Stage) telaMain.getScene().getWindow();
        stage.setTitle("MENU MOVIMENTAÇÕES");
        Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Movimentacao.fxml"));
        stage.setScene(new Scene(painelTelaPrincipal));
    }
    
    @FXML
    private void MenuCategoria(ActionEvent event) throws IOException {
        Stage stage = (Stage) telaMain.getScene().getWindow();
        stage.setTitle("MENU CATEGORIA");
        Parent painelTelaPrincipal = FXMLLoader.load(this.getClass().getResource("/view/Categoria.fxml"));
        stage.setScene(new Scene(painelTelaPrincipal));
    }
    
    @FXML
    private void fecharApp(ActionEvent event) throws IOException {
        System.exit(1);
    }      
    
}

