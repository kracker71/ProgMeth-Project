package drawing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;

public class HomeScene extends GridPane {
	public Button start;
	public Button exit;
	
	public HomeScene() {
		super();
		setAlignment(Pos.TOP_CENTER);
		setPadding(new Insets(5));
		setHgap(2);
		setVgap(2);
		
		Label topic = new Label("        Tower Defense");
		topic.setTextFill(Color.DARKRED);
		topic.setFont(new Font("Serif Bold Italic" , 60));
		add(topic,0,0);
		
		Label subTopic = new Label("                       The Legends of Tower");
		subTopic.setTextFill(Color.DARKORANGE);
		subTopic.setFont(new Font("Serif Bold Italic" , 30));
		add(subTopic,0,1);
		
		start = new Button("Start");
		start.setFont(new Font("Serif Bold Italic",24));
		start.setPrefSize(120,40);
		start.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
		add(start, 0, 150);
		
		exit = new Button("Exit");
		exit.setFont(new Font("Serif Bold Italic",24));
		exit.setPrefSize(120,40);
		exit.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
		add(exit, 1, 150);
	}
	
}
