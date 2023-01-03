package battle.entity;

import java.util.*;

public class Party {
    private ArrayList<Entity> entityList;
    private int totalPartyHP;

    public int getTotalPartyHP() {
        return this.totalPartyHP;
    }
    public void setTotalPartyHP(int totalPartyHP) {
        this.totalPartyHP = totalPartyHP;
    }
    public ArrayList<Entity> getPartyList(){
        return entityList;
    }

    public Party(){
        entityList = new ArrayList<>();
    }

    public Party(int size){
        entityList = new ArrayList<>(size);
    }

    public void healParty(int amountHealed){
        for(Entity entity: entityList){
            entity.setCurrentHP(Entity.healthCalculation(amountHealed, entity));
        }
    }

    public void fullHealParty(){
        for(Entity entity: entityList){
            entity.setCurrentHP(entity.getTotalStats().getMaxHP());
        }
    }
}
