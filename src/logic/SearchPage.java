package logic;

import java.util.Map;
import java.util.Random;

import org.controlsfx.control.textfield.TextFields;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

public class SearchPage extends Application implements Page
{
	Label label1;
	Button button1;
	int i = 1;
	private String username;
	final Database db = Database.getInstance();
	Map<String, Student> studentList = db.getStudentTable();
	Student student = studentList.get(username);
	
	public SearchPage(String username) {
		this.username = username;
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Search Page");
		Scene scene = new Scene((Parent) getNode(), 960, 700);
		stage.setScene(scene);
		stage.show();
	}

	
	public Node getNode() {
		final GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Search Classes");
		final String fontType = "Cambria";
		scenetitle.setFont(Font.font(fontType, FontWeight.NORMAL, 30));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label valueName1 = new Label("Course Title:");
		valueName1.setFont(new Font(fontType, 20));
		grid.add(valueName1, 0, 1);

		final TextField searchField = new TextField();
		TextFields.bindAutoCompletion(searchField, db.getCourseTable());
		searchField.setFont(new Font(fontType, 18));
		grid.add(searchField, 1, 1);

		final String fxBorder = "-fx-border:none;";
		final String fxBackground = "-fx-background-color:"+DARK_GREEN+";";
		final String fxTextFill = "-fx-text-fill:#FFF";
		Button btn = new Button("Search");
		btn.setStyle("-fx-graphic-text-gap: 5;"
				+ fxBorder
				+ fxBackground
				+ "-fx-font-size:1.4em;"
				+ fxTextFill);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 1);
        
        Text courseTitles = new Text("Course");
        Font titles = new Font(fontType, 20);
        courseTitles.setFont(titles);
	    grid.add(courseTitles, 1, 4); 
	    
	    Text courseProfessors = new Text("Professor");
	    courseProfessors.setFont(titles);
	    grid.add(courseProfessors, 2, 4);
	    
	    Text courseTimings = new Text(" Timing");
	    courseTimings.setFont(titles);
	    grid.add(courseTimings, 3, 4);
        
	    //add line separators
	    Separator horizontalSep = new Separator();
	    horizontalSep.setMaxWidth(1000);
	    horizontalSep.setValignment(VPos.BASELINE);
	    grid.add(horizontalSep, 1, 5);
	    
	    
	    Separator horiSep2 = new Separator();
	    horiSep2.setMaxWidth(400);
	    horiSep2.setValignment(VPos.BASELINE);
	    grid.add(horiSep2, 2, 5);
	    
	    Separator horiSep3 = new Separator();
	    horiSep3.setMaxWidth(1000);
	    horiSep3.setValignment(VPos.BASELINE);
	    grid.add(horiSep3, 3, 5);
	    
	    Separator vertSep = new Separator();
	    vertSep.setValignment(VPos.BOTTOM);
	    vertSep.setOrientation(Orientation.VERTICAL);
	    
        final Text targetClassName = new Text();
        grid.add(targetClassName, 1, 6);
        
        final Text targetClassProfessor = new Text();
        grid.add(targetClassProfessor, 2, 6);
        
        final Text targetClassTimings = new Text();
        grid.add(targetClassTimings, 3, 6);
        
        btn.setOnAction(click -> {
                targetClassName.setFill(Color.web(Colors.DARK_GREEN));
                String style = "-fx-font-color: " + LIGHT_GREEN;
            		targetClassName.setStyle(style);
            		
            		targetClassTimings.setFill(Color.web(Colors.DARK_GREEN));//setFill(Color.DARKGREEN);
            		targetClassTimings.setStyle(style);
            		
            		targetClassProfessor.setFill(Color.web(Colors.DARK_GREEN));//setFill(Color.DARKGREEN);
            		targetClassProfessor.setStyle(style);
                
                final String selectedClass = searchField.getText();
                if(!db.getCourseByString(selectedClass))
                {
                		targetClassName.setText("No such class exists.");
                		targetClassName.setFont(new Font(fontType, 17));
                		targetClassTimings.setText("Please try again.");
                		targetClassTimings.setFont(new Font(fontType, 17));
                		return;
                }
                
                //need to get relevant information about that class to display information about it.
                Course selected = db.findCourse(selectedClass);
                final String classDays = selected.getDays();
                final Time classStart = selected.getStart();
                final Time classEnd = selected.getEnd();
                final String start = Time.toString(classEnd);
                final String end = Time.toString(classStart);
                final String times = classDays + " " + start + " - " + end;
                final Teacher professor = selected.getInstructor();
                final String classProfessor = professor.first + " " + professor.last;
                final int waitlistLength = db.getWaitlistDb().get(selected).size();
                final String quartersOffered = "F W";
                final int estimatedCrashing = Math.round(waitlistLength)/2;
                
                
                targetClassName.setText(selectedClass);
                targetClassName.setFont(new Font(fontType, 17));
                targetClassTimings.setText(times);
                targetClassTimings.setFont((new Font(fontType, 17)));
                targetClassProfessor.setText(classProfessor);
                targetClassProfessor.setFont(new Font(fontType, 17));
                
                final String fxTestGap = "-fx-graphic-text-gap: 2;";
                final String fxFontSize = "-fx-font-size:1em;";
                Button viewClass = new Button("View");
                viewClass.setStyle(fxTestGap
        				+ fxBorder
        				+ fxBackground
        				+ fxFontSize
        				+ fxTextFill);
                HBox viewBtn = new HBox(2);
                viewBtn.setAlignment(Pos.CENTER);
                viewBtn.getChildren().add(viewClass);
                grid.add(viewBtn, 0, 6);
                
                viewClass.setOnAction(event ->
                	{
                		
                		final Stage popupStage =new Stage();
                	    GridPane popGrid = new GridPane();
                	    popGrid.setAlignment(Pos.TOP_LEFT);
                		popGrid.setHgap(5);
                		popGrid.setVgap(5);
                		popGrid.setPadding(new Insets(5, 5, 5, 5));
                		popupStage.initModality(Modality.APPLICATION_MODAL);
                		popupStage.setTitle("Detailed Course Information");
                		
                		Label popLabel= new Label("Course Details");
                		popLabel.setFont(Font.font(fontType,20));
                		popGrid.add(popLabel, 0, 0);
                		
                		Text courseTitle = new Text(selectedClass);
                		courseTitle.setFont(new Font(fontType, 15));
                		popGrid.add(courseTitle, 0, 2);
                		
                		Text courseTimings1 = new Text(times);
                		courseTimings1.setFont(new Font(fontType, 15));
                		popGrid.add(courseTimings1, 0, 3);
                		
                		Text courseProfessor = new Text(classProfessor);
                		courseProfessor.setFont(new Font(fontType, 15));
                		popGrid.add(courseProfessor, 1, 2);
                		
                		Text professorRatings = new Text("Professor Rating: 5 stars");
                		professorRatings.setFont(new Font(fontType, 15));
                		popGrid.add(professorRatings, 1, 3);
                		
                		Text spotsAvailable = new Text("Spots Available: "+ Integer.toString(20));
                		spotsAvailable.setFont(new Font(fontType, 15));
                		popGrid.add(spotsAvailable, 0, 4);
                		
                		Text waitlistLengthText = new Text("Waitlist Length: "+Integer.toString(waitlistLength));
                		waitlistLengthText.setFont(new Font(fontType, 15));
                		popGrid.add(waitlistLengthText, 1, 4);
                		
                		Text quartersOfferedText = new Text("Quarters Offered: "+quartersOffered);
                		quartersOfferedText.setFont(new Font(fontType, 15));
                		popGrid.add(quartersOfferedText, 0, 5);
                		
                		Text estCrashing = new Text("Estimated Students Crashing: " + Integer.toString(estimatedCrashing));
                		estCrashing.setFont(new Font(fontType, 15));
                		popGrid.add(estCrashing, 0, 6);
                		
                		double probability = -1;
                		if(waitlistLength > 0)
                		{
                			probability = db.getProbability(selected, student, professor);
                			if((Math.abs(probability-0.0) < 0.0001))
                			{
                				Random r = new Random();
                				probability = 0.1 + (2.2 - 0.1) * r.nextDouble();
                			}
                			String probStr = String.format("%1.2f", probability);
                			Text prob = new Text("Probability of Getting In: " + probStr + "%");
                			prob.setFont(new Font(fontType, 18));
                			popGrid.add(prob, 0, 7);
                		}
                		
                		
                		Button close= new Button("Close");
                		close.setStyle(fxTestGap
                				+ fxBorder
                				+ fxBackground
                				+ fxFontSize
                				+ fxTextFill);
                		close.setOnAction(event2 ->
                				popupStage.close()
                		);
                		Scene scene1= new Scene(popGrid, 500, 200);
                		popupStage.setScene(scene1);
                		popupStage.showAndWait();
                });
                
                final Button addToSchedule = new Button("Add to Schedule");
                addToSchedule.setStyle(fxTestGap
        				+ fxBorder
        				+ fxBackground
        				+ fxFontSize
        				+ fxTextFill);
                HBox addBtn = new HBox(5);
                addBtn.setAlignment(Pos.CENTER);
                addBtn.getChildren().add(addToSchedule);
                grid.add(addBtn, 4, 6);
                
                addToSchedule.setOnAction(event -> {
                		System.out.println("add course ");
                		
                		boolean ret = student.addCourse(selected);
                		System.out.println("addCourse ret = " + ret  + " ");
                });
            }
        );
        return grid;
	}

}
