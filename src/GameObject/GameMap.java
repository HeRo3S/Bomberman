package GameObject;

import org.omg.IOP.ENCODING_CDR_ENCAPS;

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
    public GameMap(){
            for(int i = 0; i < MAP_WIDTH/ CHUNK_SIZE; i++){
                map.add(new ArrayList<>());
                for(int j = 0; j < MAP_HEIGHT/ CHUNK_SIZE; j++){
                    map.get(i).add(new ArrayList<>());
                }
            }
        }
    public ArrayList<Entity> getContent(double x_, double y_, double range_){
        int range = (int) ceil(range_/CHUNK_SIZE);
        int x = (int) (x_ / CHUNK_SIZE);
        int y = (int) (y_ / CHUNK_SIZE);
        //Bound x and y
        x = max(min(x, MAP_WIDTH - range), range);
        y = max(min(y, MAP_HEIGHT - range), range);
        ArrayList<Entity> result = new ArrayList<>();
        for(int i = x - range; i <= x + range; i++){
            for(int j = y -range; j <= y + range; j++){
                result.addAll(map.get(i).get(j));
            }
        }
        return result;
    }
    public void addContent(double x_, double y_, Entity entity){
        int x = (int) (x_ / CHUNK_SIZE);
        int y = (int) (y_ / CHUNK_SIZE);
        map.get(x).get(y).add(entity);
    }
    public void removeContent(double x_, double y_, Entity entity){
        int x = (int) (x_ / CHUNK_SIZE);
        int y = (int) (y_ / CHUNK_SIZE);
        map.get(x).get(y).remove(entity);
    }
}
