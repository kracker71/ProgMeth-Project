package entity;

import javafx.scene.image.ImageView;
import logic.SimulationManager;
import logic.TileMap;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable{

	protected int pixelX, pixelY;
	protected int idxX, idxY;
	private static int Z = 0;					// Determine the order to draw
	protected boolean isDestroyed;
	protected int healthPoints;                 // Determines if the monster is still alive
    private int speed;         					// Determines time to complete path
    private int reward;                         // Monster death will trigger a resource reward
    private boolean isDead;                     // Flag is signal monster removal
    private boolean isPathFinished;               // Signals the monster finished the path alive.
    private int damage;

	
	public Entity(int healthPoints,int speed, int reward, int damage){
		this.damage = damage;
		this.healthPoints = healthPoints;
		this.speed = speed;
		this.reward = reward;
		pixelX = TileMap.BEGIN_X_IDX * TileMap.TILE_LENGTH_X; 
		pixelY = TileMap.BEGIN_Y_IDX * TileMap.TILE_LENGTH_Y;
		idxX = TileMap.BEGIN_X_IDX; //idxX = pixelX / TileMap.TILE_LENGTH_X;
		idxY = TileMap.BEGIN_Y_IDX; //idxY = pixelY / TileMap.TILE_LENGTH_Y;
		isDestroyed = false;
		isPathFinished = false;
		isDead = false;
	}
	public int getZ(){
		return Z;
	}
	
	@Override
	public boolean isDestroyed(){
		return isDestroyed;
	}
	public int getPixelX() {
		return pixelX;
	}

	public int getPixelY() {
		return pixelY;
	}

	public int getIdxX() {
		return idxX;
	}

	public int getIdxY() {
		return idxY;
	}

	public int getReward(){
    	return reward;
	}
	public boolean isDead(){
    	return isDead;
	}
	public boolean isPathFinished(){
    	return isPathFinished;
	}
	public void takeDamage(int damage){
    	healthPoints = healthPoints - damage;
    	if (healthPoints <= 0){
        	isDead = true;
        	isPathFinished = false;
        	isDestroyed = true;
    	}
	}public void walk(int[][] map) {
		switch(map[idxY][idxX]){
        	case TileMap.SIMPLE_GRASS: //simple grass do nothing
        		break;
        	case TileMap.TURRET_PLACE: //turret place able grass do nothing
        		break;
        	case TileMap.UP_VERTICAL_PATH: //move up
        		moveY(-1);
        		break;
        	case TileMap.DOWN_VERTICAL_PATH: //move down
        		moveY(1);
        		break;
        	case TileMap.HORIZON_PATH: //move forward
        		moveX();
        		break;
        	case TileMap.EAST_TO_NORTH_PATH: //move EAST TO NORTH
        		moveY(-1);
        		break;
        	case TileMap.NORTH_TO_EAST_PATH: //move NORTH TO EAST
        		if(pixelY > TileMap.TILE_LENGTH_Y * idxY){ //must go up more to keep walking in the path tile
        			moveY(-1);
        		}
        		else{
        			moveX();
        		}
        		break;
        	case TileMap.SOUTH_TO_EAST_PATH: //move SOUTH TO EAST
        		moveX();
        		break;
        	case TileMap.EAST_TO_SOUTH_PATH: //move EAST TO SOUTH
        		moveY(1);
        		break;
        	case TileMap.TOWER_TILE: //tower tile 
        		break;
        	case TileMap.GOAL_TILE: //goal tile
        		isPathFinished = true;
        		SimulationManager.attacked(damage);
        		System.out.println("getDamage");
        		isDestroyed = true;
        		break;
		}
	}
	private void moveX(){
		pixelX += speed;
		idxX = pixelX / TileMap.TILE_LENGTH_X;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	private void moveY(int factor){
		//factor = 1  mean move down
		//factor = -1 mean move up
		pixelY += speed * factor;
		idxY = pixelY / TileMap.TILE_LENGTH_Y;
	}public abstract ImageView getImageView();
}
