package GameObject;

public class Fire extends Floor {
    public Fire(double x, double y, GameMap map, int row, int column) {
        super(x, y, map, row, column);
        health = 200;
        code = SpriteSheetCode.FIRE;
        setHitBox(0,0,32,32);
        row = 0;
        column = 0;
    }
    public void update(){
        if(--health <= 0){
            map.removeContent(x,y,this);
        }
        for(Entity entity : map.getContent(x,y,1)){
            if(entity instanceof Destructible && entity.getHitBox().intersects(getHitBox())){
                if (entity.status.getBurningTimer() < 80) {
                    if(!entity.status.isBurning()) {
                        entity.health -= 20;
                    }
                    entity.status.setBurningTimer(80);
                }
            }
        }
    }
}

