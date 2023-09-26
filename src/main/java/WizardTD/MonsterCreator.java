package WizardTD;

import WizardTD.Monsters.*;
public class MonsterCreator {



    public Monster[] CreateMonsters() {

        Monster[] monsters = new Monster[1];

        for (int i=0; i<monsters.length; i++) {
            //Monster Co 6(center monster pic) + (gridnum)*32 + 40
            monsters[i] = new Monster(6+(0)*(32), 6+(3)*32+40, 1, "Gremlin", 100, 50, 5, (i+1)*12);
            monsters[i].setSprite(App.gremlinpng);
        }
        return monsters;
    }
}
