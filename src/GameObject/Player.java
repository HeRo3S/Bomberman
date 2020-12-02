package GameObject;

public abstract class Player extends Movable {
    protected double energy;
    protected double maxEnergy = 200;
    protected double energyRegen = 10;
    protected double heathRegen = 0;
    public Player(double x, double y, GameMap map) {
        super(x, y, map);
        energy = maxEnergy;
        setHitBox(0,0,32,32);
    }

    public void regen(){
        health += heathRegen;
        energy += energyRegen;
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
