package GameObject;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.*;

public class GameMap implements Serializable {
    /**
     * Note: map size must be divisible by chunk.
     */
    public static final int MAP_WIDTH = 1280;
    public static final int MAP_HEIGHT = 720;
    public static final int CHUNK_SIZE = 80;
    public ArrayList<ArrayList<ArrayList<Entity>>> map = new ArrayList<>();

    private Canvas canvas;
    private GraphicsContext gc;

    public GameMap(){
            for(int i = 0; i < MAP_WIDTH/ CHUNK_SIZE; i++){
                map.add(new ArrayList<>());
                for(int j = 0; j < MAP_HEIGHT/ CHUNK_SIZE; j++){
                    map.get(i).add(new ArrayList<>());
                }
            }
            canvas = new Canvas(MAP_WIDTH, MAP_HEIGHT);
            gc = canvas.getGraphicsContext2D();
        }
    public ArrayList<Entity> getContent(double x_, double y_, double range_){
        int range = (int) ceil(range_/CHUNK_SIZE);
        int x = (int) (x_ / CHUNK_SIZE);
        int y = (int) (y_ / CHUNK_SIZE);
        //Bound x and y
        range = max(min(range,MAP_WIDTH/CHUNK_SIZE/2),0);
        x = max(min(x, MAP_WIDTH/CHUNK_SIZE - range - 1), range);
        y = max(min(y, MAP_HEIGHT/CHUNK_SIZE - range - 1), range);
        ArrayList<Entity> result = new ArrayList<>();
        for(int i = x - range; i < x + range; i++){
            if(i < 0){
                break;
            }
            for(int j = y - range; j < y + range; j++){
                if(j < 0 || j >= MAP_HEIGHT/CHUNK_SIZE){
                    break;
                }
                result.addAll(map.get(i).get(j));
            }
        }
        return result;
    }
    public void addContent(double x_, double y_, Entity entity){
        int x = (int) (x_ / CHUNK_SIZE);
        int y = (int) (y_ / CHUNK_SIZE);
        x = max(min(x, MAP_WIDTH/CHUNK_SIZE - 1), 0);
        y = max(min(y, MAP_HEIGHT/CHUNK_SIZE - 1), 0);
        if(entity.getMap() == this) {
            map.get(x).get(y).add(entity);
            entity.setMap(this);
        }
        else {
            System.out.println("Warning: trying to add entities to another map without removing first");
        }
    }
    public void removeContent(double x_, double y_, Entity entity){
        int x = (int) (x_ / CHUNK_SIZE);
        int y = (int) (y_ / CHUNK_SIZE);
        x = max(min(x, MAP_WIDTH/CHUNK_SIZE - 1), 0);
        y = max(min(y, MAP_HEIGHT/CHUNK_SIZE - 1), 0);
        map.get(x).get(y).remove(entity);
    }
    public void updateContent(){
        for(int i = 0; i < map.size(); i++){
            for (int j = 0; j < map.get(i).size(); j++){
                for(int k = 0; k < map.get(i).get(j).size(); k++){
                    map.get(i).get(j).get(k).update();
                }
            }
        }
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void render(double time) {
        gc.clearRect(0 ,0, MAP_WIDTH, MAP_HEIGHT);
        for(int i = 0; i < map.size(); i++){
            for (int j = 0; j < map.get(i).size(); j++){
                for(int k = 0; k < map.get(i).get(j).size(); k++){
                    map.get(i).get(j).get(k).animate(gc, time);
                }
            }
        }
    }
}
