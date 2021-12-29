package tower;

import entity.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Projectile is created when the tower attacks a monster. The GameManager
 * will create the animation using the start and end locations.
 */
public class Arrow extends Projectile {
    private Entity target;     // The target of the attack
    private final int startX;   // Starting location of the projectile
    private final int startY;
    private int endX;
    private int endY;
	private static final String arrow_image_path = ClassLoader.getSystemResource("arrow.png").toString();//use class loader
    private Image newArrow = new Image(arrow_image_path);


    Arrow(Entity target , int towerX , int towerY){
        super(target,towerX , towerY , 50);
        this.target = target;
        startX = towerX;
        startY = towerY;
        endX = target.getPixelX();
        endY = target.getPixelY();
        int deltaX = endX-startX;
        int deltaY = endY-startY;
        float angle = (float) Math.toDegrees(Math.atan2(deltaY,deltaX));
        if(angle < 0){
            angle += 360;
        }
		this.setFill(new ImagePattern(newArrow));
		this.setRotate(angle+90);
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

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return newArrow;
	}



}

