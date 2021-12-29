package drawing;


import entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Canvas {

	public GameScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
	}
	public void paintComponent(GraphicsContext gc) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (!entity.isDestroyed()) {
				entity.draw(gc);
			}else if(entity instanceof Entity){
				Entity e = (Entity) entity;
				Main.removeFromPane(e.getImageView());
			}
		}

	}

}
