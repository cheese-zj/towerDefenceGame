package WizardTD.Helpers;

import WizardTD.App;
import WizardTD.Monsters.*;

import java.util.Objects;

public class MonsterCreator {



    public Monster[] CreateMonsters(int monsterAmount, int gridX, int gridY,
                                    float speed, int hp, float armour,int mana_gained_on_kill,
                                    int spawnTick, String type) {

        Monster[] monsters = new Monster[monsterAmount];

        for (int i=0; i<monsters.length; i++) {
            //Monster Co 6(center monster pic) + (gridnum)*App.CELLSIZE + App.TOPBAR
            monsters[i] =
                    new Monster(
                            (gridX)*(App.CELLSIZE),
                            (gridY)*(App.CELLSIZE),
                            speed, type, hp, armour, mana_gained_on_kill, (i*spawnTick)
                    );
            monsters[i].setSprite(App.gremlinpng);
            if (Objects.equals(type, "Gremlin")){
                monsters[i].setSprite(App.gremlinpng);
            }
        }
        return monsters;
    }
}
