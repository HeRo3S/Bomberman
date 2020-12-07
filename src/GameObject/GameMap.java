package GameObject;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Math.*;

public class GameMap implements Serializable {
    /**
     * Note: map size must be divisible by chunk.
     */
    public static final int MAP_WIDTH = 1024 ;
    public static final int MAP_HEIGHT = 576 ;
    public static final int CHUNK_SIZE = 128;
    public static final int TILE_SIZE = 32;
    private ArrayList<ArrayList<ArrayList<Entity>>> map = new ArrayList<>();
    private ArrayList<ArrayList<Entity>> renderMap = new ArrayList<>();
    public static int getMapWidth() {
        return MAP_WIDTH;
    }

    public static int getMapHeight() {
        return MAP_HEIGHT;
    }

    public GameMap(){
            for(int i = 0; i < ceil(MAP_WIDTH/ TILE_SIZE); i++){
                map.add(new ArrayList<>());
                for(int j = 0; j < ceil(MAP_HEIGHT/ TILE_SIZE); j++){
                    map.get(i).add(new ArrayList<>());
                }
            }
            for(int i = 0; i <= 3; i ++){
                renderMap.add(new ArrayList<>());
            }
        }
    public HashSet<Entity> getContent(double x_, double y_, double range_){
        int range = (int) ceil(range_/ TILE_SIZE);
        int x = (int) (x_ / TILE_SIZE);
        int y = (int) (y_ / TILE_SIZE);
        int leftBound = max(min(x - range, MAP_WIDTH/ TILE_SIZE - 1), 0);
        int rightBound = max(min(x + range, MAP_WIDTH/ TILE_SIZE - 1), 0);
        int upperBound = max(min(y - range, MAP_HEIGHT/ TILE_SIZE - 1), 0);
        int lowerBound = max(min(y + range, MAP_HEIGHT/ TILE_SIZE - 1), 0);
        HashSet<Entity> result = new HashSet<>();
        for(int i = leftBound; i <= rightBound; i++){
            for(int j = upperBound; j <= lowerBound; j++){
                result.addAll(map.get(i).get(j));
            }
        }
        return result;
    }
    public void addContent(double x_, double y_, Entity entity){
        int x = (int) (x_ / TILE_SIZE);
        int y = (int) (y_ / TILE_SIZE);
        x = max(min(x, MAP_WIDTH/ TILE_SIZE - 1), 0);
        y = max(min(y, MAP_HEIGHT/ TILE_SIZE - 1), 0);
        if(entity.getMap() == this) {
            map.get(x).get(y).add(entity);
            renderMap.get(entity.getLayer()).add(entity);
        }
        else {
            System.out.println("Warning: trying to add entities to another map without removing first");
        }
    }
    public void removeContent(double x_, double y_, Entity entity){
        int x = (int) (x_ / TILE_SIZE);
        int y = (int) (y_ / TILE_SIZE);
        x = max(min(x, MAP_WIDTH/ TILE_SIZE - 1), 0);
        y = max(min(y, MAP_HEIGHT/ TILE_SIZE - 1), 0);
        renderMap.get(entity.getLayer()).remove(entity);
        map.get(x).get(y).remove(entity);
    }
    public void moveContent(double x_, double y_, Entity entity){
        int x = (int) (entity.getX() / TILE_SIZE);
        int y = (int) (entity.getY() / TILE_SIZE);
        x = max(min(x, MAP_WIDTH/ TILE_SIZE - 1), 0);
        y = max(min(y, MAP_HEIGHT/ TILE_SIZE - 1), 0);
        map.get(x).get(y).remove(entity);
        addContentSpc(x_, y_, entity);
    }
    public void updateContent(){
        HashSet<Entity> lastMap = getContent(0,0,MAP_WIDTH);
        for(Entity entity : lastMap){
            entity.update();
        }
    }

    public void render(GraphicsContext gc, double time) {
        ArrayList<ArrayList<Entity>> render = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            render.add(new ArrayList<>());
        }
        for(int i = 0; i < map.size(); i++){
            for (int j = 0; j < map.get(i).size(); j++){
                for(int k = 0; k < map.get(i).get(j).size(); k++){
                    Entity temp = map.get(i).get(j).get(k);
                    render.get(temp.getLayer()).add(temp);
                }
            }
        }
        for(int i = 0; i < 10; i++){
            for(Entity entity : render.get(i)){
                entity.animate(gc,time);
            }
        }
    }
    private void addContentSpc(double x_, double y_, Entity entity){
        int x = (int) (x_ / TILE_SIZE);
        int y = (int) (y_ / TILE_SIZE);
        x = max(min(x, MAP_WIDTH/ TILE_SIZE - 1), 0);
        y = max(min(y, MAP_HEIGHT/ TILE_SIZE - 1), 0);
        if(entity.getMap() == this) {
            map.get(x).get(y).add(entity);
        }
        else {
            System.out.println("Warning: trying to add entities to another map without removing first");
        }
    }
}
