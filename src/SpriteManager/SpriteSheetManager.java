package SpriteManager;

import GameObject.SpriteSheetCode;

import java.util.HashMap;
import java.util.Map;

import static GameObject.SpriteSheetCode.*;

public class SpriteSheetManager {
    private static Map<SpriteSheetCode,SpriteSheet> storage = new HashMap<>();
    public static SpriteSheet getSheet(SpriteSheetCode code){
        return storage.get(code);
    }
    public static void initialize(){
        storage.put(GREEN,new SpriteSheet("GameObject/assets/mcSheet.png",5,8));
        storage.put(WISP,new SpriteSheet("GameObject/assets/wispSheet.png",3,8));
        storage.put(HEALTH_ORB,new SpriteSheet("GameObject/assets/healthOrb.png",1,1));
        storage.put(ENERGY_ORB,new SpriteSheet("GameObject/assets/energyOrb.png",1,1));
        storage.put(BASIC_RUNE, new SpriteSheet("GameObject/assets/basic_rune_sheet.png",1,4));
        storage.put(PHANTOM, new SpriteSheet("GameObject/assets/phantomSheet.png",2,8));
        storage.put(THROWER, new SpriteSheet("GameObject/assets/orcSheet.png",2,8));
        storage.put(FLOOR, new SpriteSheet("GameObject/assets/floor.png", 2, 4));
        storage.put(CRATE, new SpriteSheet("GameObject/assets/crate.png", 1, 1));
        storage.put(GATE, new SpriteSheet("GameObject/assets/gate.png", 3, 8));
        storage.put(WALL, new SpriteSheet("GameObject/assets/wall.png", 4, 13));
        storage.put(BRUTE, new SpriteSheet("GameObject/assets/bruteSheet.png",2,8));
        storage.put(SPEAR, new SpriteSheet("GameObject/assets/spear.png",1,1));
        storage.put(FIRE,new SpriteSheet("GameObject/assets/fire.png",1,4));
    }
}
