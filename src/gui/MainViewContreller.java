package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewContreller implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartamento;
	@FXML
	private MenuItem menuItemAbout;
	
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	public void onMenuItemDepartamentoAction() {
		loadView("/gui/DepartmentList.fxml");
	}
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
	
	
	private synchronized void loadView(String absoluteName) {
		//chamar outra pagina 
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch (IOException e) {
			Alerts.showAlert("erro","erro ao carregar a pagina",
					e.getMessage(), AlertType.ERROR);
		}
		
	}

}
