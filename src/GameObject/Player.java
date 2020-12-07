package GameObject;

import static java.lang.Math.min;

public abstract class Player extends Movable implements Regen{
    protected double energy;
    protected double maxEnergy = 200;
    protected double energyRegen = 20;
    protected double heathRegen = 10;
    private int regenTimer = regenDelay;
    public Player(double x, double y, GameMap map) {
        super(x, y, map);
        energy = maxEnergy;
        setHitBox(0,0,32,32);
    }

    public void regen(){
        regenTimer --;
        if(regenTimer <= 0 && !status.isBurning()){
            modifyHealth(heathRegen);
            modifyEnergy(energyRegen);
            regenTimer = regenDelay;
        }
    }
    public void modifyEnergy(double amount){
        energy = min(maxEnergy,energy + amount);
    }
    /**
     * Getter/Setter
     */
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(double maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public double getEnergyRegen() {
        return energyRegen;
    }

    public void setEnergyRegen(double energyRegen) {
        this.energyRegen = energyRegen;
    }

    public double getHeathRegen() {
        return heathRegen;
    }

    public void setHeathRegen(double heathRegen) {
        this.heathRegen = heathRegen;
    }
}
