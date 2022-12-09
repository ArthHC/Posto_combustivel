package Trabalho_posto.Posto_combustivel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.ClienteDao;
import classes.Cliente;
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

public class TelaClienteController implements Initializable {


		@FXML
		private TextField textFieldId;

		@FXML
		private TextField textFieldNome;
		
		@FXML
		private TextField textFieldSobrenome;

		@FXML
		private TextField textFieldCpf;
		
		@FXML
		private TextField textFieldTelefone;

		@FXML
		private TextField textFieldEndereco;

		@FXML
		private TextField textFieldEmail;

		@FXML
		private Button buttonSalvar;

		@FXML
		private TableView<Cliente> tableViewCliente;

		@FXML
		private TableColumn<Cliente, Integer> tableColumnIdCli;

		@FXML
		private TableColumn<Cliente, String> tableColumnNome;
		
		@FXML
		private TableColumn<Cliente, String> tableColumnSobrenome;
		
		@FXML
		private TableColumn<Cliente, String> tableColumnCpf;

		@FXML
		private TableColumn<Cliente, String> tableColumnTelefone;
		
		@FXML
		private TableColumn<Cliente, String> tableColumnEndereco;
		
		@FXML
		private TableColumn<Cliente, String> tableColumnEmail;

		@FXML
		private Button buttonExcluir;
		private ObservableList<Cliente> observableTable;
	 
		private Cliente clienteSelecionado;
		@FXML
		private Button buttonNovo;

		private ClienteDao clienteDao;

		
		@Override
		public void initialize(URL location, java.util.ResourceBundle resources) {
 
			clienteDao = new ClienteDao();
			popularTabelaCliente();
			tableViewCliente.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> selecionarCliente(newValue));
		}

		@FXML
		private void handlerSalvar(ActionEvent event) {
			try {
				String strId = textFieldId.getText();
				Integer id = null;
				if (strId != null && strId.length() > 0) {

					id = Integer.valueOf(Integer.parseInt(strId));
				}
				String nome = textFieldNome.getText();
				String sobrenome = textFieldSobrenome.getText();
				String cpf = textFieldCpf.getText();
				String telefone = textFieldTelefone.getText();
				String endereco = textFieldEndereco.getText();
				String email = textFieldEmail.getText();

				Cliente c = new Cliente();
				
				c.setNome(nome);
				c.setSobrenome(sobrenome);
				c.setCpfCli(cpf);
				c.setTelefone(telefone);
				c.setEndereco(endereco);
				c.setEmail(email);

				if (id != null) {
					c.setIdCliente(id);
					clienteDao.alterar(c);
				} else {
					clienteDao.inserir(c);
					limpar();
				}

				popularTabelaCliente();
			} catch (NumberFormatException e) {
				System.out.println("Alguma coisa errada!");
			}
		}

		public void popularTabelaCliente() {
			List<Cliente> lst = clienteDao.listar();
			tableColumnIdCli.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
			tableColumnSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
			tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
			tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
			tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			observableTable = FXCollections.observableArrayList(lst);
			tableViewCliente.setItems(observableTable);
		}

		private void selecionarCliente(Cliente cliente) {
			clienteSelecionado = cliente;
			textFieldId.setText(String.valueOf(cliente.getIdCliente()));
			textFieldNome.setText(String.valueOf(cliente.getNome()));
			textFieldSobrenome.setText(String.valueOf(cliente.getSobrenome()));
			textFieldCpf.setText(String.valueOf(cliente.getCpfCli()));
			textFieldTelefone.setText(String.valueOf(cliente.getTelefone()));
			textFieldEndereco.setText(String.valueOf(cliente.getEndereco()));
			textFieldEmail.setText(String.valueOf(cliente.getEmail()));

		}

		@FXML
		private void handlerExcluirCliente(ActionEvent event) {
			if (clienteSelecionado != null) {
				clienteDao.remover(clienteSelecionado );
				popularTabelaCliente();
			}

		}

		@FXML
		private void handlerNovoCliente(ActionEvent event) {
			limpar();

		}

		private void limpar() {
			textFieldId.setText(null);
			textFieldNome.setText(null);
			textFieldSobrenome.setText(null);
			textFieldCpf.setText(null);
			textFieldTelefone.setText(null);
			textFieldEndereco.setText(null);
			textFieldEmail.setText(null);
		}

	
}

