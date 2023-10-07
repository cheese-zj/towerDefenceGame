package WizardTD.Towers;

import WizardTD.App;
import org.checkerframework.checker.units.qual.A;
import processing.core.PImage;

public class TowerBuilder {


    public TowerBuilder(){
    }

    private PImage TowerImageIdentifier(int rangeLv, int fireSpeedLv, int dmgLv) {
        if (rangeLv == 2 && fireSpeedLv == 2 && dmgLv == 2){
            return App.tower2png;
        } else {
            return App.tower0png;
        }
    }
    public void BuildTower(int gridX, int gridY) {
        //System.out.println(gridX + " " + gridY);
        //System.out.println("Test TowerBuilder method Build Tower");
        Tower newTower = new Tower(gridX, gridY, 100,0.1,5);
        newTower.setSprite(TowerImageIdentifier(2,2,2));
        App.towers.add(newTower);
    }

}
