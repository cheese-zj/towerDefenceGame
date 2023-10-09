package WizardTD.GameSys;

import WizardTD.App;
import WizardTD.Towers.TowerBuilder;

public class U3 extends Buttons{

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
