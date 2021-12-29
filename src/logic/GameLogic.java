package logic;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameLogic {
	public static List<IRenderable> gameObjectContainer = new ArrayList<IRenderable>();//make change in this list
	
	public void addNewObject(IRenderable iRenderable){
		gameObjectContainer.add(iRenderable);
		RenderableHolder.getInstance().add(iRenderable);
	}
	public void logicUpdate() {
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			if (gameObjectContainer.get(i).isDestroyed()) {
				if(gameObjectContainer.get(i) instanceof Entity && !((Entity)gameObjectContainer.get(i)).isPathFinished()) 
					{SimulationManager.receiveReward(((Entity)(gameObjectContainer.get(i))).getReward());}
				gameObjectContainer.remove(i);
				System.out.println("remove");
			}
		}
	}public void updateLocation(int[][] map) {
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			if(!gameObjectContainer.get(i).isDestroyed() && gameObjectContainer.get(i) instanceof Entity)
			((Entity)gameObjectContainer.get(i)).walk(map);
		}
	}
}
