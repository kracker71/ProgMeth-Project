package entity;

import data.SpriteAnimation;
import drawing.Main;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Knight extends Entity{
	ImageView imageView;
	Animation animation;
	private static int healthPoints = 15; // Determines if the monster is still alive
    private static final int SPEED = 2;         // Determines time to complete path
    private static final int REWARD = 4;       // Monster death will trigger a resource reward
    private static final int DAMAGE = 10;

	public Knight() {
		super(healthPoints, SPEED, REWARD, DAMAGE);
		createFirstSprite();
		createSprite();
	}
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
        Main.removeFromPane(imageView);
        Main.relocate(pixelX, pixelY, imageView);
        Main.addToPane(imageView);
	}public ImageView getImageView(){
		return imageView;
	}
/////////////////////////////////////// Player Animation //////////////////////////////////////

	private static final String image_path = ClassLoader.getSystemResource("Knight Walk.png").toString();
	private static final Image IMAGES = new Image(image_path);

	private static final int COLUMNS  = 4;
	private static final int COUNT    = 4;
	private static final int OFFSET_X = 0;
	private static final int OFFSET_Y = 0;
	private static final int WIDTH    = 29;
	private static final int HEIGHT   = 40;

	void createFirstSprite() {
		imageView = new ImageView(IMAGES);
	}

	void createSprite() {
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));//achieving a "zoom" effect ,rotate
		imageView.relocate((double)(pixelX), (double)(pixelY));
		animation = new SpriteAnimation(
					imageView,
					Duration.millis(500),
					COUNT, COLUMNS,
					OFFSET_X, OFFSET_Y,
					WIDTH, HEIGHT
					);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();			
	}
}
