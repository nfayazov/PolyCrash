package logic;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Colors;




public class MainPage extends Application implements Page{
	private BorderPane border;
	private Profile profile;
	private SearchPage searchPage;
	private SchedulePage schedulePage;
	private Database db;
	private static final String CLEAR = "transparent";
	private static final String SET_BG = "-fx-background-color: ";
	
	public static void main(String[] args) {
		launch(MainPage.class, args);
	}
	
	
	public void start(Stage stage) {
		int i = 3;
		db = Database.getInstance();
		border = new BorderPane();
		Login login = new Login();
		border.setCenter(login.getNode());
		
		login.getEnter().setOnAction(event -> {
	    	login.loginCheck();
	    	if(login.getLoggedInUsername().length() > 0) {
	    		startMainpage(login.getLoggedInUsername());
	    	}
		 });
		

		Scene scene = new Scene(border, 960, 700);
		stage.setScene(scene);
		stage.setTitle("Main Page");
		stage.show();
	}
	
	public void startMainpage(String username) {
		profile = new Profile(username);
		searchPage = new SearchPage(username);
		schedulePage = new SchedulePage( db.getScheduleByUsername(username) );
		schedulePage.getAddClass().setOnAction(event -> 
			    	border.setCenter(searchPage.getNode())
		);
		VBox navBar = getNavBar();
		border.setLeft(navBar);
		border.setCenter(profile.getNode());
	}
	
	public String getDarkGreen() {
		return "#004d00";
	}
	
	private void setDefaultButtonColorsToClear(String [] defaultButtonColors) {
		for(int i = 0; i < defaultButtonColors.length; i++) {
			defaultButtonColors[i] = CLEAR;
		}
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

		final Button [] sideButtons = {
				profileButton,
				homeButton,
				browseCoursesButton,
				exitButton
			};
		
		final String [] defaultButtonColors = new String[sideButtons.length];
		setDefaultButtonColorsToClear(defaultButtonColors);
		
		
		profileButton.setOnAction(event -> {
			border.setCenter(profile.getNode());
			defaultButtonColors[0] = Colors.LIGHT_GREEN;
			for(int i = 0; i < defaultButtonColors.length; i++) {
				if(i == 0) {
					continue;
				}
				defaultButtonColors[i] = CLEAR;
				sideButtons[i].setStyle(SET_BG + CLEAR);
			}
		});
		
		homeButton.setOnAction(event -> {
			border.setCenter(schedulePage.getNode());
			defaultButtonColors[1] = Colors.LIGHT_GREEN;
			for(int i = 0; i < defaultButtonColors.length; i++) {
				if(i == 1) {
					continue;
				}
				defaultButtonColors[i] = CLEAR;
				sideButtons[i].setStyle(SET_BG + CLEAR);
			}
		});
		
		browseCoursesButton.setOnAction(event -> {
			border.setCenter(searchPage.getNode());
			defaultButtonColors[2] = Colors.LIGHT_GREEN;
			for(int i = 0; i < defaultButtonColors.length; i++) {
				if(i == 2) {
					continue;
				}
				defaultButtonColors[i] = CLEAR;
				sideButtons[i].setStyle(SET_BG + CLEAR);
			}
		});
		
		exitButton.setOnAction(event -> 
			System.exit(0)
		);
		
		setupHighlighting(sideButtons, defaultButtonColors);
		
		
		return sideButtons;
		
	}
	
	private void setupHighlighting(Button [] sideButtons, String[] defaultButtonColors) {
		for(int i = 0; i < sideButtons.length; i++) {
			sideButtons[i].setStyle(SET_BG + defaultButtonColors[i]);
			sideButtons[i].setPrefSize(500, 500);
			final int tmp = i;
			sideButtons[i].setOnMouseEntered(event -> 
				sideButtons[tmp].setStyle(SET_BG + Colors.LIGHT_GREEN)
			);
			sideButtons[i].setOnMouseExited(event ->
					sideButtons[tmp].setStyle(SET_BG + defaultButtonColors[tmp])
			);
			
		}
		
	}
	
	
	
	private VBox getNavBar() {
		VBox navBar = new VBox();
		Button [] sideButtons = getSideButtons();
		for(int i = 0; i < sideButtons.length; i++) {
			navBar.getChildren().add(sideButtons[i]);
		}
		navBar.setStyle(SET_BG + Colors.DARK_GREEN);
		navBar.setPrefWidth(200);
		return navBar;
	}


	public Node getNode() {
		return null;
	}
}
