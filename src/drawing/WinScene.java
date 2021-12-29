package drawing;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class WinScene extends GridPane {
	public Button exit;
	
	public WinScene() {
		super();
		setAlignment(Pos.BOTTOM_CENTER);
		setPadding(new Insets(25, 25, 25, 25));
		
		exit = new Button("Exit");
		exit.setFont(new Font("Serif Bold Italic",24));
		exit.setPrefSize(120,40);
		exit.setVisible(false);
		
		add(exit, 10, 5000);
	}
}