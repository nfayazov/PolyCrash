package logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


//Use  the function "getSchedulePane(Schedule s)" in order to return a project pane.
public class SchedulePage implements Page{
	/*This is all copied from the MainPage class, and is just for testing to ensure SchedulePage will
	 * return correctly when its contents are added to the main page. It can be deleted later.*/
	Schedule s;
	private Button addClass;
	private Button pushClass;
	
	
	static final String BSTYLE = "-fx-border-style: ";
	static final String BORCOLOR = "-fx-border-color: ";
	static final String TFILL = "-fx-text-fill: ";

	static final String BGCOLOR = "-fx-background-color: ";
	static final String WHITE = "#FFF;";
	static final String PAD = "-fx-padding: ";
	static final String BGRADIUS = "-fx-background-radius: ";
	static final String FSIZE = "-fx-font-size: ";

	static final String BRADNUM =  "15 0 0 0;";
	public SchedulePage(Schedule s) {
		this.s = s;
		addClass = getAddClassButton();
		pushClass = getPushButton();
	}
	
	public Button getAddClass() {
		return addClass;
	}
	
	public Node getNode() {
		return getSchedulePageContent();
	}
	

	/*Dedicated code for the scheduling page is below. The function getPageContent(Schedule)
	 * returns an BorderPane object to be used with the BorderPane representing our main page.
	 * It takes in the value of a user's schedule.*/	
	
	private BorderPane getSchedulePageContent()
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
		addLeftColumn(schedule, 80);
		//Populating the columns corresponding to the five days of the week with fixed width 200.
		addDayColumn(schedule, 100, 1, s, "M");
		addDayColumn(schedule, 100, 2, s, "T");
		addDayColumn(schedule, 100, 3, s, "W");
		addDayColumn(schedule, 100, 4, s, "R");
		addDayColumn(schedule, 100, 5, s, "F");
		
		schedule.setStyle(BGCOLOR+WHITE
				+ PAD + "20 0 0 0;");
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
				+ BORCOLOR+Colors.LIGHT_GREEN+";"
				+ BGCOLOR + WHITE
				+ BGRADIUS + BRADNUM);
			top.setPrefHeight(25);
			top.setPrefWidth(columnwidth);
			schedule.add(top, columnIndex, i);
		}
		
		//Top empty pane
		Pane top = new Pane();
		top.setStyle("-fx-border-style: solid solid hidden hidden;"
			+ BORCOLOR+Colors.LIGHT_GREEN+";"
			+ BGCOLOR+ WHITE
			+ PAD + "10;"
			+ BGRADIUS + BRADNUM);
		top.setPrefHeight(50);
		top.setPrefWidth(columnwidth);
		schedule.add(top, columnIndex, 1);
		
		//Adds a column for the specified day of the week at the provided column index.
		for(Course course : s.getCourses())
		{
			
			
			if(course.onDay(day))
			{					
				Label courseBlock = new Label("("+course.name+")");
				courseBlock.setStyle(BSTYLE +"solid;"
						+ BORCOLOR+Colors.DARK_GREEN+" "+Colors.DARK_GREEN+" "+Colors.LIGHT_GREEN+" "+Colors.DARK_GREEN+";"
						+ TFILL+Colors.DARK_GREEN+";"
						+ "-fx-font-size:.9em;"

						+ BGCOLOR+Colors.LIGHT_GREEN+";"
						+ PAD + "1;");

				courseBlock.setPrefWidth(columnwidth);
				courseBlock.setAlignment(Pos.BASELINE_RIGHT);
				schedule.add(courseBlock, columnIndex, getRowFromTime(course.getStart()));
				
				Label courseBlock2 = new Label("");
				courseBlock2.setStyle("-fx-border-style:hidden solid solid solid;"
						+ BORCOLOR+Colors.DARK_GREEN+";"
						+ TFILL+Colors.DARK_GREEN+";"
						+ FSIZE+".9em;"

						+ BGCOLOR+Colors.LIGHT_GREEN+";"
						+ PAD + "1;");

				courseBlock2.setPrefWidth(columnwidth);
				courseBlock2.setAlignment(Pos.BASELINE_RIGHT);
				schedule.add(courseBlock2, columnIndex, getRowFromTime(course.getEnd()));
				
				if((getRowFromTime(course.getEnd())-getRowFromTime(course.getStart())) > 1)
				{
					for(int i=getRowFromTime(course.getStart())+1; i < getRowFromTime(course.getEnd()); i++)
					{
						Pane blankpane = new Pane();
						blankpane.setStyle("-fx-border-style:hidden solid hidden solid ;"
								+ BORCOLOR+Colors.LIGHT_GREEN+" "+Colors.DARK_GREEN+" "+Colors.LIGHT_GREEN+" "+Colors.DARK_GREEN+";"
								+ TFILL+Colors.DARK_GREEN+";"
								+ FSIZE+".9em;"

								+ BGCOLOR+Colors.LIGHT_GREEN+";"
								+ PAD + "1;");

						blankpane.setPrefWidth(columnwidth);
						schedule.add(blankpane, columnIndex, i);
					}
				}
				
			}
		}
		
		//Bottom empty pane
				Pane bottom = new Pane();
				bottom.setStyle("-fx-border-style: hidden solid solid hidden;"
					+ BORCOLOR+Colors.LIGHT_GREEN+";"
					+  BGCOLOR +  WHITE
					+ PAD + "10;"
					+ BGRADIUS+ BRADNUM);
				bottom.setPrefHeight(50);
				bottom.setPrefWidth(columnwidth);
				schedule.add(bottom, columnIndex, 34);
	}
	
	//Code to return the bottom pane containing the two buttons.
	private VBox getBottomPane()
	{
		VBox bottom = new VBox();
		bottom.setStyle(BGCOLOR + "#FFFFFF");
		
		
		
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
		Button addClass1 = new Button();
		Image addClassImage = new Image(getClass().getResourceAsStream("resources/addIcon_64px.png"), 
				32, 32, false, false);
		addClass1.setGraphic(new ImageView(addClassImage));
		addClass1.setText("Add New Class");
		addClass1.setPrefWidth(300);
		addClass1.setStyle("-fx-graphic-text-gap: 10;"
				+ "-fx-border:none;"
				+BGCOLOR+Colors.DARK_GREEN+";"
				+ FSIZE+"1.6em;"
				+ TFILL + WHITE);
		
		return addClass1;
	}
	
	private Button getPushButton()
	{
		Button pushClass1 = new Button();
		Image pushClassImage = new Image(getClass().getResourceAsStream("resources/pushIcon_64px.png"), 
				32, 32, false, false);
		pushClass1.setGraphic(new ImageView(pushClassImage));
		pushClass1.setText("Push to Student Center");
		pushClass1.setPrefWidth(300);
		pushClass1.setStyle("-fx-graphic-text-gap: 10;"
				+ "-fx-border:none;"
				+ BGCOLOR+Colors.DARK_GREEN+";"
				+ FSIZE+"1.6em;"
				+ TFILL+ WHITE
				+ "-fx-content-display:right");
		
		return pushClass1;
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
					+ BORCOLOR+Colors.LIGHT_GREEN+";"
					+ TFILL + "#FFF; "
					+ FSIZE+"1.1em;"

					+ BGCOLOR+Colors.DARK_GREEN+";"
					+ PAD + "1;");

			time.setPrefWidth(columnwidth);
			time.setAlignment(Pos.BASELINE_RIGHT);
			schedule.add(time, 0, getRowFromTime(timestamp));
			
			/*Creates an empty cell for use with half hour marks.*/
			Pane emptyCell = new Pane();
			emptyCell.setStyle("-fx-border-style:hidden solid hidden solid;"
					+ BORCOLOR+Colors.LIGHT_GREEN+";"
					+ TFILL+ "#FFF; "
					+ FSIZE+ "1.1em;"
					+ BGCOLOR+Colors.DARK_GREEN+";"
					+ PAD + "1;");
			schedule.add(emptyCell, 0, getRowFromTime(timestamp)+1);
			
			hour++;
		}
	}
	
	private void addLeftColumn(GridPane schedule, int columnwidth)
	{
		//Top empty pane
		Pane lefttop = new Pane();
		lefttop.setStyle(BORCOLOR+Colors.LIGHT_GREEN+";"
				+ BGCOLOR+Colors.LIGHT_GREEN+";"
				+ PAD + "10;"
				+ BGRADIUS + BRADNUM);
		lefttop.setPrefHeight(25);
		lefttop.setPrefWidth(columnwidth);
		schedule.add(lefttop, 0, 1);
				
		//Add labels for times
		addTimeLabels(schedule, columnwidth);
		
		//Bottom empty pane
		Pane leftbottom = new Pane();
		leftbottom.setStyle(BORCOLOR+Colors.LIGHT_GREEN+";"
				+ BGCOLOR+Colors.LIGHT_GREEN+";"
				+ PAD + "10;"
				+ BGRADIUS +BRADNUM);
		leftbottom.setPrefHeight(25);
		leftbottom.setPrefWidth(columnwidth);
		schedule.add(leftbottom, 0, 34);
	}
	
}
