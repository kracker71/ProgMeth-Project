package tower;

import entity.*;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * Projectile is created when the tower attacks a monster. The GameManager
 * will create the animation using the start and end locations.
 */
public abstract class Projectile extends Circle {
    private Entity target;     // The target of the attack
    private final int startX;   // Starting location of the projectile
    private final int startY;


    Projectile(Entity target , int towerX , int towerY ,int size){
        super(towerX , towerY , size);
        this.target = target;
        startX = towerX;
        startY = towerY;
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
    	return null;
    };


}

