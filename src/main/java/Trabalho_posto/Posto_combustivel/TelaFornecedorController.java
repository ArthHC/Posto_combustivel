package Trabalho_posto.Posto_combustivel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.FornecedorDao;
import classes.Fornecedor;
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

public class TelaFornecedorController implements Initializable {


		@FXML
		private TextField textFieldId;

		@FXML
		private TextField textFieldNome;
		
		@FXML
		private TextField textFieldCnpj;
		
		@FXML
		private TextField textFieldTelefone;

		@FXML
		private TextField textFieldEndereco;

		@FXML
		private TextField textFieldEmail;

		@FXML
		private Button buttonSalvarFornecedor;

		@FXML
		private TableView<Fornecedor> tableViewFornecedor;

		@FXML
		private TableColumn<Fornecedor, Integer> tableColumnId;

		@FXML
		private TableColumn<Fornecedor, String> tableColumnNome;
		
		@FXML
		private TableColumn<Fornecedor, String> tableColumnCnpj;

		@FXML
		private TableColumn<Fornecedor, String> tableColumnTelefone;
		
		@FXML
		private TableColumn<Fornecedor, String> tableColumnEndereco;
		
		@FXML
		private TableColumn<Fornecedor, String> tableColumnEmail;

		@FXML
		private Button buttonExcluirFornecedor;
		private ObservableList<Fornecedor> observableTable;
	 
		private Fornecedor fornecedorSelecionado;
		@FXML
		private Button buttonNovoFornecedor;

		private FornecedorDao fornecedorDao;

		
		@Override
		public void initialize(URL location, java.util.ResourceBundle resources) {
 
			fornecedorDao = new FornecedorDao();
			popularTabelaFornecedor();
			tableViewFornecedor.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> selecionarFornecedor(newValue));
		}

		@FXML
		private void handlerSalvarFornecedor(ActionEvent event) {
			try {
				String strId = textFieldId.getText();
				Integer id = null;
				if (strId != null && strId.length() > 0) {

					id = Integer.valueOf(Integer.parseInt(strId));
				}
				String nome = textFieldNome.getText();
				String cnpj = textFieldCnpj.getText();
				String telefone = textFieldTelefone.getText();
				String endereco = textFieldEndereco.getText();
				String email = textFieldEmail.getText();

				Fornecedor f = new Fornecedor();
				
				f.setNomeForn(nome);
				f.setCnpj(cnpj);
				f.setTelefoneForn(telefone);
				f.setEndereco(endereco);
				f.setEmailForn(email);

				if (id != null) {
					f.setIdFornecedor(id);
					fornecedorDao.alterar(f);
				} else {
					fornecedorDao.inserir(f);
					limpar();
				}

				popularTabelaFornecedor();
			} catch (NumberFormatException e) {
				System.out.println("Alguma coisa errada!");
			}
		}

		public void popularTabelaFornecedor() {
			List<Fornecedor> lst = fornecedorDao.listar();
			tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome_cli"));
			tableColumnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
			tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone_cli"));
			tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
			tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			observableTable = FXCollections.observableArrayList(lst);
			tableViewFornecedor.setItems(observableTable);
		}

		private void selecionarFornecedor(Fornecedor fornecedor) {
			fornecedorSelecionado = fornecedor;
			textFieldId.setText(String.valueOf(fornecedor.getIdFornecedor()));
			textFieldNome.setText(String.valueOf(fornecedor.getNomeForn()));
			textFieldCnpj.setText(String.valueOf(fornecedor.getCnpj()));
			textFieldTelefone.setText(String.valueOf(fornecedor.getTelefoneForn()));
			textFieldEndereco.setText(String.valueOf(fornecedor.getEndereco()));
			textFieldEmail.setText(String.valueOf(fornecedor.getEmailForn()));

		}

		@FXML
		private void handlerExcluirFornecedor(ActionEvent event) {
			if (fornecedorSelecionado != null) {
				fornecedorDao.remover(fornecedorSelecionado );
				popularTabelaFornecedor();
			}

		}

		@FXML
		private void handlerNovoFornecedor(ActionEvent event) {
			limpar();

		}

		private void limpar() {
			textFieldId.setText(null);
			textFieldNome.setText(null);
			textFieldCnpj.setText(null);
			textFieldTelefone.setText(null);
			textFieldEndereco.setText(null);
			textFieldEmail.setText(null);
		}

	
}