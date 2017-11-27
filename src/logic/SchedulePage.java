package logic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.*;



public class SchedulePage extends Application implements Page{
	/*This is all copied from the MainPage class, and is just for testing to ensure SchedulePage will
	 * return correctly when its contents are added to the main page. It can be deleted later.*/
	public static void main(String[] args) {
		launch(SchedulePage.class, args);
		
		/* EXAMPLE (delete this) */
		Database db = new Database();
		db.printScheduleByUsername("lcowart89");
	}
	
	
	public void start(Stage stage) {
		BorderPane border = new BorderPane();
		VBox navBar = getNavBar();
		BorderPane content = getPageContent();
		border.setCenter(content);
		border.setLeft(navBar);
		Scene scene = new Scene(border, 960, 700);
		stage.setScene(scene);
		stage.setTitle("SchedulePageTest");
		stage.show();
		
	}
	
	private VBox getNavBar() {
		VBox navBar = new VBox();
		navBar.setStyle("-fx-background-color: " + DARK_GREEN);
		navBar.setPrefWidth(200);
		return navBar;
	}


	public Node getNode() {
		// TODO Auto-generated method stub
		return null;
	}

	/*Dedicated code for the scheduling page is below. The function getPageContent() returns an BorderPane
	 object to be used with the BorderPane representing our main page.*/	
	
	private BorderPane getPageContent()
	{
		BorderPane content = new BorderPane();
		content.setStyle("-fx-background-color: #000");
		
		return content;
	}
}
