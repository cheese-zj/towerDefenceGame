package WizardTD.GameSys;

import WizardTD.App;

public class P extends Buttons{

    public P(float x, float y, App app) {
        super(x, y, app,"P", "PAUSE");
    }
    @Override
    public void functionality(App app) {
        if (checked) {
            App.GAME_TICKING = false;
        } else {
            App.GAME_TICKING = true;
        }
    }
}
