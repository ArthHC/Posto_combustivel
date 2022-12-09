package Trabalho_posto.Posto_combustivel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.PagamentoDao;
import classes.Pagamento;
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

public class TelaPagamentoController implements Initializable {
	
	@FXML
	private TextField textFieldId;
	
	@FXML
	private TextField textFieldTipo;
	
	@FXML
	private Button buttonSalvarPagamento;
	
	@FXML
	private Button buttonNovoPagamento;
	
	@FXML
	private Button buttonExcluirPagamento;
	
	@FXML
	private TableView<Pagamento> tableViewPagamento;
	
	@FXML
	private TableColumn<Pagamento, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Pagamento, String> tableColumnTipo;	
	
	private ObservableList<Pagamento> observableTable;
	
	private Pagamento pagamentoSelecionado;
	
	private PagamentoDao pagamentoDao;
	
	@Override
	public void initialize(URL location, java.util.ResourceBundle resources) {

		pagamentoDao = new PagamentoDao();
		popularTabelaPagamento();
		tableViewPagamento.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarPagamento(newValue));
	}
	
	@FXML
	private void handlerSalvarPagamento(ActionEvent event) {
		try {
			String strId = textFieldId.getText();
			Integer id = null;
			if (strId != null && strId.length() > 0) {

				id = Integer.valueOf(Integer.parseInt(strId));
			}
			String pag = textFieldTipo.getText();
			
			Pagamento p = new Pagamento();
			
			p.setPagamento(pag);
			
			if (id != null) {
				p.setIdPagamento(id);
				pagamentoDao.alterar(p);
			} else {
				pagamentoDao.inserir(p);
				limpar();
			}

			popularTabelaPagamento();
		} catch (NumberFormatException e) {
			System.out.println("Alguma coisa errada!");
		}
	}
	public void popularTabelaPagamento() {
		List<Pagamento> lst = pagamentoDao.listar();
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("0"));
		tableColumnTipo.setCellValueFactory(new PropertyValueFactory<>("Pagamento"));
		observableTable = FXCollections.observableArrayList(lst);
		tableViewPagamento.setItems(observableTable);
	}

	private void selecionarPagamento(Pagamento pagamento) {
		pagamentoSelecionado = pagamento;
		textFieldId.setText(String.valueOf(pagamento.getIdPagamento()));
		textFieldTipo.setText(String.valueOf(pagamento.getPagamento()));

	}

	@FXML
	private void handlerExcluirPagamento(ActionEvent event) {
		if (pagamentoSelecionado != null) {
			pagamentoDao.remover(pagamentoSelecionado );
			popularTabelaPagamento();
		}

	}

	@FXML
	private void handlerNovoPagamento(ActionEvent event) {
		limpar();

	}

	private void limpar() {
		textFieldId.setText(null);
		textFieldTipo.setText(null);
	}

}	

