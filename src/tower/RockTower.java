package tower;

import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RockTower extends Tower {
	private static String url = "Rock Tower.png";
	private static String NAME = "Rock Tower";
	private static String urlForDraw = "Rock Tower40.png";
	private static final String image_path = ClassLoader.getSystemResource(urlForDraw).toString();
	private static Image image = new Image(image_path);

	public RockTower() {
		this.attackDamage = 25;
		this.attackRange = 0;
		this.sellCost = 100;
	}
	public RockTower(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.attackDamage = 25;
		this.attackRange = 120;
		this.sellCost = 100;
	}

	@Override
	public void createProjectile(Entity target) {
		// TODO Auto-generated method stub
		projectileList.add(new LavaRock(target , pixelX ,pixelY));
	}public String getUrl() {
		return url;
	}
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
        gc.drawImage(image, pixelX, pixelY);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

}
