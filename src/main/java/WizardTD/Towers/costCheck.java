package WizardTD.Towers;

import WizardTD.GameSys.ButtonClasses.*;
import WizardTD.GameSys.ManaBar;


public interface costCheck {
    default int costAdd(boolean checked, int Lv) {
        int Cost = 0;
        if (checked) Cost = (Lv+2)*10;
        return Cost;
    }

    default void towerCostManage(Tower tower) {
        tower.rangeCost = 0;
        tower.rangeCost = costAdd(U1.U1checked, tower.rangeCost);
        tower.fireCost = 0;
        tower.fireCost = costAdd(U2.U2checked, tower.fireCost);
        tower.dmgCost = 0;
        tower.dmgCost = costAdd(U3.U3checked, tower.dmgCost);
    }

    default void towerConsume(Tower tower) {
        if (ManaBar.mana > tower.rangeCost && U1.U1checked) {
            tower.towerRangeLv++; ManaBar.mana -= tower.rangeCost;}
        if (ManaBar.mana > tower.fireCost && U2.U2checked) {
            tower.towerFireLv++; ManaBar.mana -= tower.fireCost;}
        if (ManaBar.mana > tower.dmgCost && U3.U3checked) {
            tower.towerDmgLv++; ManaBar.mana -= tower.dmgCost;}
    }


}
