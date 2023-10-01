package WizardTD;

import WizardTD.Monsters.*;
public class MonsterCreator {



    public Monster[] CreateMonsters() {

        Monster[] monsters = new Monster[2];

        for (int i=0; i<monsters.length; i++) {
            //Monster Co 6(center monster pic) + (gridnum)*App.CELLSIZE + App.TOPBAR
            monsters[i] = new Monster( 6+(0)*(App.CELLSIZE), 6+(3)*App.CELLSIZE+App.TOPBAR, 2, "Gremlin", 100, 50, 5, i*6);
            monsters[i].setSprite(App.gremlinpng);
        }
        return monsters;
    }
}
