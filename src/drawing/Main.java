package drawing;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.SimulationManager;
import logic.TileMap;
import tower.*;

import button.InformationBar;
import button.ItemPane;



public class Main extends Application {
	private static final String image_path = ClassLoader.getSystemResource("Background.png").toString();
	private static final Image IMAGE = new Image(image_path);

    public final static int RESOLUTION_X = 40*20;//800
    public final static int RESOLUTION_Y = 40*14;//560
    static AudioClip buyingTowerSound = new AudioClip(ClassLoader.getSystemResource("audio/TowerSell.wav").toString());
    public static AudioClip backgroundSound = new AudioClip(ClassLoader.getSystemResource("audio/BackgroundMusic.wav").toString());
    static AudioClip menuSound = new AudioClip(ClassLoader.getSystemResource("audio/MenuSound.mp3").toString());
    
    static Pane pane = new Pane();
    @Override
    public void start(Stage stage){
        StackPane root = new StackPane();
        Scene scene = new Scene(root,RESOLUTION_X,RESOLUTION_Y);
        
        Canvas canvas = new Canvas(RESOLUTION_X,RESOLUTION_Y);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        String startPath = ClassLoader.getSystemResource("HomeScene.png").toString();
        gc.drawImage(new Image(startPath), 0,0 );
        
        HomeScene homeScene = new HomeScene();
        root.getChildren().add(homeScene);
        stage.setScene(scene);
        stage.setTitle("Tower Defense : The Legends of Tower");        
        stage.setResizable(false);
        stage.show();
        
        menuSound.setVolume(menuSound.getVolume()*0.6);
        menuSound.play();
        
        homeScene.start.setOnMouseClicked((MouseEvent e) -> {
        	menuSound.stop();
        	
        	LoseScene losePage = new LoseScene();
			losePage.exit.setOnMouseClicked((MouseEvent f) -> {
				stage.close();
			});
			
			WinScene winPage = new WinScene();
			winPage.exit.setOnMouseClicked((MouseEvent f) -> {
				stage.close();
			});
			
			
            ItemPane itemPane = new ItemPane();
            InformationBar informationBar = new InformationBar();
            
            GameLogic logic = new GameLogic();
            GameScreen gameScreen = new GameScreen(RESOLUTION_X,RESOLUTION_Y);
            GraphicsContext gc1 = gameScreen.getGraphicsContext2D();
        	TileMap map = new TileMap(RESOLUTION_X, RESOLUTION_Y, gc1);
        	
        	root.getChildren().remove(homeScene) ;
			root.getChildren().addAll(winPage,losePage, gameScreen);
            root.getChildren().add(pane);
            root.getChildren().add(informationBar);
            root.getChildren().add(itemPane);
			
			addListerner(root, map, logic);
	        backgroundSound.setPriority(5);
	        backgroundSound.setCycleCount(AudioClip.INDEFINITE);
	        backgroundSound.play();
	        
			AnimationTimer animation = new AnimationTimer() {
				public void handle(long now) {
					if(SimulationManager.isLose()) {
						root.getChildren().remove(gameScreen);
						root.getChildren().removeAll(pane,informationBar,itemPane);
						String losePath = ClassLoader.getSystemResource("LoseScene.png").toString();
				        gc.drawImage(new Image(losePath), 0,0 );
						gc.setFont(new Font("Serif Bold Italic", 40));
						gc.fillText("Wave : " + SimulationManager.getWave(), 10, 40);
						gc.setFont(new Font("Serif Bold Italic", 80));
						gc.fillText("You lose", 260, 220);
						losePage.exit.setVisible(true);
						backgroundSound.stop();
					}
					else if(SimulationManager.isWin()){
						root.getChildren().remove(gameScreen);
						root.getChildren().removeAll(pane,informationBar,itemPane,losePage);
						String winPath = ClassLoader.getSystemResource("WinScene.png").toString();
				        gc.drawImage(new Image(winPath), 0,0 );
						winPage.exit.setVisible(true);
						backgroundSound.stop();
					}
					else {
						map.pathPaint(gc1);
						drawBackground(gc1);
						SimulationManager.waveManage(logic, now);
						logic.updateLocation(map.getMap());
						SimulationManager.createProjectiles();
						logic.logicUpdate();
						gameScreen.paintComponent(gc1);
						SimulationManager.updateHealth();
						informationBar.InformationUpdate();
						
					}
				}
			};
			animation.start();
			
		});
        
        homeScene.exit.setOnMouseClicked((MouseEvent e) -> {
        	menuSound.stop();
			stage.close();
		});
        
        stage.setScene(scene);
        stage.setTitle("Tower Defense : The Legend of Tower");        
        stage.setResizable(false);
        stage.show();


    }
    
    public static void addAnimationToPane(Node node) {
    	pane.getChildren().add(node);
    }
    
    public static void removeAnimationFromPane(Node node) {
    	pane.getChildren().remove(node);
    }
    
    private void drawBackground(GraphicsContext gc) {
    	gc.drawImage(IMAGE, 0, 0);
    }
    
    public static void removeFromPane(ImageView imageview) {
        pane.getChildren().remove(imageview);
    }
    
    public static void addToPane(ImageView imageview) {
        pane.getChildren().add(imageview);
    }
    
    public static void relocate(double x, double y, ImageView im) {
        im.relocate(x, y);
    }
	
    public void addListerner(Pane root, TileMap map, GameLogic logic) {
		root.setOnMouseClicked((MouseEvent event)->{
			int idxX = (int)(event.getSceneX() / TileMap.TILE_LENGTH_X);
			int idxY = (int)(event.getSceneY() / TileMap.TILE_LENGTH_Y);
			int pixelX = idxX * TileMap.TILE_LENGTH_X;
			int pixelY = idxY * TileMap.TILE_LENGTH_Y;
        	if(map.getMap()[idxY][idxX] == TileMap.TURRET_PLACE && SimulationManager.selectedItemButton != null) {
        		if(SimulationManager.getMoney() >= SimulationManager.getSelectedItemButton().getItem().getSellCost()) {
        			Tower tower;
        			if(SimulationManager.getSelectedItemButton().getItem() instanceof ArrowTower) {
            			tower = new ArrowTower(pixelX ,pixelY);
            		}else if(SimulationManager.getSelectedItemButton().getItem() instanceof RockTower){
            			tower = new RockTower(pixelX ,pixelY);
            		}else {
            			return;
            		}SimulationManager.reduceMoneyBuyTower();
            		buyingTowerSound.play();
            		logic.addNewObject(tower);
            		map.setMapNode(idxX, idxY, TileMap.TOWER_TILE);
        		}
        	}
        });
	}public void stop() throws Exception {
		// TODO Auto-generated method stub
		menuSound.stop();
		backgroundSound.stop();
	}

    public static void main(String[] args) {
        launch(args);
    }
}
