package tower;

import entity.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


/**
 * Projectile is created when the tower attacks a monster. The GameManager
 * will create the animation using the start and end locations.
 */
public class LavaRock extends Projectile {
    private Entity target;     // The target of the attack
    private final int startX;   // Starting location of the projectile
    private final int startY;

	private static final String lava_image_path = ClassLoader.getSystemResource("pixellava.png").toString();//use class loader
	Image newLava = new Image(lava_image_path);


    LavaRock(Entity target , int towerX , int towerY){
        super(target,towerX , towerY , 9);
        this.target = target;
        startX = towerX;
        startY = towerY;
		this.setFill(new ImagePattern(newLava));
    }

    public Entity getTarget(){
        return target;
    }

    public int getEndX(){
        return target.getPixelX();
    }

    public int getEndY(){
        return target.getPixelY();
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }
    public Image getImage() {
		// TODO Auto-generated method stub
		return newLava;
	}
}

