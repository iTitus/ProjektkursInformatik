package projektkurs.simulation.tower;

import java.util.Arrays;

public enum TowerType {

    SIMPLE_SHOOTER("Simple Shooter", 1, new int[] { 2 }, new int[] { 10 }, new double[] { 2.5 }, new double[] { 0.25 }, new int[] { 2 }, new int[] { 0xFF0000 });

    private final int maxTier;
    private final String name;
    private final double[] range, speed;
    private final int[] upgradeCost, frequency, damage, color;

    private TowerType(String name, int maxTier, int[] upgradeCost, int[] frequency, double[] range, double[] speed, int[] damage, int[] color) {
        if (range.length != maxTier || frequency.length != maxTier || upgradeCost.length != maxTier || color.length != maxTier || speed.length != maxTier) {
            throw new IllegalArgumentException("Pascht nischt un wackelt genausowenich un hätt uch kenne Luft");
        }
        this.name = name;
        this.maxTier = maxTier;
        this.upgradeCost = Arrays.copyOf(upgradeCost, maxTier);
        this.frequency = Arrays.copyOf(frequency, maxTier);
        this.range = Arrays.copyOf(range, maxTier);
        this.speed = Arrays.copyOf(speed, maxTier);
        this.damage = Arrays.copyOf(damage, maxTier);
        this.color = Arrays.copyOf(color, maxTier);
    }

    public int getColor(int tier) {
        return color[tier - 1];
    }

    public int getConstructionCost() {
        return getUpgradeCost(0);
    }

    public int getDamage(int tier) {
        return damage[tier - 1];
    }

    public int getFrequency(int tier) {
        return frequency[tier - 1];
    }

    public int getMaxTier() {
        return maxTier;
    }

    public String getName() {
        return name;
    }

    public double getRange(int tier) {
        return range[tier - 1];
    }

    public double getSpeed(int tier) {
        return speed[tier - 1];
    }

    public int getUpgradeCost(int tier) {
        return upgradeCost[tier];
    }

}
