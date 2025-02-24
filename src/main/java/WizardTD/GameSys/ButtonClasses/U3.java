package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;

/**
 * Update option 3: TowerDamage Level upgrade
 * <p>
 * </p>
 * @see Buttons
 * @see T
 * @see WizardTD.Towers.Tower
 */
public class U3 extends Buttons {

    public static boolean U3checked;
    public U3(float x, float y, App app) {
        super(x, y, app,"U3", "Upgrade\ndamage",'3');
    }
    @Override
    public void functionality(App app) {
        if (checked) {
            U3checked = true;
        } else {
            U3checked = false;
        }
    }
}
