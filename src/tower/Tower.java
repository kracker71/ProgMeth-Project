package tower;

import java.util.ArrayList;

import entity.Entity;
import logic.TileMap;
import sharedObject.IRenderable;


public abstract class Tower implements IRenderable{
	protected static int Z = 1;//draw before entity
	protected int pixelX,pixelY,idxX,idxY;
	protected final int BUILDTIME = 1;
	protected int attackDamage;
	protected boolean destroyed;
	protected int attackRange;
	protected int sellCost;
	protected static ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	protected TowerAttackService towerAttacker;
	
	public Tower() {

	}
	public Tower(int x,int y) {
		pixelX = x;
		pixelY = y;
		idxX = pixelX / TileMap.TILE_LENGTH_X;
		idxY = pixelY / TileMap.TILE_LENGTH_Y;
		towerAttacker = new TowerAttackService(this);
		towerAttacker.pollTower(BUILDTIME);
	}
	
	public abstract void createProjectile(Entity target);

	public int getBuildTime() {
		return BUILDTIME;
	}
	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	public int getSellCost() {
		return sellCost;
	}

	public void setSellCost(int sellCost) {
		this.sellCost = sellCost;
	}

	public static ArrayList<Projectile> getProjectileList() {
		return projectileList;
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

	public void setPixelX(int pixelX) {
		this.pixelX = pixelX;
		idxX = pixelX / TileMap.TILE_LENGTH_X;
	}

	public void setPixelY(int pixelY) {
		this.pixelY = pixelY;
		idxY = pixelY / TileMap.TILE_LENGTH_Y;
	}
	public String getToolText() {
		return ("Name : "+getName()
				+"\nDamage : "+getAttackDamage()
				+"\nCost : "+getSellCost());
	}

	public TowerAttackService getTowerAttacker() {
		return towerAttacker;
	}
	public int getZ() {
		return Z;
	}
	public boolean isDestroyed(){
		return destroyed;
	}
	public void setTowerAttacker(TowerAttackService towerAttacker) {
		this.towerAttacker = towerAttacker;
	}
	public abstract String getUrl();
	public abstract String getName();

	
}