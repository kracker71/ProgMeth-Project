package button;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import logic.SimulationManager;
import tower.*;

//You might need to do something to the following line
public class ItemPane extends HBox{

	private ObservableList<ItemButton> itemButtonList = FXCollections.observableArrayList();
	
	public ItemPane () {
		// TODO implements the ItemPane's constructor
		this.setAlignment(Pos.BOTTOM_RIGHT);
		this.setPadding(new Insets(10));
		this.setSpacing(8);
		ItemButton arrowTower = new ItemButton(new ArrowTower()); 
		itemButtonList.add(arrowTower);
		ItemButton rockTower = new ItemButton(new RockTower()); 
		itemButtonList.add(rockTower);

		
		arrowTower.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(SimulationManager.getMoney()>=arrowTower.getItem().getSellCost()) {
					setSelectedButton(arrowTower);
				}
			}
			
		});
		rockTower.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(SimulationManager.getMoney()>=rockTower.getItem().getSellCost()) {
					setSelectedButton(rockTower);
				}
			}
			
		});
		this.getChildren().addAll(arrowTower, rockTower);	
	} 

	public void setSelectedButton ( ItemButton selecteditemButton ) {
		// TODO set selectedItemButton of SimulationManager to given ItemButton
		// resetButtonsBackgroundColor and the highlight the selecteditemButton
		SimulationManager.setSelectedItemButton(selecteditemButton);
		resetButtonsBackGroundColor();
		selecteditemButton.highlight();
	}

	public void resetButtonsBackGroundColor() {
		// TODO unhighlight each button in itemButtonList
		for(ItemButton itemBtn : itemButtonList) {
			itemBtn.unhighlight();
		}
	}	
	
}
