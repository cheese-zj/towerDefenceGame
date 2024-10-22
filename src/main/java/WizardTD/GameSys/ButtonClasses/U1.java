package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;


/**
 * Update option 1: TowerRange Level upgrade
 * <p>
 * </p>
 * @see Buttons
 * @see T
 * @see WizardTD.Towers.Tower
 */
public class U1 extends Buttons {

    public static boolean U1checked;
    public U1(float x, float y, App app) {
        super(x, y, app,"U1", "Upgrade\nrange",'1');
    }
    @Override
    public void functionality(App app) {
        if (checked) {
            U1checked = true;
        } else {
            U1checked = false;
        }
    }
}
