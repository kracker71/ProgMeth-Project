package game.object;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import game.engine.Coordinate;
public abstract class MobBase {
	private static ArrayList<Coordinate> path;
	
	
	private int healthPoints;
	private int movementSpeed;
	private int reward;
	private boolean moveX;
	private int nodeDirection;
	private boolean isDead;
	private boolean pathFinished;			//Tell that mob has finished path alive
	private int x;
	private int y;
	private int z;
	
	
	
	
	public MobBase(int healthPointS) {
		pathFinished = false;
		moveX = true;
		nodeDirection = 1;
		this.healthPoints = healthPoints;
		movementSpeed = 1;
		reward = 2;
	}
	
	public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getReward(){
        return reward;
    }
    
    public boolean isDead() {
    	return isDead;
    	
    }
	
    public boolean isPathFinished() {
    	return pathFinished;

    }
    
    public static void setPath(ArrayList<Coordinate> pathSet){
        path = pathSet;
    }
    
    public void takeDamage(int damage){
        healthPoints = healthPoints - damage;
        if (healthPoints <= 0){
            isDead = true;
            pathFinished = false;
        }
    }
    
    public void updateLocation(int distance){

    }
    
	
}
