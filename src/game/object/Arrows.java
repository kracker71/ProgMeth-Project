package game.object;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arrows extends Rectangle {
	private MobBase target;     // The target of the attack
    private final int startX;   // Starting location of the projectile
    private final int startY;


    public Arrows(MobBase target , int towerX , int towerY , Image image){
        super(towerX,towerY,2,10);
        this.target = target;
        startX = towerX;
        startY = towerY;
        this.setFill(new ImagePattern(image));
    }

    public MobBase getTarget(){
        return target;
    }

    public int getEndX(){
        return target.getX();
    }

    public int getEndY(){
        return target.getY();
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }
}
