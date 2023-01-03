package battle;

import java.util.*;
import battle.entity.*;
import battle.entity.Party;
import battle.entity.characters.*;
import battle.location.Location;

public class Battle {
    public Battle(Party playerParty, Location battleLocation){
        boolean battleFinished = false;
        Location.EnemyList<Enemy> locationEnemyList = battleLocation.generateEnemyList();
        Party enemyParty = new Party(4);
        ArrayList<Entity> battleEntities = new ArrayList<>();
        
        for(int index = 0 ; index <5; index++){
            Enemy enemy = locationEnemyList.next();
            enemyParty.getPartyList().add(enemy);
        }

        for(Entity entity: playerParty.getPartyList()){
            battleEntities.add(entity);
        }
        for(Entity entity: enemyParty.getPartyList()){
            battleEntities.add(entity);
        }

        do{
            turn(battleEntities, playerParty, enemyParty);
        }
        while(!battleFinished);
    }

    private void speedSort(ArrayList<Entity> battleEntities){
        Map<Integer, Entity> speedMap = new HashMap<Integer, Entity>();
        int index = 0;
        for(Entity entity: battleEntities){
            speedMap.put(entity.getEffectiveStats().getSpeed(), entity);
        }
        Map<Integer, Entity> sortedSpeedMap = new TreeMap<Integer, Entity>(
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2){
                    return o2.compareTo(o1);
                }
            }
        );

        for(Entity entity: sortedSpeedMap.values()){
            battleEntities.set(index, entity);
            index++;
        }
    }

    private void decisionMaking(Entity actingEntity){
        System.out.println("What do you want to do?");
    }

    private void turn(ArrayList<Entity> battleEntities, Party playerParty, Party enemyParty){
        speedSort(battleEntities);
        for(int index = 0; index <= battleEntities.size(); index++){
                Entity actingEntity = battleEntities.get(index);
                if(actingEntity instanceof PartyMembers){
                    decisionMaking(actingEntity);
                }
                else{
                    actingEntity.attack(actingEntity.selectTarget(playerParty));
                }
        }
    }
}
