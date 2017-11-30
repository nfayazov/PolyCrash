package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

public class SearchPage extends Application implements Page
{
	Label label1;
	Button button1;
	int i = 1;
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
		scenetitle.setFont(Font.font("Cambria", FontWeight.NORMAL, 30));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label valueName1 = new Label("Course Title:");
		valueName1.setFont(new Font("Cambria", 20));
		grid.add(valueName1, 0, 1);

		final TextField searchField = new TextField();
		//final List<String> classes = new ArrayList<String>(Arrays.asList("CSC 307", "CSC 225", "CSC 357"));
		final Database db = new Database();
		TextFields.bindAutoCompletion(searchField, db.getCourseTable());
		searchField.setFont(new Font("Cambria", 18));
		//TextFields.bindAutoCompletion(searchField, classes);
		grid.add(searchField, 1, 1);

		Button btn = new Button("Search");
		btn.setStyle("-fx-graphic-text-gap: 5;"
				+ "-fx-border:none;"
				+ "-fx-background-color:"+DARK_GREEN+";"
				+ "-fx-font-size:1.4em;"
				+ "-fx-text-fill:#FFF");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 1);
        
        Text CourseTitles = new Text("Course");
        CourseTitles.setFont(Font.font("Cambria",20));
	    grid.add(CourseTitles, 1, 4); 
	    
	    Text courseProfessors = new Text("Professor");
	    courseProfessors.setFont(Font.font("Cambria", 20));
	    grid.add(courseProfessors, 2, 4);
	    
	    Text courseTimings = new Text(" Timing");
	    courseTimings.setFont(Font.font("Cambria", 20));
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
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            public void handle(ActionEvent e) {
                targetClassName.setFill(Color.DARKGREEN);
            		targetClassName.setStyle("-fx-font-color: " + LIGHT_GREEN);
            		
            		targetClassTimings.setFill(Color.DARKGREEN);
            		targetClassTimings.setStyle("-fx-font-color: " + LIGHT_GREEN);
            		
            		targetClassProfessor.setFill(Color.DARKGREEN);
            		targetClassProfessor.setStyle("-fx-font-color: " + LIGHT_GREEN);
                
                final String selectedClass = searchField.getText();
                if(db.getCourseByString(selectedClass) == false)
                {
                		System.out.println("selected class = " + selectedClass);
                		targetClassName.setText("No such class exists.");
                		targetClassName.setFont(new Font("Cambria", 17));
                		targetClassTimings.setText("Please try again.");
                		targetClassTimings.setFont(new Font("Cambria", 17));
                		return;
                }
                
                //need to get relevant information about that class to display information about it.
                final String classTimes = "MWF 3:00 - 4:00 PM";
                final String classProfessor = "Falessi";
                //int spotsAvailable = 20;
                final int waitlistLength = 0;
                final String quartersOffered = "F W";
                final int estimatedCrashing = 0;
                
                
                targetClassName.setText(selectedClass);
                targetClassName.setFont(new Font("Cambria", 17));
                targetClassTimings.setText(classTimes);
                targetClassTimings.setFont((new Font("Cambria", 17)));
                targetClassProfessor.setText(classProfessor);
                targetClassProfessor.setFont(new Font("Cambria", 17));
                
                
                //grid.add(vertSep, 2, 6);
                
                Button viewClass = new Button("View");
                viewClass.setStyle("-fx-graphic-text-gap: 2;"
        				+ "-fx-border:none;"
        				+ "-fx-background-color:"+DARK_GREEN+";"
        				+ "-fx-font-size:1em;"
        				+ "-fx-text-fill:#FFF");
                HBox viewBtn = new HBox(2);
                viewBtn.setAlignment(Pos.CENTER);
                viewBtn.getChildren().add(viewClass);
                grid.add(viewBtn, 0, 6);
                
                viewClass.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent e)
                	{
                		System.out.println("view detailed class information");
                		
                		final Stage popupStage =new Stage();
                	    GridPane popGrid = new GridPane();
                	    popGrid.setAlignment(Pos.TOP_LEFT);
                		popGrid.setHgap(5);
                		popGrid.setVgap(5);
                		popGrid.setPadding(new Insets(5, 5, 5, 5));
                		popupStage.initModality(Modality.APPLICATION_MODAL);
                		popupStage.setTitle("Detailed Course Information");
                		
                		Label label1= new Label("Course Details");
                		label1.setFont(Font.font("Cambria",20));
                		popGrid.add(label1, 0, 0);
                		
                		Text courseTitle = new Text(selectedClass);
                		courseTitle.setFont(new Font("Cambria", 15));
                		popGrid.add(courseTitle, 0, 2);
                		
                		Text courseTimings = new Text(classTimes);
                		courseTimings.setFont(new Font("Cambria", 15));
                		popGrid.add(courseTimings, 0, 3);
                		
                		Text courseProfessor = new Text(classProfessor);
                		courseProfessor.setFont(new Font("Cambria", 15));
                		popGrid.add(courseProfessor, 1, 2);
                		
                		Text professorRatings = new Text("Professor Rating: 5 stars");
                		professorRatings.setFont(new Font("Cambria", 15));
                		popGrid.add(professorRatings, 1, 3);
                		
                		Text spotsAvailable = new Text("Spots Available: "+ Integer.toString(20));
                		spotsAvailable.setFont(new Font("Cambria", 15));
                		popGrid.add(spotsAvailable, 0, 4);
                		
                		Text waitlistLengthText = new Text("Waitlist Length: "+Integer.toString(waitlistLength));
                		waitlistLengthText.setFont(new Font("Cambria", 15));
                		popGrid.add(waitlistLengthText, 1, 4);
                		
                		Text quartersOfferedText = new Text("Quarters Offered: "+quartersOffered);
                		quartersOfferedText.setFont(new Font("Cambria", 15));
                		popGrid.add(quartersOfferedText, 0, 5);
                		
                		Text estCrashing = new Text("Estimated Students Crashing: " + Integer.toString(estimatedCrashing));
                		estCrashing.setFont(new Font("Cambria", 15));
                		popGrid.add(estCrashing, 0, 6);
                		
                		
                		
                		Button button1= new Button("Close");
                		button1.setStyle("-fx-graphic-text-gap: 2;"
                				+ "-fx-border:none;"
                				+ "-fx-background-color:"+DARK_GREEN+";"
                				+ "-fx-font-size:1em;"
                				+ "-fx-text-fill:#FFF");
                		button1.setOnAction(new EventHandler<ActionEvent>() {
                			public void handle(ActionEvent e)
                			{
                				popupStage.close();
                			}
                		});
                		Scene scene1= new Scene(popGrid, 500, 200);
                		popupStage.setScene(scene1);
                		popupStage.showAndWait();
                		
                	}
                });
                
                final Button addToSchedule = new Button("Add to Schedule");
                addToSchedule.setStyle("-fx-graphic-text-gap: 2;"
        				+ "-fx-border:none;"
        				+ "-fx-background-color:"+DARK_GREEN+";"
        				+ "-fx-font-size:1em;"
        				+ "-fx-text-fill:#FFF");
                HBox addBtn = new HBox(5);
                addBtn.setAlignment(Pos.CENTER);
                addBtn.getChildren().add(addToSchedule);
                grid.add(addBtn, 4, 6);
                
                addToSchedule.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent e)
                	{
                		System.out.println("Send this information to Schedule");
                	}
                });
            }
        });
        return grid;
	}

}
