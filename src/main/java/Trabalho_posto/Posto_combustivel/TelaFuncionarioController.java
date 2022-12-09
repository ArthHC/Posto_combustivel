package Trabalho_posto.Posto_combustivel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.FuncionarioDao;
import classes.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class TelaFuncionarioController implements Initializable{

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldNome;
	
	@FXML
	private TextField textFieldSobrenome;

	@FXML
	private TextField textFieldCpf;
	
	@FXML
	private TextField textFieldPis;
	
	@FXML
	private TextField textFieldSalario;
	
	@FXML
	private TextField textFieldDepartamento;
	
	@FXML
	private TextField textFieldTelefone;

	@FXML
	private TextField textFieldEndereco;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private Button buttonSalvarFuncionario;

	@FXML
	private TableView<Funcionario> tableViewFuncionario;

	@FXML
	private TableColumn<Funcionario, Integer> tableColumnId;

	@FXML
	private TableColumn<Funcionario, String> tableColumnNome;
	
	@FXML
	private TableColumn<Funcionario, String> tableColumnSobrenome;
	
	@FXML
	private TableColumn<Funcionario, String> tableColumnCpf;
	
	@FXML
	private TableColumn<Funcionario, String> tableColumnPis;
	
	@FXML
	private TableColumn<Funcionario, Float> tableColumnSalario;
	
	@FXML
	private TableColumn<Funcionario, String> tableColumnDepartamento;

	@FXML
	private TableColumn<Funcionario, String> tableColumnTelefone;
	
	@FXML
	private TableColumn<Funcionario, String> tableColumnEndereco;
	
	@FXML
	private TableColumn<Funcionario, String> tableColumnEmail;

	@FXML
	private Button buttonExcluirFuncionario;
	private ObservableList<Funcionario> observableTable;
 
	private Funcionario funcionarioSelecionado;
	@FXML
	private Button buttonNovoFuncionario;

	private FuncionarioDao funcionarioDao;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		funcionarioDao = new FuncionarioDao();
		popularTabelaFuncionario();
		tableViewFuncionario.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarFuncionario(newValue));
	}
	
	@FXML
	private void handlerSalvarFuncionario(ActionEvent event) {
		try {
			String strId = textFieldId.getText();
			Integer idFuncionario = null;
			if (strId != null && strId.length() > 0) {

				idFuncionario = Integer.valueOf(Integer.parseInt(strId));
			}
			
			
			String nome = textFieldNome.getText();
			String sobrenome = textFieldSobrenome.getText();
			String cpf = textFieldCpf.getText();
			String pis = textFieldPis.getText();
			
			String strSalario = textFieldSalario.getText();
			Float salario = null;
			if (strSalario != null && strSalario.length() >0 ){
				
				salario = Float.valueOf(Float.parseFloat(strSalario));
			}
			
			String departamento = textFieldDepartamento.getText();
			String telefone = textFieldTelefone.getText();
			String endereco = textFieldEndereco.getText();
			String email = textFieldEmail.getText();

			Funcionario f = new Funcionario();

			f.setNome(nome);
			f.setSobrenome(sobrenome);
			f.setCpfFunc(cpf);
			f.setPis(pis);
			f.setSalario(salario);
			f.setDepartamento(departamento);
			f.setTelefone(telefone);
			f.setEndereco(endereco);
			f.setEmail(email);

			if (idFuncionario != null) {
				f.setIdFuncionario(idFuncionario);
				funcionarioDao.alterar(f);
			} else {
				funcionarioDao.inserir(f);
				limpar();
			}

			popularTabelaFuncionario();
		} catch (NumberFormatException e) {
			System.out.println("Alguma coisa errada!");
		}
	}

	public void popularTabelaFuncionario() {
		List<Funcionario> lst = funcionarioDao.listar();
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id_func"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome_func"));
		tableColumnSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
		tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf_func"));
		tableColumnPis.setCellValueFactory(new PropertyValueFactory<>("Pis_func"));
		tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone_func"));
		tableColumnSalario.setCellValueFactory(new PropertyValueFactory<>("salario_func"));
		tableColumnDepartamento.setCellValueFactory(new PropertyValueFactory<>("Departamento_func"));
		tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco_func"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email_func"));
		observableTable = FXCollections.observableArrayList(lst);
		tableViewFuncionario.setItems(observableTable);
	}

	private void selecionarFuncionario(Funcionario funcionario) {
		funcionarioSelecionado = funcionario;
		textFieldId.setText(String.valueOf(funcionario.getIdFuncionario()));
		textFieldNome.setText(String.valueOf(funcionario.getNome()));
		textFieldSobrenome.setText(String.valueOf(funcionario.getSobrenome()));
		textFieldCpf.setText(String.valueOf(funcionario.getCpfFunc()));
		textFieldPis.setText(String.valueOf(funcionario.getPis()));
		textFieldSalario.setText(String.valueOf(funcionario.getSalario()));
		textFieldDepartamento.setText(String.valueOf(funcionario.getDepartamento()));
		textFieldTelefone.setText(String.valueOf(funcionario.getTelefone()));
		textFieldEndereco.setText(String.valueOf(funcionario.getEndereco()));
		textFieldEmail.setText(String.valueOf(funcionario.getEmail()));

	}

	@FXML
	private void handlerExcluirFuncionario(ActionEvent event) {
		if (funcionarioSelecionado != null) {
			funcionarioDao.remover(funcionarioSelecionado );
			popularTabelaFuncionario();
		}

	}

	@FXML
	private void handlerNovoFuncionario(ActionEvent event) {
		limpar();

	}

	private void limpar() {
		textFieldId.setText(null);
		textFieldNome.setText(null);
		textFieldSobrenome.setText(null);
		textFieldCpf.setText(null);
		textFieldPis.setText(null);
		textFieldSalario.setText(null);
		textFieldDepartamento.setText(null);
		textFieldTelefone.setText(null);
		textFieldEndereco.setText(null);
		textFieldEmail.setText(null);
	}

}
