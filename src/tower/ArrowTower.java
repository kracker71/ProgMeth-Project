package tower;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ArrowTower extends Tower {
	private static String url = "Arrow Tower.png";
	private static String NAME = "Arrow Tower";
	private static String urlForDraw = "Arrow Tower40.png";
	
	private static final String image_path = ClassLoader.getSystemResource(urlForDraw).toString();
	private static Image image = new Image(image_path);

	public ArrowTower() {
		this.attackDamage = 5;
		this.attackRange = 0;
		this.sellCost = 20;
	}
	public ArrowTower(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.attackDamage = 5;
		this.attackRange = 120;
		this.sellCost = 20;
	}
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
        gc.drawImage(image, pixelX, pixelY);
	}

	@Override
	public void createProjectile(Entity target) {
		// TODO Auto-generated method stub
		projectileList.add(new Arrow(target , pixelX ,pixelY));
	}
	public String getUrl() {
		return url;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

}
