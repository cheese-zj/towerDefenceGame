package WizardTD.Towers;

import WizardTD.App;
import WizardTD.GameSys.ManaBar;

/**
 * Handles the creation and management of towers in the WizardTD game.
 * <p>
 * The TowerBuilder class offers functionalities related to building towers on the game grid.
 * It checks if the player has enough mana to build a tower and, if so,
 * deducts the required amount and places the tower on the specified grid cell.
 * </p>
 */
public class TowerBuilder implements costCheck {

    public static int towerRangeLv;
    public static int towerFireLv;
    public static int towerDmgLv;
    public static int buildCost = App.json.getInt("tower_cost");

    /**
     * Attempts to build a tower on the game grid at the specified cell coordinates.
     * <p>
     * Checks if the player has enough mana to build a tower and selected upgrades. If sufficient mana is available,
     * it creates a new tower, manages its costs, deducts the required mana, and places the tower
     * on the specified grid cell.
     * </p>
     *
     * @param gridX The x-coordinate of the grid cell where the tower is to be placed.
     * @param gridY The y-coordinate of the grid cell where the tower is to be placed.
     */
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
