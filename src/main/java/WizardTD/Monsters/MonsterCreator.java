package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Monsters.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static WizardTD.Helpers.MapCreator.spawnPoints;

public class MonsterCreator {

    public static int[] getRandomCoordinates(ArrayList<String> spawnPoints) {
        // Get a random element from the list
        Random rand = new Random();
        String randomCoord = spawnPoints.get(rand.nextInt(spawnPoints.size()));

        // Split the string into X and Y components
        String[] splitCoords = randomCoord.split(" ");
        int x = Integer.parseInt(splitCoords[0]);
        int y = Integer.parseInt(splitCoords[1]);
        //System.out.println(x + " " + y);
        return new int[]{x, y};
    }

    public Monster[] CreateMonsters(int monsterAmount,
                                             float speed, int hp, float armour, int mana_gained_on_kill,
                                             int spawnTick, float preWavePause, String type) {

        Monster[] monsters = new Monster[monsterAmount];

        for (int i=0; i<monsters.length; i++) {

            int posFixSpawnX = 0, posFixSpawnY = 0;
            int[] coordinates = getRandomCoordinates(spawnPoints);
            int gridX = coordinates[0];
            int gridY = coordinates[1];
            {
                if (gridX == 0) posFixSpawnX = -31;
                else if (gridX == 19) posFixSpawnX = 31;
                else if (gridY == 0) posFixSpawnY = -31;
                else if (gridY == 19) posFixSpawnY = 31;
            }

            Monster newMonster = new Monster(
                    (gridX)*(App.CELLSIZE) + posFixSpawnX,
                    (gridY)*(App.CELLSIZE) + posFixSpawnY,
                    speed, type, hp, armour, mana_gained_on_kill, (i*spawnTick)+preWavePause*60);
            newMonster.setSprite(App.gremlinpng);
            if (Objects.equals(type, "gremlin")) newMonster.setSprite(App.gremlinpng);
            else if (Objects.equals(type, "worm")) newMonster.setSprite(App.wormpng);
            else if (Objects.equals(type, "beetle")) newMonster.setSprite(App.beetlepng);
            {
                if (gridY == 0) newMonster.goSouth();
                if (gridY == 19) newMonster.goNorth();
                if (gridX == 0) newMonster.goEast();
                if (gridX == 19) newMonster.goWest();
            }
            monsters[i] = (newMonster);
        }
        return monsters;
    }
}
