package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;

/**
 * Update option 2: TowerFireSpeed Level upgrade
 * <p>
 * </p>
 * @see Buttons
 * @see T
 * @see WizardTD.Towers.Tower
 */
public class U2 extends Buttons {

    public static boolean U2checked;
    public U2(float x, float y, App app) {
        super(x, y, app,"U2", "Upgrade\nspeed",'2');
    }
    @Override
    public void functionality(App app) {
        if (checked) {
            U2checked = true;
        } else {
            U2checked = false;
        }
    }
}
