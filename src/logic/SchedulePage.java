package logic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
import javafx.scene.layout.Pane;
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
		//content.setStyle("-fx-background-color: #000");
		
		GridPane schedule = getSchedulePane();
		//BorderPane.setMargin(schedule, new Insets(10, 10, 50, 10));
		
		VBox bottom = getBottomPane();
		bottom.setPrefHeight(250);
		
		content.setCenter(schedule);
		content.setBottom(bottom);
		
		return content;
	}
	
	private Button getAddClassButton()
	{
		Button addClass = new Button();
		Image addClassImage = new Image(getClass().getResourceAsStream("resources/addIcon_64px.png"), 
				32, 32, false, false);
		addClass.setGraphic(new ImageView(addClassImage));
		addClass.setText("Add New Class");
		addClass.setPrefWidth(300);
		addClass.setStyle("-fx-graphic-text-gap: 10;"
				+ "-fx-border:none;"
				+ "-fx-background-color:"+DARK_GREEN+";"
				+ "-fx-font-size:1.6em;"
				+ "-fx-text-fill:#FFF");
		
		return addClass;
	}
	
	private Button getPushButton()
	{
		Button pushClass = new Button();
		Image pushClassImage = new Image(getClass().getResourceAsStream("resources/pushIcon_64px.png"), 
				32, 32, false, false);
		pushClass.setGraphic(new ImageView(pushClassImage));
		pushClass.setText("Push to Student Center");
		pushClass.setPrefWidth(300);
		pushClass.setStyle("-fx-graphic-text-gap: 10;"
				+ "-fx-border:none;"
				+ "-fx-background-color:"+DARK_GREEN+";"
				+ "-fx-font-size:1.6em;"
				+ "-fx-text-fill:#FFF;"
				+ "-fx-content-display:right");
		
		return pushClass;
	}
	
	private VBox getBottomPane()
	{
		VBox bottom = new VBox();
		bottom.setStyle("-fx-background-color:#FFFFFF");
		
		/*Button for adding classes.*/
		Button addClass = getAddClassButton();
		Button pushClass = getPushButton();
		
		bottom.getChildren().add(addClass);
		bottom.getChildren().add(pushClass);
		bottom.setSpacing(10);
		bottom.setAlignment(Pos.TOP_CENTER);
		bottom.setPadding(new Insets(20, 0, 0, 0));
		
		return bottom;
	}
	
	private void addTimeLabels(GridPane schedule, int columnwidth)
	{
		//For each time label, add a row on the schedule appropriately.
		//Starts the loop at 7AM.
		String AMPM = "AM";
		int hour = 7;
		
		//Has room for hours 7AM - 10PM.
		for(int i = 1; i <= 15; i++)
		{
			if(hour == 12)
			{
				//If the hour is noon, we change the AM/PM label to PM.
				AMPM = "PM";
				hour = 1;
			}
			Label time = new Label(Integer.toString(hour)+" "+AMPM);
			time.setStyle("-fx-border-color: "+LIGHT_GREEN+";"
					+ "-fx-text-fill:#FFF; "
					+ "-fx-font-size:1.1em;"
					+ "-fx-background-color: "+DARK_GREEN+";"
					+ "-fx-padding:7;");
			time.setPrefWidth(columnwidth);
			time.setAlignment(Pos.BASELINE_RIGHT);
			schedule.add(time, 0, i+1);
			hour++;
		}
	}
	
	private void addLeftColumn(GridPane schedule, int columnwidth)
	{
		//Top empty pane
		Pane left_top = new Pane();
		left_top.setStyle("fx-border-color:"+LIGHT_GREEN+";"
				+ "-fx-background-color:"+LIGHT_GREEN+";"
				+ "-fx-padding:10;"
				+ "-fx-background-radius:15 0 0 0;");
		left_top.setPrefHeight(50);
		left_top.setPrefWidth(columnwidth);
		schedule.add(left_top, 0, 1);
				
		//Add labels for times
		addTimeLabels(schedule, columnwidth);
		
		//Bottom empty pane
		Pane left_bottom = new Pane();
		left_bottom.setStyle("fx-border-color:"+LIGHT_GREEN+";"
				+ "-fx-background-color:"+LIGHT_GREEN+";"
				+ "-fx-padding:10;"
				+ "-fx-background-radius:0 0 0 15;");
		left_bottom.setPrefHeight(50);
		left_bottom.setPrefWidth(columnwidth);
		schedule.add(left_bottom, 0, 17);
	}
	
	private GridPane getSchedulePane()
	{
		GridPane schedule = new GridPane();
		
		//initializing the schedule pane's left column with fixed width 150.
		addLeftColumn(schedule, 100);
		
		schedule.setStyle("-fx-background-color:#FFF;"
				+ "-fx-padding:20 0 0 0;");
		schedule.setAlignment(Pos.TOP_CENTER);
		
		return schedule;
	}
}
