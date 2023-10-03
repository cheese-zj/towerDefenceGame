package WizardTD;

import WizardTD.Monsters.*;
public class MonsterCreator {



    public Monster[] CreateMonsters() {

        Monster[] monsters = new Monster[4];

        for (int i=0; i<monsters.length; i++) {
            //Monster Co 6(center monster pic) + (gridnum)*App.CELLSIZE + App.TOPBAR
            monsters[i] = new Monster( (8)*(App.CELLSIZE), (19)*App.CELLSIZE, 2, "Gremlin", 100, 50, 5, i*12);
            monsters[i].setSprite(App.gremlinpng);
        }
        return monsters;
    }
}
