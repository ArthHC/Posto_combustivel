package Trabalho_posto.Posto_combustivel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class TelaPrincipalController implements Initializable {

    @FXML
    private MenuItem menuItemCadastrarCliente;

    @FXML
    private MenuItem menuItemCadastrarFornecedor;
    
    @FXML
    private MenuItem menuItemCadastrarFuncionario;
    
    @FXML
    private MenuItem menuItemCadastrarProduto;
    
    @FXML
    private MenuItem menuItemCadastrarPedido;
    
    @FXML
    private MenuItem menuItemCadastrarCombustivel;
    
    @FXML
    private MenuItem menuItemTelasobre;
    
    @FXML
    private MenuItem menuAjuda;

    @FXML
    private AnchorPane anchorPaneFundo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handlerTelaCliente(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaCliente.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }

    @FXML
    private void handlerTelaFornecedor(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaFornecedor.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }

    @FXML
    private void handlerTelaFuncionario(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaFuncionario.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }

    @FXML
    private void handlerTelaProduto(javafx.event.ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaProduto.fxml"));
        anchorPaneFundo.getChildren().setAll(a);
    }
    @FXML
    private void handlerTelaPedido(javafx.event.ActionEvent event) throws IOException {
    	AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaPedido.fxml"));
    	anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerTelaCombustivel(javafx.event.ActionEvent event) throws IOException {
    	AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaCombustivel.fxml"));
    	anchorPaneFundo.getChildren().setAll(a);
    }

    @FXML
    private void handlerTelaPagamento(javafx.event.ActionEvent event) throws IOException {
    	AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaPagamento.fxml"));
    	anchorPaneFundo.getChildren().setAll(a);
    }
    
    @FXML
    private void handlerTelaAjuda(javafx.event.ActionEvent event) throws IOException {
    	AnchorPane a = FXMLLoader.load(getClass().getResource("/Trabalho_posto/Posto_combustivel/TelaSobre.fxml"));
    	anchorPaneFundo.getChildren().setAll(a);
    }
}