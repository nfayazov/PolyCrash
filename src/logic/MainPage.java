package logic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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



public class MainPage extends Application{
	public static void main(String[] args) {
		launch(MainPage.class, args);
	}
	
	
	public void start(Stage stage) {
		BorderPane border = new BorderPane();
		VBox navBar = getNavBar();
		border.setLeft(navBar);
		Scene scene = new Scene(border);
		stage.setScene(scene);
		stage.setTitle("Main Page");
		stage.show();
		
	}
	
	public String getDarkGreen() {
		return "#004d00";
	}
	
	private Hyperlink[] getSideLinks() {
		Hyperlink sideLinks[] = {
				new Hyperlink("Home"),
				new Hyperlink("Schedule"),
				new Hyperlink("Browse Classes")
			};
		return sideLinks;
	}
	
	private VBox getNavBar() {
		VBox navBar = new VBox();
		navBar.setPadding(new Insets(10));
		Hyperlink sideLinks[] = getSideLinks();
		
		for(int i = 0; i < sideLinks.length; i++) {
			//navBar.setMargin(sideLinks[i], new Insets(0, 0, 0, 8));
			navBar.getChildren().add(sideLinks[i]);
		}
		return navBar;
	}
}
