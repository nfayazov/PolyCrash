package logic;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Profile extends Application implements Page {

	DatePicker timepicker;
	
	Student student;
	
	public Profile(String username) {
		Database db = Database.getInstance();
		Map<String, Student> studentList = (HashMap<String, Student>) db.getStudentTable();
		student = studentList.get(username);
		System.out.println(student);
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
		final VBox vbox = new VBox();
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
        
        Label username = new Label(student.username);
        username.setFont(new Font("Cambria", 20));
        username.setTextFill(Color.web(DARK_GREEN));
        vbox.getChildren().addAll(username);
        
        Label name = new Label(student.first + " " + student.last);
        name.setFont(new Font("Cambria", 20));
        name.setStyle("-fx-font-weight: bold");
        name.setTextFill(Color.web(DARK_GREEN));
	    vbox.getChildren().add(name);
	    
	    Label email = new Label(student.username + "@calpoly.edu");
	    email.setFont(new Font("Cambria", 15));
	    email.setTextFill(Color.web(DARK_GREEN));
	    vbox.getChildren().add(email);
        
	    
	    final ComboBox<String> major = new ComboBox<String>();
	    
        major.getItems().addAll(
        	"Choose your major",
            "Computer Science",
            "Software Engineering",
            "Computer Engineering",
            "Electrical Engineering",
            "General Engineering"  
        );
        major.getSelectionModel().select("Choose your major");

        vbox.getChildren().add(major);
        
	    final ComboBox<String> year = new ComboBox<String>();
        year.getItems().addAll(
        	"Choose your year",
            "Freshman",
            "Sophomore",
            "Junior",
            "Senior",
            "Graduate"  
        );
        year.getSelectionModel().select("Choose your year");

        vbox.getChildren().add(year);
        
        timepicker = new DatePicker();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Registration Time");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.CENTER);
        gridPane.add(timepicker, 0, 0);
        vbox.getChildren().add(timepicker);
        
        //change password hypek
        Hyperlink link = new Hyperlink();
        link.setText("Change Password");
        link.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
        		System.out.println("Change Password pressed");
        		final Label errorPW = new Label("");
        		
        		final Stage popupStage =new Stage();
        	    VBox popGrid = new VBox();
        	    popGrid.setPadding(new Insets(10));
      	      	popGrid.setSpacing(8);
        	    popGrid.setAlignment(Pos.CENTER);
        	
        		popGrid.setPadding(new Insets(5, 5, 5, 5));
        		popupStage.initModality(Modality.APPLICATION_MODAL);
        		popupStage.setTitle("Change Password");
        		
        		Label label1= new Label("Change Password");
        		popGrid.getChildren().add(label1);
        		
        		final PasswordField verifyOldPW = new PasswordField();
        		verifyOldPW.setPromptText("Current Password");
      	      	popGrid.getChildren().add(verifyOldPW);
      	      	
      	      	final PasswordField  newPW = new PasswordField();
        		newPW.setPromptText("New Password");
      	      	popGrid.getChildren().add(newPW);
      	      	
      	      	final PasswordField  verifynewPW = new PasswordField();
      	      	verifynewPW.setPromptText("Retype Password");
    	      	popGrid.getChildren().add(verifynewPW);
      	      
    	      	Button enter = new Button("Enter");
    		   
    		      popGrid.getChildren().add(enter);
    		      errorPW.setTextFill(Color.web(DARK_GREEN));
    		      vbox.getChildren().add(errorPW);
    		      
    			  enter.setOnAction(new EventHandler<ActionEvent>() {
    				    public void handle(ActionEvent e) {
    				    	//verifying user and pass
    				    	errorPW.setText("");
    				    	
    				    	System.out.print(student.getPassword());
    		    	      	if (verifyOldPW.getText().isEmpty() || newPW.getText().isEmpty() 
    		    	      			|| verifynewPW.getText().isEmpty()) {
    		    	      		errorPW.setText("Please fill all fields");
    		    	      	}
    		    	      	else if (verifyOldPW.getText().compareTo(student.getPassword()) != 0) {
    		    	      		errorPW.setText("Incorrect old password");
    		      	      	}
    		      	      	
    		    	      	else if (newPW.getText().compareTo(verifynewPW.getText()) != 0) {
    		    	      		errorPW.setText("New passwords do not match");
    		      	      	}
    		    	      	
    		    	      	else {
    		    	      		System.out.print(student.getPassword());
    		    	      		//student.changePassword(newPW.getText());
    		    	      		errorPW.setText("Password changed.");
    		    	      	}
    				     }
    				 });
    			  
        
        		
    	      	popGrid.getChildren().add(errorPW);
    	      	
        		Button button1= new Button("Close");
        		//button1.setOnAction(click -> popupStage.close());  
        		button1.setOnAction(new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent e)
        			{
        				popupStage.close();
        			}
        		});
        		Scene scene1= new Scene(popGrid, 400, 400);
        		popupStage.setScene(scene1);
        		popupStage.showAndWait();
        		
                
            }
        });
        
        vbox.getChildren().add(link);
        
		return vbox;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
