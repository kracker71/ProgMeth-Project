package game.object;
import game.engine.Coordinate;
import game.engine.services.TowerAttackerService;

import javafx.scene.paint.Color;


public abstract class TowerBase {
	private int BuildTime = 5000;
	private int attackDamage;
	private double attackSpeed;
	private int attackRange;
	private int sellCost;
	private ArrayList<Projectile> projectileList;
	private Coordinate coords;
	private TowerAttackerService towerAttacker;
	
	public TowerBase(int x,int y) {
		projectileList = new ArrayList<Projectile>();
		coords = new Coordinate(x,y);
		attackDamage = 5;
		attackSpeed = 1.0;
		attackRange = 200;
		towerAttacker = new TowerAtackerService<this>;
		towerAttacker.pollTower(BuildTime);
		sellCost = 35;
	}
	
	public void createProjectile(Monster target) {
		projectileList.add(new Projectile(target,coords.getExactX(), coords.getExactY() , Color.BLACK));
	}

	public int getBuildTime() {
		return BuildTime;
	}

	public void setBuildTime(int buildTime) {
		BuildTime = buildTime;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = attackSpeed;
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

	public ArrayList<Projectile> getProjectileList() {
		return projectileList;
	}

	public void setProjectileList(ArrayList<Projectile> projectileList) {
		this.projectileList = projectileList;
	}

	public Coordinate getCoords() {
		return coords;
	}

	public void setCoords(Coordinate coords) {
		this.coords = coords;
	}

	public TowerAttackerService getTowerAttacker() {
		return towerAttacker;
	}

	public void setTowerAttacker(TowerAttackerService towerAttacker) {
		this.towerAttacker = towerAttacker;
	}
	
	
	
}
