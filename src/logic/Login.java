package logic;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Application implements Page{
	
	Label error1 = new Label("");
	Label errorLogin = new Label("");
	public Button enter;
	public String loggedInUsername;
    final TextField username;
    final PasswordField pw;

	public Login() {
		enter = new Button("Sign In");
		loggedInUsername = "";
		username = new TextField();
		pw = new PasswordField();
	}

	public boolean checkValid(String username, String pw) {
		Database db = Database.getInstance();
		Map<String, Student> studentList = db.getStudentTable();
		//string is username, student->pw
		if(studentList.containsKey(username)) {
			Student s = studentList.get(username);
			
			if (s.getPassword().compareTo(pw) == 0) {
				return true;
			}
			errorLogin.setText("Incorrect password");
			try        
			{
			    Thread.sleep(1000);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
			return false;
		}
		else {
			errorLogin.setText("Username does not exist");
			return false;
		}
		
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
		  BorderPane root = new BorderPane();
		 
	      // TOP ------------------------------------
	      Label title = new Label("PolyCrash");
	      title.setFont(new Font("Cambria", 50));
	      title.setTextFill(Color.web("#FFF"));
	      title.setPadding(new Insets(10, 10, 10, 10));
	      VBox top = new VBox();
	      top.setAlignment(Pos.BOTTOM_CENTER);
	      top.getChildren().addAll(title);
	      top.setPrefHeight(350);
		  top.setStyle("-fx-background-color: " + DARK_GREEN);
	 
	      Label title1 = new Label("Sign in with your calpoly.edu information");
	      title1.setFont(new Font("Cambria", 20));
	      title1.setTextFill(Color.web(DARK_GREEN));
	      title1.setPadding(new Insets(10, 10, 10, 10));
	      
	      VBox vbox = new VBox();
	      vbox.setPadding(new Insets(10));
	      vbox.setSpacing(8);
	      vbox.setAlignment(Pos.BOTTOM_CENTER);
	      vbox.getChildren().add(title1);
	      
	      error1.setTextFill(Color.web(DARK_GREEN));
	      vbox.getChildren().add(error1);

		  username.setPromptText("Username");
	      vbox.getChildren().add(username);
	      
		  pw.setPromptText("Password");
	      vbox.getChildren().add(pw);

	      root.setCenter(enter);
	      vbox.getChildren().add(enter);

	      errorLogin.setTextFill(Color.web(DARK_GREEN));
	      vbox.getChildren().add(errorLogin);
	      
		  
	      root.setTop(top);
	      root.setBottom(vbox);
		  return root;
	}
	
	public void loginCheck() {
		//verifying user and pass
    	errorLogin.setText("");
    	error1.setText("");
        if ((!username.getText().isEmpty() || !pw.getText().isEmpty())) {
        	//if not in database/error
        	if (checkValid(username.getText(), pw.getText())) {
        		loggedInUsername = username.getText();
        	}
            
        } else {
            error1.setText("Please fill both fields");
        }
	}

}
