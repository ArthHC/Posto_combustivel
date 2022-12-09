package Trabalho_posto.Posto_combustivel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.PedidoDao;
import classes.Pedido;
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

public class TelaPedidoController implements Initializable {


		@FXML
		private TextField textFieldId;

		@FXML
		private TextField textFieldPedido;
		
		@FXML
		private TextField textFieldQtde;

		@FXML
		private TextField textFieldValor;
		
		@FXML
		private TextField textFieldIdPag;

		@FXML
		private TextField textFieldIdProd;

		@FXML
		private TextField textFieldIdCli;

		@FXML
		private Button buttonSalvarPedido;

		@FXML
		private TableView<Pedido> tableViewPedido;

		@FXML
		private TableColumn<Pedido, Integer> tableColumnIdPedido;

		@FXML
		private TableColumn<Pedido, String> tableColumnPedido;
		
		@FXML
		private TableColumn<Pedido, Float> tableColumnQtde;
		
		@FXML
		private TableColumn<Pedido, Float> tableColumnValor;

		@FXML
		private TableColumn<Pedido, Integer> tableColumnIdPag;
		
		@FXML
		private TableColumn<Pedido, Integer> tableColumnIdProd;
		
		@FXML
		private TableColumn<Pedido, Integer> tableColumnIdCli;

		@FXML
		private Button buttonExcluirPedido;
		private ObservableList<Pedido> observableTable;
	 
		private Pedido pedidoSelecionado;
		@FXML
		private Button buttonNovoPedido;

		private PedidoDao pedidoDao;

		
		@Override
		public void initialize(URL location, java.util.ResourceBundle resources) {
 
			pedidoDao = new PedidoDao();
			popularTabelaPedido();
			tableViewPedido.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> selecionarPedido(newValue));
		}

		@FXML
		private void handlerSalvarPedido(ActionEvent event) {
			try {
				String strId = textFieldId.getText();
				Integer id = null;
				if (strId != null && strId.length() > 0) {

					id = Integer.valueOf(Integer.parseInt(strId));
				}
				
				String strIdPag = textFieldIdPag.getText();
				Integer IdPag = null;
				if (strIdPag != null && strIdPag.length() >0 ){
					
					IdPag = Integer.valueOf(Integer.parseInt(strIdPag));
				}
				
				String strIdProd = textFieldIdProd.getText();
				Integer IdProd = null;
				if (strIdProd != null && strIdProd.length() >0 ){
					
					IdProd = Integer.valueOf(Integer.parseInt(strIdProd));
				}
				
				String strIdCli = textFieldIdCli.getText();
				Integer IdCli = null;
				if (strIdCli != null && strIdCli.length() >0 ){
					
					IdCli = Integer.valueOf(Integer.parseInt(strIdCli));
				}
				
				String pedido = textFieldPedido.getText();
				
				String strValor = textFieldValor.getText();
				Float valor = null;
				if (strValor != null && strValor.length() >0 ){
					
					valor = Float.valueOf(Float.parseFloat(strValor));
				}
				
				String strQtde = textFieldQtde.getText();
				Float qtde = null;
				if (strQtde != null && strQtde.length() >0 ){
					
					qtde = Float.valueOf(Float.parseFloat(strQtde));
				}
				
				Pedido p = new Pedido();
				
				p.setFkPagamento(IdPag);
				p.setFkProduto(IdProd);
				p.setFkCliente(IdCli);
				p.setNomePedido(pedido);
				p.setValor(valor);
				p.setQtde(qtde);
				
				if (id != null) {
					p.setIdPedido(id);
					pedidoDao.alterar(p);
				} else {
					pedidoDao.inserir(p);
					limpar();
				}

				popularTabelaPedido();
			} catch (NumberFormatException e) {
				System.out.println("Alguma coisa errada!");
			}
		}

		public void popularTabelaPedido() {
			List<Pedido> lst = pedidoDao.listar();
			tableColumnIdCli.setCellValueFactory(new PropertyValueFactory<>("id"));
			tableColumnIdPag.setCellValueFactory(new PropertyValueFactory<>("id_pagamento"));
			tableColumnIdProd.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
			tableColumnIdCli.setCellValueFactory(new PropertyValueFactory<>("Id_cliente"));
			tableColumnPedido.setCellValueFactory(new PropertyValueFactory<>("nome_pedido"));
			tableColumnQtde.setCellValueFactory(new PropertyValueFactory<>("quantidade_pedido"));
			tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor_produto"));
			observableTable = FXCollections.observableArrayList(lst);
			tableViewPedido.setItems(observableTable);
		}

		private void selecionarPedido(Pedido pedido) {
			pedidoSelecionado = pedido;
			textFieldId.setText(String.valueOf(pedido.getIdPedido()));
			tableColumnIdPag.setText(String.valueOf(pedido.getFkPagamento()));
			tableColumnIdProd.setText(String.valueOf(pedido.getFkProduto()));
			tableColumnIdCli.setText(String.valueOf(pedido.getFkCliente()));
			tableColumnPedido.setText(String.valueOf(pedido.getNomePedido()));
			tableColumnQtde.setText(String.valueOf(pedido.getQtde()));
			tableColumnValor.setText(String.valueOf(pedido.getValor()));

		}

		@FXML
		private void handlerExcluirPedido(ActionEvent event) {
			if (pedidoSelecionado != null) {
				pedidoDao.remover(pedidoSelecionado );
				popularTabelaPedido();
			}

		}

		@FXML
		private void handlerNovoPedido(ActionEvent event) {
			limpar();

		}

		private void limpar() {
			textFieldId.setText(null);
			tableColumnPedido.setText(null);
			tableColumnQtde.setText(null);
			tableColumnValor.setText(null);
			tableColumnIdPag.setText(null);
			tableColumnIdProd.setText(null);
			tableColumnIdCli.setText(null);
		}

	
}
