package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Monsters.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static WizardTD.Helpers.MapCreator.spawnPoints;

/**
 * This class provides utility for creating and spawning monsters in the game.
 * <p>
 * It helps in determining random spawn points for the monsters and also initializes them
 * with their respective sprites and starting directions based on their spawn points.
 * </p>
 */
public class MonsterCreator {

    /**
     * Returns random spawn coordinates from a list of available spawn points.
     *
     * @param spawnPoints An ArrayList containing string representations of spawn points.
     * @return An array of two integers representing x and y coordinates.
     */
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
    /**
     * Creates an array of monsters based on the provided specifications.
     * <p>
     * The monsters are created at random spawn points and initialized with a sprite based on their type.
     * Their starting directions are also determined based on their spawn points.
     * </p>
     *
     * @param monsterAmount     The number of monsters to create.
     * @param speed             The speed of the monsters.
     * @param hp                The health points of the monsters.
     * @param armour            The armor value for the monsters.
     * @param mana_gained_on_kill The amount of mana gained upon killing the monsters.
     * @param spawnTick         The tick interval for spawning the monsters.
     * @param preWavePause      The pause before the wave of monsters starts spawning.
     * @param type              The type of the monsters (e.g., "gremlin", "worm", "beetle").
     * @return An array of created Monster objects.
     */
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
