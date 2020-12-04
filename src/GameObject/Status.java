package GameObject;

import java.io.Serializable;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Status implements Serializable {
    enum currentStatus{
        CHANNEL,
        STUN,
        BURNING,
        ATTACK_CD,
    }
    private int channelTimer;
    private int stunTimer;
    private int burningTimer;
    private int attackTimer;
    public Status(){

    }
    public void add(currentStatus effect, double sec){
        switch (effect){
            case CHANNEL:
                channelTimer += sec * 60;
                break;
            case STUN:
                stunTimer += sec * 60;
                break;
            case BURNING:
                burningTimer += sec * 60;
                break;
            case ATTACK_CD:
                attackTimer += sec * 60;
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
    public boolean canAttack(){
        return attackTimer <= 0;
    }
    public void update(){
        burningTimer = max(--burningTimer,0);
        stunTimer = max(--stunTimer, 0);
        channelTimer = max(--channelTimer, 0);
        attackTimer = max(--attackTimer, 0);
    }

    public int getBurningTimer() {
        return burningTimer;
    }

    public void setBurningTimer(int burningTimer) {
        this.burningTimer = burningTimer;
    }
}
