package WizardTD.Managers;

import WizardTD.App;
import WizardTD.Monsters.*;

import java.util.Objects;

public class MonsterCreator {



    public Monster[] CreateMonsters(int monsterAmount, String type) {

        Monster[] monsters = new Monster[monsterAmount];

        for (int i=0; i<monsters.length; i++) {
            //Monster Co 6(center monster pic) + (gridnum)*App.CELLSIZE + App.TOPBAR
            monsters[i] = new Monster( (6)*(App.CELLSIZE), (19)*App.CELLSIZE, 4, type, 100, 50, 5, i*12);
            monsters[i].setSprite(App.gremlinpng);
            if (Objects.equals(type, "Gremlin")){
                monsters[i].setSprite(App.gremlinpng);
            }
        }
        return monsters;
    }
}
