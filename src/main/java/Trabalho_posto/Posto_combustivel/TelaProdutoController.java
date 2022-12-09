package Trabalho_posto.Posto_combustivel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.ProdutoDao;
import classes.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaProdutoController implements Initializable {


			@FXML
			private TextField textFieldId;

			@FXML
			private TextField textFieldNome;
			
			@FXML
			private TextField textFieldCodigo;
			
			@FXML
			private TextField textFieldQtde;

			@FXML
			private TextField textFieldValor;

			@FXML
			private TextField textFieldIdCombustivel;

			@FXML
			private Button buttonSalvarProduto;

			@FXML
			private TableView<Produto> tableViewProduto;

			@FXML
			private TableColumn<Produto, Integer> tableColumnId;

			@FXML
			private TableColumn<Produto, String> tableColumnNome;
			
			@FXML
			private TableColumn<Produto, Integer> tableColumnCodigo;

			@FXML
			private TableColumn<Produto, Float> tableColumnQtde;
			
			@FXML
			private TableColumn<Produto, Float> tableColumnValor;
			
			@FXML
			private TableColumn<Produto, Integer> tableColumnIdCombustivel;

			@FXML
			private Button buttonExcluirProduto;
			private ObservableList<Produto> observableTable;
		 
			private Produto produtoSelecionado;
			@FXML
			private Button buttonNovoProduto;

			private ProdutoDao produtoDao;

			
			@Override
			public void initialize(URL location, java.util.ResourceBundle resources) {
	 
				produtoDao = new ProdutoDao();
				popularTabelaProduto();
				tableViewProduto.getSelectionModel().selectedItemProperty()
						.addListener((observable, oldValue, newValue) -> selecionarProduto(newValue));
			}

			@FXML
			private void handlerSalvarProduto(ActionEvent event) {
				try {
					String strId = textFieldId.getText();
					Integer id = null;
					if (strId != null && strId.length() > 0) {

						id = Integer.valueOf(Integer.parseInt(strId));
					}
					String nome = textFieldNome.getText();
					
					String strCod = textFieldCodigo.getText();
					Integer cod = null;
					if (strCod != null && strCod.length() >0 ){
						
						cod = Integer.valueOf(Integer.parseInt(strCod));
					}
					
					String strQtde = textFieldQtde.getText();
					Float qtde = null;
					if (strQtde != null && strQtde.length() >0 ){
						
						qtde = Float.valueOf(Float.parseFloat(strQtde));
					}
					
					String strValor = textFieldValor.getText();
					Float valor = null;
					if (strValor != null && strValor.length() >0 ){
						
						valor = Float.valueOf(Float.parseFloat(strValor));
					}
					
					String strIdCombustivel = textFieldIdCombustivel.getText();
					Integer IdCombustivel = null;
					if (strIdCombustivel != null && strIdCombustivel.length() >0 ){
						
						IdCombustivel = Integer.valueOf(Integer.parseInt(strIdCombustivel));
					}

					Produto prod = new Produto();
					
					prod.setNomeProduto(nome);
					prod.setCodigoBarras(cod);
					prod.setQtde(qtde);
					prod.setValor(valor);
					prod.setFkCombustivel(IdCombustivel);

					if (id != null) {
						prod.setIdProduto(id);
						produtoDao.alterar(prod);
					} else {
						produtoDao.inserir(prod);
						limpar();
					}

					popularTabelaProduto();
				} catch (NumberFormatException e) {
					System.out.println("Alguma coisa errada!");
				}
			}

			public void popularTabelaProduto() {
				List<Produto> lst = produtoDao.listar();
				tableColumnId.setCellValueFactory(new PropertyValueFactory<>("IdProduto"));
				tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Nome_produto"));
				tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
				tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
				tableColumnQtde.setCellValueFactory(new PropertyValueFactory<>("qtde"));
				tableColumnIdCombustivel.setCellValueFactory(new PropertyValueFactory<>("FkCombustivel"));
				observableTable = FXCollections.observableArrayList(lst);
				tableViewProduto.setItems(observableTable);
			}

			private void selecionarProduto(Produto produto) {
				produtoSelecionado = produto;
				textFieldId.setText(String.valueOf(produto.getIdProduto()));
				textFieldNome.setText(String.valueOf(produto.getNomeProduto()));
				textFieldCodigo.setText(String.valueOf(produto.getCodigoBarras()));
				textFieldValor.setText(String.valueOf(produto.getValor()));
				textFieldQtde.setText(String.valueOf(produto.getQtde()));
				textFieldIdCombustivel.setText(String.valueOf(produto.getFkCombustivel()));

			}

			@FXML
			private void handlerExcluirProduto(ActionEvent event) {
				if (produtoSelecionado != null) {
					produtoDao.remover(produtoSelecionado );
					popularTabelaProduto();
				}

			}

			@FXML
			private void handlerNovoProduto(ActionEvent event) {
				limpar();

			}

			private void limpar() {
				textFieldId.setText(null);
				textFieldNome.setText(null);
				textFieldCodigo.setText(null);
				textFieldValor.setText(null);
				textFieldQtde.setText(null);
				textFieldIdCombustivel.setText(null);
			}

		
}
