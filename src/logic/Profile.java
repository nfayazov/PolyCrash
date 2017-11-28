package logic;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Profile extends Application implements Page {

	DatePicker timepicker;
	
	public Profile() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	  public void start(Stage stage) throws Exception {
	      Node root = getNode();
	      Scene scene = new Scene((Parent) root, 960, 700);
	      stage.setTitle("Login Page");
	      stage.setScene(scene);
	      stage.show();
	}
	
	public Node getNode() {
		/*Have a VBox with all children aligned in center
		ImageView: Profile Pic
		Label: username
		TextField: Name (set initial value to “Firstname Lastname”)
		TextField: email (set initial value to “email@calpoly.edu”)
		ComboBox: Major (set text to “Major”)
		ComboBox: Year (set text to “Major”)
		DatePicker: Registration Time
		 */
		VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.setAlignment(Pos.CENTER);
	    
	    // load the image
        Image image = new Image(getClass().getResourceAsStream("resources/profileIcon_64px.png"));

        // display pic
        ImageView pic = new ImageView();
        pic.setFitWidth(100);
        pic.setPreserveRatio(true);
        pic.setSmooth(true);
        pic.setCache(true);
        pic.setImage(image);
        vbox.getChildren().addAll(pic);
        
        Label username = new Label("username here");
        vbox.getChildren().addAll(username);
        
        final TextField n = new TextField();
	    n.setPromptText("Firstname Lastname");
	    vbox.getChildren().add(n);
	    
        final TextField email = new TextField();
	    email.setPromptText("email@calpoly.edu");
	    vbox.getChildren().add(email);
        
	    
	    final ComboBox major = new ComboBox<String>();
        major.getItems().addAll(
            "Computer Science",
            "Software Engineering",
            "Computer Engineering",
            "Electrical Engineering",
            "General Engineering"  
        );
        vbox.getChildren().add(major);
        
        //TODO:  year
        
        timepicker = new DatePicker();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Check-In Date:");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(timepicker, 0, 1);
        vbox.getChildren().add(gridPane);
        //time);
		return vbox;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
