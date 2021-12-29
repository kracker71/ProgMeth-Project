package tower;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import entity.Entity;
import logic.GameLogic;


public class TowerAttackService {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ScheduledFuture<?> pollHandler;
	private Tower tower;
	private Entity target;
	
	public TowerAttackService(Tower tower) {
		this.tower = tower;
	}
	
	public void pollTower(int delay) {//Hit entity
		final Runnable task = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int towerMinXRange = tower.getPixelX() - tower.getAttackRange();
                int towerMaxXRange = tower.getPixelX() + tower.getAttackRange();
                int towerMinYRange = tower.getPixelY() - tower.getAttackRange();
                int towerMaxYRange = tower.getPixelY() + tower.getAttackRange();
                for(int i = 0;i < GameLogic.gameObjectContainer.size();i++) {
                	if(GameLogic.gameObjectContainer.get(i) instanceof Entity) {
                		target = (Entity) GameLogic.gameObjectContainer.get(i);
                		if (target.getPixelX() > towerMinXRange
                                & target.getPixelX() < towerMaxXRange
                                & target.getPixelY() > towerMinYRange
                                & target.getPixelY() < towerMaxYRange) {
                            tower.createProjectile(target);
                            target.takeDamage(tower.getAttackDamage());
                            break;
                        }
                		
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
