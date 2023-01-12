package battle;

import battle.entity.characters.PartyMembers;
import battle.location.Location;
import battle.entity.*;

public class Main {
    public static void main(String[] args) throws Exception {
        PartyMembers orpheus = new PartyMembers("Orpheus", 1000, 50, 100, 100, 100);
        orpheus.addToActiveParty();

        Location desert = new Location("desert");

        Enemy blueSlime = new Enemy("Blue Slime", "blahblahblah", desert, 25, 200, 10, 50, 50, 100);
        Enemy greenSlime = new Enemy("Green Slime", "blahblahblah", desert, 25, 200, 10, 50, 50, 100);
        Enemy pinkSlime = new Enemy("Pink Slime", "blahblahblah", desert, 25, 200, 10, 50, 50, 100);
        Enemy redSlime = new Enemy("Red Slime", "blahblahblah", desert, 25, 200, 10, 50, 50, 100);

        desert.getEnemyList().add(blueSlime);
        desert.getEnemyList().add(greenSlime);
        desert.getEnemyList().add(pinkSlime);
        desert.getEnemyList().add(redSlime);

        Battle battle1 = new Battle(PartyMembers.activeParty, desert);
    }
}
