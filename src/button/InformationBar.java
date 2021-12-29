package button;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import logic.SimulationManager;

public class InformationBar extends HBox{
	ProgressBar healthBar = SimulationManager.healthBar;
	Label moneyLabel;
	WavePane wavePane;
	
	public InformationBar(){
		moneyLabel = new Label();
		moneyLabel.textProperty().setValue("$ " + SimulationManager.getMoney());
		moneyLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: yellow; -fx-font-weight: bold; -fx-font-family: \"family\"; -fx-background-color: darkslategrey;"
				+ " -fx-border-style: solid; -fx-border-color: dimgrey ; -fx-border-width: 5 ;");
		//=========
		wavePane = new WavePane();
		//=========
		healthBar.setPrefWidth(140);
		healthBar.setPrefHeight(20);
		healthBar.setStyle("-fx-accent: tomato");
		
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(195);
		this.setPadding(new Insets(10));
		this.getChildren().addAll(moneyLabel, wavePane, healthBar);
	}public void InformationUpdate(){
		moneyLabel.textProperty().setValue("$ " + SimulationManager.getMoney());
		wavePane.upDate();
	}
}
