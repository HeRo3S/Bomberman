package GameObject;

import static jdk.nashorn.internal.objects.NativeMath.max;

public class Status {
    private int channelTimer;
    private int stunTimer;
    private int burningTimer;
    public Status(){

    }
    public void add(String effect, double sec){
        switch (effect){
            case "channel":
                channelTimer += sec * 60;
                break;
            case "stun":
                stunTimer += sec * 60;
                break;
            case "burning":
                burningTimer += sec * 60;
                break;
        }
    }
    public boolean isChannelling(){
        return channelTimer > 0;
    }
    public boolean isStunning(){
        return stunTimer > 0;
    }
    public boolean isBurning(){
        return burningTimer > 0;
    }
    public void update(){
        max(--burningTimer,0);
        max(--stunTimer, 0);
        max(--channelTimer, 0);

    }
}
