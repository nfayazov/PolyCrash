package logic;

import javafx.scene.Group;
import javafx.scene.Node;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Login extends Application implements Page{

	Label error1 = new Label("");
	
	public Login() {
		//constructor
	}

	public boolean checkVaild( String username, String pw) {
		//TO-DO: check database if user and pw matches
		return true;
	}
	
	
	@Override
	  public void start(Stage stage) throws Exception {
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
	 
	      // BOTTOM ----------------------------
	      /*GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25,25,25,25));
			*/
	      Label title1 = new Label("Sign in with your calpoly.edu information");
	      title1.setFont(new Font("Cambria", 20));
	      title1.setTextFill(Color.web(DARK_GREEN));
	      title1.setPadding(new Insets(10, 10, 10, 10));
	      
	      VBox vbox = new VBox();
	      vbox.setPadding(new Insets(10));
	      vbox.setSpacing(8);
	      vbox.setAlignment(Pos.BOTTOM_CENTER);
	      vbox.getChildren().addAll(title1);
	      vbox.getChildren().addAll(error1);
	      
	      final TextField username = new TextField();
		  username.setPromptText("Username");
	      vbox.getChildren().add(username);
	      
	      final PasswordField pw = new PasswordField();
		  pw.setPromptText("Password");
	      vbox.getChildren().add(pw);

	      Button enter = new Button("Sign In");
	      root.setCenter(enter);
	      vbox.getChildren().add(enter);

		  enter.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			    	//verifying user and pass
			        if ((!username.getText().isEmpty() | !pw.getText().isEmpty())) {
			        	//if not in database/error
			        	if (checkVaild(username.getText(), pw.getText()) == false) 
			        		error1.setText("Invalid username or password");
			        	else {
			        		//get student info, go to Main Page
			        		//TO-DO
			        	}
			            
			        } else {
			            error1.setText("Please fill both fields");
			        }
			     }
			 });
		  
	      root.setTop(top);
	      root.setBottom(vbox);
	      
	      //set scene
	      Scene scene = new Scene(root, 960, 700);
	      stage.setTitle("Login Page");
	      stage.setScene(scene);
	      stage.show();
	}
	

	public Node getNode() {
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 launch(args);
	}

}
