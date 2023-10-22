package WizardTD.Towers;

import WizardTD.GameSys.ButtonClasses.*;
import WizardTD.GameSys.ManaBar;

/**
 * Provides functionalities related to the management of tower costs and upgrades in the WizardTD game.
 * <p>
 * The costCheck interface offers default methods for calculating tower upgrade costs, managing
 * the costs based on selected upgrades, and consuming the in-game currency (mana) to implement
 * the desired tower upgrades.
 * </p>
 */
public interface costCheck {

    /**
     * Calculates the cost for a tower upgrade based on the current level and whether the upgrade is selected.
     *
     * @param checked Indicates if the upgrade is selected.
     * @param Lv The current level of the tower's attribute (Range, Fire speed, or Damage).
     * @return The computed upgrade cost.
     */
    default int costAdd(boolean checked, int Lv) {
        int Cost = 0;
        if (checked) Cost = (Lv+2)*10;
        return Cost;
    }

    /**
     * Manages and updates the upgrade costs for a specific tower.
     *
     * @param tower The tower for which the costs are being managed.
     */
    default void towerCostManage(Tower tower) {
        tower.rangeCost = 0;
        tower.rangeCost = costAdd(U1.U1checked, tower.towerRangeLv);
        tower.fireCost = 0;
        tower.fireCost = costAdd(U2.U2checked, tower.towerFireLv);
        tower.dmgCost = 0;
        tower.dmgCost = costAdd(U3.U3checked, tower.towerDmgLv);
    }

    /**
     * Consumes the required mana to implement the selected tower upgrades.
     * Upgrades the tower's attributes if enough mana is available and the upgrade is selected.
     *
     * @param tower The tower to be upgraded.
     */
    default void towerConsume(Tower tower) {
        if (ManaBar.mana > tower.rangeCost && U1.U1checked) {
            tower.towerRangeLv++; ManaBar.mana -= tower.rangeCost;}
        if (ManaBar.mana > tower.fireCost && U2.U2checked) {
            tower.towerFireLv++; ManaBar.mana -= tower.fireCost;}
        if (ManaBar.mana > tower.dmgCost && U3.U3checked) {
            tower.towerDmgLv++; ManaBar.mana -= tower.dmgCost;}
    }


}
