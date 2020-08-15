package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.service.DepartmentService;

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
		loadView("/gui/DepartmentList.fxml",(DepartmentListController controller)->{
			controller.setDepartmentService(new DepartmentService());
			controller.upadateTableView();
		});
	}
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml",x ->{});
	}
	
	
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
	
	
	private synchronized <T> void loadView(String absoluteName,Consumer<T> initializingAction) {
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
			
			T controller = loader.getController();
			initializingAction.accept(controller);
		}
		catch (IOException e) {
			Alerts.showAlert("erro","erro ao carregar a pagina",
					e.getMessage(), AlertType.ERROR);
		}
		
	}

}
