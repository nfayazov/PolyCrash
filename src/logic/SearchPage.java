//need to integrate this with MainPage sidebars.

import org.controlsfx.control.textfield.TextFields;

import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;

public class SearchPage extends Application
{
	Label label1;
	Button button1;
	int i = 1;
	public static void main(String[] args)
	{
		//Database db = new Database();
		//System.out.println(db.getCourseTable());
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Search Page");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Search Classes");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label valueName1 = new Label("Course Title:");
		grid.add(valueName1, 0, 1);

		TextField searchField = new TextField();
		List<String> classes = new ArrayList<String>(Arrays.asList("CSC 307", "CSC 225", "CSC 357"));
		Database db = new Database();
		TextFields.bindAutoCompletion(searchField, db.getCourseTable());
		//TextFields.bindAutoCompletion(searchField, classes);
		grid.add(searchField, 1, 1);

		Button btn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 1);
        
        Text CourseTitles = new Text("Course");
        CourseTitles.setFont(Font.font("Arial",15));
	    grid.add(CourseTitles, 1, 4); 
	    
	    Text courseProfessors = new Text("Professor");
	    courseProfessors.setFont(Font.font("Arial", 15));
	    grid.add(courseProfessors, 2, 4);
	    
	    Text courseTimings = new Text(" Timing");
	    courseTimings.setFont(Font.font("Arial", 15));
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
        	 
            @Override
            public void handle(ActionEvent e) {
                targetClassName.setFill(Color.DARKGREEN);
            		targetClassName.setStyle("-fx-font-color: " + LIGHT_GREEN);
            		
            		targetClassTimings.setFill(Color.DARKGREEN);
            		targetClassTimings.setStyle("-fx-font-color: " + LIGHT_GREEN);
            		
            		targetClassProfessor.setFill(Color.DARKGREEN);
            		targetClassProfessor.setStyle("-fx-font-color: " + LIGHT_GREEN);
                
                String selectedClass = searchField.getText();
                Course selectedCourse = new Course(selectedClass, 1);
                Course testCourse = new Course(selectedClass, 1);
                //db.getCourseTable().get
                System.out.println(selectedCourse);
                System.out.println(testCourse);
                //NOTE: all selected courses are showing up as not being a part of getCourseTable despite showing up as a suggestion.
                if(db.getCourseTable().contains(selectedCourse) == false)
                {
                		System.out.println("selected class = " + selectedClass);
                		targetClassName.setText("No such class exists.");
                		targetClassTimings.setText("Please try again.");
                		return;
                }
                
                //need to get relevant information about that class to display information about it.
                String classTimes = "MWF 3:00 - 4:00 PM";
                String classProfessor = "Falessi";
                int spotsAvailable = 20;
                int waitlistLength = 0;
                String quartersOffered = "F W";
                int estimatedCrashing = 0;
                
                
                targetClassName.setText(selectedClass);
                targetClassTimings.setText(classTimes);
                targetClassProfessor.setText(classProfessor);
                //grid.add(vertSep, 2, 6);
                
                Button viewClass = new Button("View");
                HBox viewBtn = new HBox(2);
                viewBtn.setAlignment(Pos.CENTER);
                viewBtn.getChildren().add(viewClass);
                grid.add(viewBtn, 0, 6);
                
                viewClass.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent e)
                	{
                		System.out.println("view detailed class information");
                		
                		Stage popupStage =new Stage();
                	    GridPane popGrid = new GridPane();
                	    popGrid.setAlignment(Pos.TOP_LEFT);
                		popGrid.setHgap(5);
                		popGrid.setVgap(5);
                		popGrid.setPadding(new Insets(5, 5, 5, 5));
                		popupStage.initModality(Modality.APPLICATION_MODAL);
                		popupStage.setTitle("Detailed Course Information");
                		
                		Label label1= new Label("Course Details");
                		popGrid.add(label1, 0, 0);
                		
                		Text courseTitle = new Text(selectedClass);
                		popGrid.add(courseTitle, 0, 2);
                		
                		Text courseTimings = new Text(classTimes);
                		popGrid.add(courseTimings, 0, 3);
                		
                		Text courseProfessor = new Text(classProfessor);
                		popGrid.add(courseProfessor, 1, 2);
                		
                		Text professorRatings = new Text("Professor Rating: 5 stars");
                		popGrid.add(professorRatings, 1, 3);
                		
                		Text spotsAvailable = new Text("Spots Available: "+ Integer.toString(20));
                		popGrid.add(spotsAvailable, 0, 4);
                		
                		Text waitlistLengthText = new Text("Waitlist Length: "+Integer.toString(waitlistLength));
                		popGrid.add(waitlistLengthText, 1, 4);
                		
                		Text quartersOfferedText = new Text("Quarters Offered: "+quartersOffered);
                		popGrid.add(quartersOfferedText, 0, 5);
                		
                		Text estCrashing = new Text("Estimated Students Crashing: " + Integer.toString(estimatedCrashing));
                		popGrid.add(estCrashing, 1, 5);
                		
                		
                		
                		Button button1= new Button("Close");
                		button1.setOnAction(click -> popupStage.close());    
                		Scene scene1= new Scene(popGrid, 400, 400);
                		popupStage.setScene(scene1);
                		popupStage.showAndWait();
                		
                	}
                });
                
                final Button addToSchedule = new Button("Add to Schedule");
                //addToSchedule.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black; -fx-text-size: 20");
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

		Scene scene = new Scene(grid, 960, 700);
		stage.setScene(scene);
		stage.show();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
