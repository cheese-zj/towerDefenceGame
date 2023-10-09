package WizardTD.GameSys;

import WizardTD.App;
import WizardTD.Towers.TowerBuilder;

public class U2 extends Buttons{

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
