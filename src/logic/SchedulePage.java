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

import static org.junit.Assert.assertEquals;

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
		
		Schedule s = testSchedule();
		BorderPane content = getSchedulePageContent(s);
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
	
	private Schedule testSchedule()
	{
		Schedule s = new Schedule();
		Course course1 = new Course("CSC 307", 1);
		course1.setDays("MWF");
		course1.setStart(new Time(8, 30));
		course1.setEnd(new Time(10, 0));

		Course course2 = new Course("CPE 357", 2);
		course2.setDays("TR");
		course2.setStart(new Time(19, 0));
		course2.setEnd(new Time(20, 0));
		
		Course course3 = new Course("CSC 349", 1);
		course3.setDays("TR");
		course3.setStart(new Time(12, 0));
		course3.setEnd(new Time(14, 30));


		s.addCourse(course1);
		s.addCourse(course2);
		s.addCourse(course3);
		assertEquals(s.getNumCourses(), 3);
		return s;
	}

	/*Dedicated code for the scheduling page is below. The function getPageContent(Schedule)
	 * returns an BorderPane object to be used with the BorderPane representing our main page.
	 * It takes in the value of a user's schedule.*/	
	
	private BorderPane getSchedulePageContent(Schedule s)
	{
		BorderPane content = new BorderPane();
		
		GridPane schedule = getSchedulePane(s);
		
		VBox bottom = getBottomPane();
		bottom.setPrefHeight(250);
		
		content.setCenter(schedule);
		content.setBottom(bottom);
		
		return content;
	}
	
	//Code to return the center pane containing the schedule.
	private GridPane getSchedulePane(Schedule s)
	{
		GridPane schedule = new GridPane();
		
		//initializing the schedule pane's left column with fixed width 150.
		addLeftColumn(schedule, 100);
		//Populating the columns corresponding to the five days of the week with fixed width 200.
		addDayColumn(schedule, 140, 1, s, "M");
		addDayColumn(schedule, 140, 2, s, "T");
		addDayColumn(schedule, 140, 3, s, "W");
		addDayColumn(schedule, 140, 4, s, "R");
		addDayColumn(schedule, 140, 5, s, "F");
		
		schedule.setStyle("-fx-background-color:#FFF;"
				+ "-fx-padding:20 0 0 0;");
		schedule.setAlignment(Pos.TOP_CENTER);
		
		return schedule;
	}
	
	//Adds all 
	private void addDayColumn(GridPane schedule, int columnwidth, int columnIndex, Schedule s, String day)
	{
		for(int i=1; i < 34; i++)
		{
			Pane top = new Pane();
			top.setStyle("-fx-border-style: hidden solid hidden hidden;"
				+ "-fx-border-color:"+LIGHT_GREEN+";"
				+ "-fx-background-color:#FFF;"
				+ "-fx-background-radius:15 0 0 0;");
			top.setPrefHeight(50);
			top.setPrefWidth(columnwidth);
			schedule.add(top, columnIndex, i);
		}
		
		//Top empty pane
		Pane top = new Pane();
		top.setStyle("-fx-border-style: solid solid hidden hidden;"
			+ "-fx-border-color:"+LIGHT_GREEN+";"
			+ "-fx-background-color:#FFF;"
			+ "-fx-padding:10;"
			+ "-fx-background-radius:15 0 0 0;");
		top.setPrefHeight(50);
		top.setPrefWidth(columnwidth);
		schedule.add(top, columnIndex, 1);
		
		//Adds a column for the specified day of the week at the provided column index.
		for(Course course : s.getCourses())
		{
			if(course.onDay(day))
			{					
				Label courseBlock = new Label("("+course.name+")");
				courseBlock.setStyle("-fx-border-style:solid;"
						+ "-fx-border-color: "+DARK_GREEN+" "+DARK_GREEN+" "+LIGHT_GREEN+" "+DARK_GREEN+";"
						+ "-fx-text-fill:"+DARK_GREEN+";"
						+ "-fx-font-size:.9em;"
						+ "-fx-background-color: "+LIGHT_GREEN+";"
						+ "-fx-padding:4;");
				courseBlock.setPrefWidth(columnwidth);
				courseBlock.setAlignment(Pos.BASELINE_RIGHT);
				schedule.add(courseBlock, columnIndex, getRowFromTime(course.getStart()));
				
				Label courseBlock2 = new Label("");
				courseBlock2.setStyle("-fx-border-style:hidden solid solid solid;"
						+ "-fx-border-color: "+DARK_GREEN+";"
						+ "-fx-text-fill:"+DARK_GREEN+";"
						+ "-fx-font-size:.9em;"
						+ "-fx-background-color: "+LIGHT_GREEN+";"
						+ "-fx-padding:4;");
				courseBlock2.setPrefWidth(columnwidth);
				courseBlock2.setAlignment(Pos.BASELINE_RIGHT);
				schedule.add(courseBlock2, columnIndex, getRowFromTime(course.getEnd()));
				
				if((getRowFromTime(course.getEnd())-getRowFromTime(course.getStart())) > 1)
				{
					for(int i=getRowFromTime(course.getStart())+1; i < getRowFromTime(course.getEnd()); i++)
					{
						Pane blankpane = new Pane();
						blankpane.setStyle("-fx-border-style:hidden solid hidden solid ;"
								+ "-fx-border-color: "+LIGHT_GREEN+" "+DARK_GREEN+" "+LIGHT_GREEN+" "+DARK_GREEN+";"
								+ "-fx-text-fill:"+DARK_GREEN+";"
								+ "-fx-font-size:.9em;"
								+ "-fx-background-color: "+LIGHT_GREEN+";"
								+ "-fx-padding:4;");
						blankpane.setPrefWidth(columnwidth);
						schedule.add(blankpane, columnIndex, i);
					}
				}
				
			}
		}
		
		//Bottom empty pane
				Pane bottom = new Pane();
				bottom.setStyle("-fx-border-style: hidden solid solid hidden;"
					+ "-fx-border-color:"+LIGHT_GREEN+";"
					+ "-fx-background-color:#FFF;"
					+ "-fx-padding:10;"
					+ "-fx-background-radius:15 0 0 0;");
				bottom.setPrefHeight(50);
				bottom.setPrefWidth(columnwidth);
				schedule.add(bottom, columnIndex, 34);
	}
	
	//Code to return the bottom pane containing the two buttons.
	private VBox getBottomPane()
	{
		VBox bottom = new VBox();
		bottom.setStyle("-fx-background-color:#FFFFFF");
		
		Button addClass = getAddClassButton();
		Button pushClass = getPushButton();
		
		bottom.getChildren().add(addClass);
		bottom.getChildren().add(pushClass);
		bottom.setSpacing(10);
		bottom.setAlignment(Pos.TOP_CENTER);
		bottom.setPadding(new Insets(20, 0, 0, 0));
		
		return bottom;
	}
	
	//Bottom pane functions below
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
	
	private int getRowFromTime(Time time)
	{
		//Takes in a timestamp and returns the correct row.
		int row = ((time.hour-6)*2);
		
		if(time.minute >= 30)
		{
			row += 1;
		}
		return row;
	}
	
	//Schedule pane functions below
	private void addTimeLabels(GridPane schedule, int columnwidth)
	{
		//For each time label, add a row on the schedule appropriately.
		//Starts the loop at 7AM.		
		//Has room for hours 7AM - 10PM.
		int hour = 7;
		for(int i = 1; i < 17; i++)
		{
			Time timestamp = new Time(hour, 0);
			
			Label time = new Label(Integer.toString(timestamp.getPeriodHour())+" "+timestamp.period);
			time.setStyle("-fx-border-style:solid solid hidden solid;"
					+ "-fx-border-color: "+LIGHT_GREEN+";"
					+ "-fx-text-fill:#FFF; "
					+ "-fx-font-size:1.1em;"
					+ "-fx-background-color: "+DARK_GREEN+";"
					+ "-fx-padding:4;");
			time.setPrefWidth(columnwidth);
			time.setAlignment(Pos.BASELINE_RIGHT);
			schedule.add(time, 0, getRowFromTime(timestamp));
			
			/*Creates an empty cell for use with half hour marks.*/
			Pane emptyCell = new Pane();
			emptyCell.setStyle("-fx-border-style:hidden solid hidden solid;"
					+ "-fx-border-color: "+LIGHT_GREEN+";"
					+ "-fx-text-fill:#FFF; "
					+ "-fx-font-size:1.1em;"
					+ "-fx-background-color: "+DARK_GREEN+";"
					+ "-fx-padding:1;");
			schedule.add(emptyCell, 0, getRowFromTime(timestamp)+1);
			
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
		schedule.add(left_bottom, 0, 34);
	}
	
}
