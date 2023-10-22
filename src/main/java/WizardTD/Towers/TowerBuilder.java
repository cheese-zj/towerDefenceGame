package WizardTD.Towers;

import WizardTD.App;
import WizardTD.GameSys.ManaBar;

public class TowerBuilder implements costCheck {

    public static int towerRangeLv;
    public static int towerFireLv;
    public static int towerDmgLv;
    public static int buildCost = App.json.getInt("tower_cost");

    public TowerBuilder() {
    }

    //Building the Tower: check the mana condition and react to that.
    public void BuildTower(int gridX, int gridY) {

        towerRangeLv = 0;
        towerFireLv = 0;
        towerDmgLv = 0;

        if (ManaBar.mana > buildCost) {
            Tower newTower = new Tower(gridX, gridY, towerRangeLv, towerFireLv, towerDmgLv);
            towerCostManage(newTower);
            ManaBar.mana -= (buildCost);
            towerConsume(newTower);
            App.towers.add(newTower);
            App.grasses[gridX][gridY].setOccupied(true);
        }
    }
}
