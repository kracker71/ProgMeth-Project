package service;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import game.engine.GameState;
import game.engine.characters.Monster;
import game.object.*;

public class TowerAttackService {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ScheduledFuture<?> pollHandler;
	private TowerBase tower;
	private Monster target;
	
	public TowerAttackerService(Tower tower) {
		this.tower = tower;
	}
	
	public void pollTower(int delay) {
		final Runnable task = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int towerMinXRange = tower.getX() - tower.getAttackRange();
                int towerMaxXRange = tower.getX() + tower.getAttackRange();
                int towerMinYRange = tower.getY() - tower.getAttackRange();
                int towerMaxYRange = tower.getY() + tower.getAttackRange();
                Iterator<Monster> iterator = GameState.getGame().getMonstersAlive().iterator();
                
                while (iterator.hasNext()) {
                	target = iterator.next();
                	if (target.getX() > towerMinXRange
                            & target.getX() < towerMaxXRange
                            & target.getY() > towerMinYRange
                            & target.getY() < towerMaxYRange) {
                        tower.createProjectile(target);
                        target.takeDamage(tower.getAttackDamage());
                        break;
                    }
                }
			}
			
		};//end Runnable
		
		pollHandler = scheduler.scheduleWithFixedDelay(task , delay , 1000 , TimeUnit.MILLISECONDS);
	}
	
	public void cancel() {
		pollHandler.cancel(true);
	}
	
}
