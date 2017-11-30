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



public class MainPage extends Application implements Page{
	public BorderPane border;
	public Profile profile;
	public SearchPage searchPage;
	public SchedulePage schedulePage;
	public Login login;
	public static Database db;
	
	public static void main(String[] args) {
		db = Database.getInstance();
		launch(MainPage.class, args);
	}
	
	
	public void start(Stage stage) {
		border = new BorderPane();
		String username = "lcowart89";
		login = new Login();
		border.setCenter(login.getNode());
		
		login.enter.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	login.loginCheck();
		    	if(login.loggedInUsername.length() > 0) {
		    		startMainpage(login.loggedInUsername);
		    		System.out.print("Starting main page");
		    	}
		     }
		 });
		
//		login.enter.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//		    public void handle(ActionEvent e) {
//		    	if(login.loggedInUsername.length() > 0) {
//		    		startMainpage(login.loggedInUsername);
//		    		System.out.print("Starting main page");
//		    	}
//		    }
//		});
		Scene scene = new Scene(border, 960, 700);
		stage.setScene(scene);
		stage.setTitle("Main Page");
		stage.show();
	}
	
	public void startMainpage(String username) {
		profile = new Profile(username);
		searchPage = new SearchPage();
		schedulePage = new SchedulePage( db.getScheduleByUsername(username) );
		VBox navBar = getNavBar();
		border.setLeft(navBar);
		border.setCenter(profile.getNode());
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
				border.setCenter(profile.getNode());
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
				System.out.println("Clicked on home button");;
				border.setCenter(schedulePage.getNode());
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
				System.out.println("Clicked on browse courses");
				border.setCenter(searchPage.getNode());
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


	public Node getNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
