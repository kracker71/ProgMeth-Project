package logic;


import button.ItemButton;
import drawing.Main;
import entity.*;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import sharedObject.RenderableHolder;
import tower.Projectile;
import tower.*;

public class SimulationManager {

	public static ItemButton selectedItemButton;
	public static ProgressBar healthBar = new ProgressBar(1);
	
	private static final double MAXHP = 100; 
	private static double health = 100;
	private static int money = 50;
	private static int wave = 0;
	private static int damageOnThisFrame = 0;
	private static boolean isGameOver = false;
	private static boolean isGameEnd = false;
	private static boolean isWin = false;
	private static boolean isLose = false;
	private static boolean hasItem = false;
	
	private static long lastTimeTriggered = -1;
	public static int timer = 0;
	public static int waveTime = 3;
	
	static AudioClip arrowSound = new AudioClip(ClassLoader.getSystemResource("audio/Arrow.wav").toString());
	static AudioClip lavaRockSound = new AudioClip(ClassLoader.getSystemResource("audio/LavaRock.wav").toString());
	static AudioClip gameOverSound = new AudioClip(ClassLoader.getSystemResource("audio/Gameover.wav").toString());
	static AudioClip winningSound = new AudioClip(ClassLoader.getSystemResource("audio/WinSound.mp3").toString());

	static {
		lavaRockSound.setVolume(lavaRockSound.getVolume()/4);
		arrowSound.setVolume(arrowSound.getVolume()/3);
	}
	public static ItemButton getSelectedItemButton() {
		return selectedItemButton;
	}
	
	public static void setSelectedItemButton(ItemButton selectedItemButton) {
		SimulationManager.selectedItemButton = selectedItemButton;
		hasItem = true;
	}
	public static int getWave() {
		return wave;
	}
	public static int getMoney() {
		return money;
	}public static void receiveReward(int reward) {
		money += reward;
	}public static boolean hasItem() {
		return hasItem;
	}
	
	public static void reduceMoneyBuyTower() {
		SimulationManager.money -= SimulationManager.getSelectedItemButton().getItem().getSellCost();
		if(hasItem && SimulationManager.money < SimulationManager.getSelectedItemButton().getItem().getSellCost()) {
			hasItem = false;
			SimulationManager.getSelectedItemButton().unhighlight();
			SimulationManager.setSelectedItemButton(null);
		}
	}
	public static void attacked(int damage) {
		SimulationManager.damageOnThisFrame += damage;
	}
	public static void updateHealth(){
		reduceHealth(damageOnThisFrame);
		healthBar.setProgress((double)health/MAXHP);
		SimulationManager.damageOnThisFrame = 0;
	}
	private static void reduceHealth(int damage) {
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) isGameOver = true;
	}
	public static void createProjectiles(){
		Path projectilePath;
		PathTransition animation;
		for(int i = 0; i < Tower.getProjectileList().size(); i++){
			// Create animation path
			Projectile projectile = Tower.getProjectileList().get(i);
			projectilePath = new Path(new MoveTo(projectile.getStartX() , projectile.getStartY()));
			projectilePath.getElements().add(new LineTo(projectile.getEndX() , projectile.getEndY()));
			animation = new PathTransition(Duration.millis(300) , projectilePath , projectile);
			Main.addAnimationToPane(animation.getNode());

			// When the animation finishes, hide it and remove it
			animation.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
					Main.removeAnimationFromPane(finishedAnimation.getNode());
				}
			});
			if(projectile instanceof LavaRock) lavaRockSound.play();
			if(projectile instanceof Arrow) arrowSound.play();
			animation.play();
		}
		Tower.getProjectileList().clear();
	}
	public static void waveManage(GameLogic logic, long now) {
		lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
		if(now - lastTimeTriggered >= 1000000000) {//1 second is past
			timer++;
			lastTimeTriggered = now;
			generateMonster(logic);
		}if(timer >= waveTime) {
			wave++;
			if(wave == 1) waveTime = 10;
			timer = 0;
		}if(isGameOver && !isGameEnd) {
			isLose = true;
			gameOverSound.play();
			isGameEnd = true;
			System.out.println("You Lose");
		}
		if(wave >= 10 && !isGameOver && isGameClear() && !isGameEnd) {
			isWin = true;
			Main.backgroundSound.stop();
			winningSound.play();
			System.out.println("You Win!");
			isGameEnd = true;
		}
	}
	public static void generateMonster(GameLogic logic) {
		if(1 <= wave && wave <= 4) { //knight 10 unit/wave : 1 unit/sec
			if(timer == 1) waveTime = 10;
			logic.addNewObject(new Knight());
		}if(wave == 5) {
			if(timer <= 5) { //knight 5 unit : 1 unit/sec
				logic.addNewObject(new Knight());
			}if(timer == 1) { //add just one ShovelKnight that is the first Boss!
				logic.addNewObject(new ShovelKnight());
				waveTime = 20;
			}
		}if(6 <= wave && wave <= 9) { //10 knight with 4X HP
			if(timer == 1) {
				waveTime = 10;
			}Entity e = new Knight();
			e.setHealthPoints(e.getHealthPoints() * 4);
			logic.addNewObject(e);
		}
		if(wave == 10) {// last wave
			if(timer == 1) {
				waveTime = 23;
				logic.addNewObject(new Knight());
				logic.addNewObject(new ShovelKnight());
				logic.addNewObject(new KingKnight());
			}
		}
	}
	private static boolean isGameClear() {
		int entityCount = 0;
		for(int i = 0; i < GameLogic.gameObjectContainer.size(); i++) {
			if(GameLogic.gameObjectContainer.get(i) instanceof Entity) entityCount++;
		}return entityCount == 0;
	}
	
	public static boolean isGameOver() {
		return isGameOver;
	}
	
	public static boolean isGameEnd() {
		return isGameEnd;
	}

	public static boolean isWin() {
		return isWin;
	}

	public static boolean isLose() {
		return isLose;
	}

}
