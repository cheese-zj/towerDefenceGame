package WizardTD.GameSys;

import WizardTD.App;

public class M extends Buttons{

    public M(float x, float y, App app) {
        super(x, y, app,"M", "Mana pool\ncost: ");
    }
    @Override
    public void functionality(App app) {
        title = "Mana pool\ncost: " + ManaBar.manaPoolCost;
        //ManaBar.manaPoolCost++;
        if (checked) {

        }
    }
}
