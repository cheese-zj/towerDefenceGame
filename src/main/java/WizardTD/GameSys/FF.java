package WizardTD.GameSys;

import WizardTD.App;

public class FF extends Buttons{

    public FF(float x, float y, App app) {
        super(x, y, app,"FF", "2x\nSpeed",'f');
    }
    @Override
    public void functionality(App app) {
        if (checked) {
            App.TICK_Multiplier = 2;
        } else {
            App.TICK_Multiplier = 1;
        }
    }
}
