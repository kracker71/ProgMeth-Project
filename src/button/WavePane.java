package button;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.SimulationManager;

public class WavePane extends VBox {
	
	Label waveLabel;
	Text leftTime;
	int counter;
	
	public WavePane(){
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(8);
		
		waveLabel = new Label();
		waveLabel.textProperty().setValue("WAVE " + SimulationManager.getWave());
		waveLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: navy;"
				+ " -fx-font-weight: bold; -fx-font-family: \"family\";");
		
		counter =  Math.max(SimulationManager.waveTime - SimulationManager.timer, 0);
		leftTime = new Text("" + counter);
		leftTime.setStyle("-fx-font-size: 20px; -fx-text-fill: grey;");
		
		this.getChildren().addAll(waveLabel, leftTime);
	}
	public void upDate() {
		waveLabel.textProperty().setValue("WAVE " + SimulationManager.getWave());
		counter =  Math.max(SimulationManager.waveTime - SimulationManager.timer,0);
		leftTime.setText("" + counter);
	}
}
