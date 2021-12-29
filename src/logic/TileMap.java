package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


//responsible for painting the map for the client and tracking nodes

public class TileMap{

    private int[][] map;
    public static final int TILE_LENGTH_X = 40;    //Length of tiles
    public static final int TILE_LENGTH_Y = 40;
    private final int NUMER_OF_TILE_X;
    private final int NUMER_OF_TILE_Y;
    public static final int BEGIN_X_IDX = 0;
    public static final int BEGIN_Y_IDX = 6;
    
    public static final int SIMPLE_GRASS = 0;
    public static final int TURRET_PLACE = 1;
    public static final int UP_VERTICAL_PATH = 2;
    public static final int DOWN_VERTICAL_PATH = 3;
    public static final int HORIZON_PATH = 4;
    public static final int EAST_TO_NORTH_PATH = 5;
    public static final int NORTH_TO_EAST_PATH = 6;
    public static final int SOUTH_TO_EAST_PATH = 7;
    public static final int EAST_TO_SOUTH_PATH = 8;
    public static final int TOWER_TILE = 9;
    public static final int GOAL_TILE = 10;
    
    public TileMap(int mapWidth , int mapHeight , GraphicsContext gc){
        NUMER_OF_TILE_X = (int) (mapWidth / TILE_LENGTH_X);
        NUMER_OF_TILE_Y = (int) (mapHeight / TILE_LENGTH_Y);
        map = generateMapArray();
        pathPaint(gc);
    }

    //Method paints the map using the given map array and tileset
    public void pathPaint(GraphicsContext gc){
        //reads map array to paint the path
        for(int x = 0; x <  NUMER_OF_TILE_X; x++){
            for(int y = 0; y <  NUMER_OF_TILE_Y; y++ ){
                switch(map[y][x]){
                    case SIMPLE_GRASS: //simple grass do nothing
                        break;
                    case TURRET_PLACE: //turret place able grass do nothing
                        break;
                    case UP_VERTICAL_PATH: //paint up vertical path
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                    case DOWN_VERTICAL_PATH: //paint down vertical path
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                    case HORIZON_PATH: //paint horizon path
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                    case EAST_TO_NORTH_PATH: //paint corner EAST TO NORTH
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                    case NORTH_TO_EAST_PATH: //paint corner NORTH TO EAST
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                    case SOUTH_TO_EAST_PATH: //paint corner SOUTH TO EAST
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                    case EAST_TO_SOUTH_PATH: //paint corner EAST TO SOUTH
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                    case TOWER_TILE: //tower tile 
                        break;
                    case GOAL_TILE: //goal tile
                    	gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(x * TILE_LENGTH_X, y * TILE_LENGTH_X,TILE_LENGTH_X, TILE_LENGTH_Y);
                        break;
                }
            }
        };
    }public int[][] getMap(){
    	return map;
    }
    private int[][] generateMapArray(){
        int[][] map;
        map = new int[][]
                {
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 1 , 6 , 4 , 4 , 4 , 8 , 1 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 0 , 0 , 0 , 0 },
                        {0 , 1 , 2 , 1 , 1 , 1 , 3 , 1 , 0 , 0 , 0 , 0 , 1 , 6 , 4 , 8 , 0 , 0 , 0 , 0 },
                        {1 , 1 , 2 , 1 , 0 , 1 , 3 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 3 , 0 , 0 , 0 , 0 },
                        {4 , 4 , 5 , 1 , 0 , 1 , 7 , 4 , 8 , 1 , 1 , 6 , 4 , 5 , 1 , 7 , 4 , 10 , 10 , 10 },
                        {1 , 1 , 1 , 1 , 0 , 1 , 1 , 1 , 3 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 7 , 4 , 4 , 5 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }
                };
        return map;
    }

    //sets the map node for the given coordinates to input value than repaints adjustment
    public void setMapNode(int xCord , int yCord , int updatedValue){
        map[yCord][xCord] = updatedValue;
    }
}
