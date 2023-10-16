package WizardTD.Towers;

import WizardTD.App;
import WizardTD.GameSys.*;

public class TowerBuilder {

    public static int towerRangeLv;
    public static int towerFireLv;
    public static int towerDmgLv;
    public static int buildCost = 100;

    //read the config file
    public TowerBuilder() {
    }

    public void BuildTower(int gridX, int gridY) {
        //System.out.println(gridX + " " + gridY);
        //System.out.println("Test TowerBuilder method Build Tower");
        towerRangeLv = 0;
        towerFireLv = 0;
        towerDmgLv = 0;

        if (ManaBar.mana > buildCost) {
            int rangeCost = 0;
            int fireCost = 0;
            int dmgCost = 0;
            if (U1.U1checked) rangeCost = (towerRangeLv + 2) * 10;
            if (U2.U2checked) fireCost = (towerFireLv + 2) * 10;
            if (U3.U3checked) dmgCost = (towerDmgLv + 2) * 10;
            ManaBar.mana -= (buildCost);
            if (ManaBar.mana > rangeCost && U1.U1checked) {
                towerRangeLv++;
                ManaBar.mana -= rangeCost;
            }
            if (ManaBar.mana > fireCost && U2.U2checked) {
                towerFireLv++;
                ManaBar.mana -= fireCost;
            }
            if (ManaBar.mana > dmgCost && U3.U3checked) {
                towerDmgLv++;
                ManaBar.mana -= dmgCost;
            }
            Tower newTower = new Tower(gridX, gridY, towerRangeLv, towerFireLv, towerDmgLv);
            App.towers.add(newTower);
            App.grasses[gridX][gridY].setOccupied(true);
        }
    }
}
