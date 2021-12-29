package button;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tower.*;
import javafx.geometry.Insets;

// You might need to do something to the following line
public class ItemButton extends Button{

	private Tower tower;

	ItemButton(Tower tower){
		// TODO complete the constructor
		this.tower = tower;
		this.setPadding(new Insets(5));
		String image_path = ClassLoader.getSystemResource(tower.getUrl()).toString();
		ImageView imageView = new ImageView(new Image(image_path));
		imageView.setFitWidth(90);
		imageView.setFitHeight(90);
		this.setGraphic(imageView);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setTooltip();
	}

	public void highlight() {
		// TODO 
		setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void unhighlight() {
		// TODO
		setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	// TODO GETTER
	public Tower getItem() {
		return tower;
	}

	
	private void setTooltip() {
		Tooltip	tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText((String)tower.getToolText());
		this.setOnMouseMoved((MouseEvent e) -> {
			if (tower != null)
			tooltip.show(this, e.getScreenX(), e.getScreenY()+10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});		
	}


	

}
