package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.MapCreator;
import WizardTD.Tiles.*;
public class MonsterPathReader {

    public boolean SightExpand(Path path) {

        return false; //placeholder
    }
    public void Read(Monster monster) {

        int monsterX = (monster.getX()-6)/32;
        int monsterY = (monster.getY()-6)/32;
        System.out.println(monsterX);
        System.out.println(monsterY);

        if (App.paths[monsterX][monsterY] != null){
            System.out.println(App.paths[monsterX][monsterY].isPath());
        }

        System.out.println(monster.currentDirection());
    }

}
