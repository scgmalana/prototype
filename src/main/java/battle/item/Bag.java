package battle.item;

import java.util.*;

import battle.item.equipment.Equipment;

public class Bag {
    private static final HashMap<String, Item> itemList = new HashMap<>();
    private static final HashMap<String, Equipment> equipmentList = new HashMap<>();
    private static final HashMap<String, Consumables> consumableList =  new HashMap<>();

    public static Map<String, Item> getItemList() {
        return itemList;
    }
    public static Map<String, Equipment> getEquipmentList() {
        return equipmentList;
    }
    public static Map<String, Consumables> getConsumableList() {
        return consumableList;
    }

    private Bag(){
        /*
         * Bag exists to hold the every item list, doesn't really have its own things that need to be created
         */
    }

    public void pickUpItem(Item targetItem){
        Bag.itemList.put(targetItem.getName(), targetItem);
    }
    public void pickUpEquipment(Equipment targetEquipment){
        Bag.equipmentList.put(targetEquipment.getName(), targetEquipment);
    }
}
