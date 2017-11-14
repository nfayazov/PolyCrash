package logic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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



public class MainPage extends Application{
	final static String DARK_GREEN = "#004d00";
	final static String LIGHT_GREEN = "#80ff80";
	public static void main(String[] args) {
		launch(MainPage.class, args);
	}
	
	
	public void start(Stage stage) {
		BorderPane border = new BorderPane();
		VBox navBar = getNavBar();
		border.setLeft(navBar);
		Scene scene = new Scene(border, 960, 700);
		stage.setScene(scene);
		stage.setTitle("Main Page");
		stage.show();
		
	}
	
	public String getDarkGreen() {
		return "#004d00";
	}
	
	private Button[] getSideButtons() {
		final Button profileButton = new Button();
		profileButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/profileIcon_64px.png"))));
		
		Button homeButton = new Button();
		homeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/calendarIcon_64px.png"))));

		Button browseCoursesButton = new Button();
		browseCoursesButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/searchIcon_64px.png"))));

		Button exitButton = new Button();
		exitButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/exitIcon_64px.png"))));

		final Button sideButtons[] = {
				profileButton,
				homeButton,
				browseCoursesButton,
				exitButton
			};
		
		final String defaultButtonColors[] = new String[sideButtons.length];
		for(int i = 0; i < defaultButtonColors.length; i++) {
			defaultButtonColors[i] = "transparent";
		}
		
		profileButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				System.out.println("Clicked on profile");
				defaultButtonColors[0] = LIGHT_GREEN;
				for(int i = 0; i < defaultButtonColors.length; i++) {
					if(i == 0) {
						continue;
					}
					defaultButtonColors[i] = "transparent";
					sideButtons[i].setStyle("-fx-background-color: transparent");
				}
			}
		});
		
		homeButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				System.out.println("Clicked on profile");;
				defaultButtonColors[1] = LIGHT_GREEN;
				for(int i = 0; i < defaultButtonColors.length; i++) {
					if(i == 1) {
						continue;
					}
					defaultButtonColors[i] = "transparent";
					sideButtons[i].setStyle("-fx-background-color: transparent");
				}
			}
		});
		
		browseCoursesButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				System.out.println("Clicked on browse courses");;
				defaultButtonColors[2] = LIGHT_GREEN;
				for(int i = 0; i < defaultButtonColors.length; i++) {
					if(i == 2) {
						continue;
					}
					defaultButtonColors[i] = "transparent";
					sideButtons[i].setStyle("-fx-background-color: transparent");
				}
			}
		});
		
		exitButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				System.out.println("Clicked on exit");
				System.exit(0);
			}
		});
		
		
		
		for(int i = 0; i < sideButtons.length; i++) {
			sideButtons[i].setStyle("-fx-background-color: " + defaultButtonColors[i]);
			sideButtons[i].setPrefSize(500, 500);
			final int tmp = i;
			sideButtons[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent t) {
					sideButtons[tmp].setStyle("-fx-background-color: " + LIGHT_GREEN);
				}
			});
			sideButtons[i].setOnMouseExited(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent t) {
					sideButtons[tmp].setStyle("-fx-background-color: " + defaultButtonColors[tmp]);
				}
			});
			
		}
		

		
		
		return sideButtons;
		
	}
	
	
	
	
	private VBox getNavBar() {
		VBox navBar = new VBox();
		Button sideButtons[] = getSideButtons();
		for(int i = 0; i < sideButtons.length; i++) {
			navBar.getChildren().add(sideButtons[i]);
		}
		navBar.setStyle("-fx-background-color: " + DARK_GREEN);
		navBar.setPrefWidth(200);
		return navBar;
	}
}
