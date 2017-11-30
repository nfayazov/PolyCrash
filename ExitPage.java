package logic;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class ExitPage extends Application implements Page
{
	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Exit Page");
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
		
		Text scenetitle = new Text("Logout and Exit?");
		final String fontType = "Cambria";
		scenetitle.setFont(Font.font(fontType, FontWeight.NORMAL, 50));
		grid.add(scenetitle, 22, 25);
		
		final String fxBorder = "-fx-border:none;";
		final String fxBackground = "-fx-background-color:"+DARK_GREEN+";";
		final String fxTextFill = "-fx-text-fill:#FFF";
		final String fxTextGap = "-fx-graphic-text-gap: 5;";
		final String fxFontSize = "-fx-font-size:2em;";
		
		Button exitButton = new Button("Yes");
		exitButton.setStyle(fxTextGap
				+ fxBorder
				+ fxBackground
				+ fxFontSize
				+ fxTextFill);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(exitButton);
        grid.add(hbBtn, 19, 27);
        
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
        		public void handle(ActionEvent e)
        		{
        			System.out.println("Exiting application");
        			System.exit(0);
        		}
        });
        
        
        
        Button stayButton = new Button("No");
		stayButton.setStyle(fxTextGap
				+ fxBorder
				+ fxBackground
				+ fxFontSize
				+ fxTextFill);
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(stayButton);
        grid.add(hbBtn1, 25, 27);
        
        stayButton.setOnAction(new EventHandler<ActionEvent>() {
        		public void handle(ActionEvent e)
        		{
        			System.out.println("stay on application");
        		}
        });
		
             
        return grid;
	}

}
