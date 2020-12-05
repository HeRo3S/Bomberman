package GameObject;

public class Fire extends Floor {
    int frame = 20;
    public Fire(double x, double y, GameMap map, int row, int column) {
        super(x, y, map, row, column);
        health = 200;
        code = SpriteSheetCode.FIRE;
        setHitBox(1,1,30,30);
        row = 0;
        column = 0;
    }
    public void update(){
        frame --;
        if(frame <= 0){
            row++;
            if(row > 3){
                row = 0;
            }
            frame = 25;
        }
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

