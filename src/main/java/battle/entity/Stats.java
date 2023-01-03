package battle.entity;

public class Stats {
    private int maxHP;
    private int maxSP;
    private int attack;
    private int defense;
    private int speed;
    
    public Stats(int maxHP, int maxSP, int attack, int defense, int speed){
        this.maxHP = maxHP;
        this.maxSP = maxSP;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public int getMaxHP(){
        return maxHP;
    }
    public int getMaxSP(){
        return maxSP;
    }
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }
    public int getSpeed(){
        return speed;
    }

}
