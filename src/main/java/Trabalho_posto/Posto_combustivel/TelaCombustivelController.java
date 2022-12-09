package Trabalho_posto.Posto_combustivel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.CombustivelDao;
import classes.Combustivel;
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

public class TelaCombustivelController implements Initializable {
	
	@FXML
	private TextField textFieldId;
	
	@FXML
	private TextField textFieldTipo;
	
	@FXML
	private Button buttonSalvarCombustivel;
	
	@FXML
	private Button buttonNovoCombustivel;
	
	@FXML
	private Button buttonExcluirCombustivel;
	
	@FXML
	private TableView<Combustivel> tableViewCombustivel;
	
	@FXML
	private TableColumn<Combustivel, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Combustivel, String> tableColumnTipo;	
	
	private ObservableList<Combustivel> observableTable;
	
	private Combustivel combustivelSelecionado;
	
	private CombustivelDao combustivelDao;
	
	@Override
	public void initialize(URL location, java.util.ResourceBundle resources) {

		combustivelDao = new CombustivelDao();
		popularTabelaCombustivel();
		tableViewCombustivel.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarCombustivel(newValue));
	}
	
	@FXML
	private void handlerSalvarCombustivel(ActionEvent event) {
		try {
			String strId = textFieldId.getText();
			Integer id = null;
			if (strId != null && strId.length() > 0) {

				id = Integer.valueOf(Integer.parseInt(strId));
			}
			String tipo = textFieldTipo.getText();
			
			Combustivel com = new Combustivel();
			
			com.setTipoCombustivel(tipo);
			
			if (id != null) {
				com.setIdCombustivel(id);
				combustivelDao.alterar(com);
			} else {
				combustivelDao.inserir(com);
				limpar();
			}

			popularTabelaCombustivel();
		} catch (NumberFormatException e) {
			System.out.println("Alguma coisa errada!");
		}
	}
	public void popularTabelaCombustivel() {
		List<Combustivel> lst = combustivelDao.listar();
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnTipo.setCellValueFactory(new PropertyValueFactory<>("Combustivel"));
		observableTable = FXCollections.observableArrayList(lst);
		tableViewCombustivel.setItems(observableTable);
	}

	private void selecionarCombustivel(Combustivel combustivel) {
		combustivelSelecionado = combustivel;
		textFieldId.setText(String.valueOf(combustivel.getIdCombustivel()));
		textFieldTipo.setText(String.valueOf(combustivel.getTipoCombustivel()));

	}

	@FXML
	private void handlerExcluirCombustivel(ActionEvent event) {
		if (combustivelSelecionado != null) {
			combustivelDao.remover(combustivelSelecionado );
			popularTabelaCombustivel();
		}

	}

	@FXML
	private void handlerNovoCombustivel(ActionEvent event) {
		limpar();

	}

	private void limpar() {
		textFieldId.setText(null);
		textFieldTipo.setText(null);
	}

}	

