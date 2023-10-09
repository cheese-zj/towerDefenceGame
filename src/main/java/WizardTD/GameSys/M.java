package WizardTD.GameSys;

import WizardTD.App;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShape;

public class M extends Buttons{

    PShape hintBox;
    public M(float x, float y, App app) {
        super(x, y, app,"M", "Mana pool\ncost: ",'m');
        hintBox = app.createShape(PConstants.RECT, x-110,y,90,24);
        hintBox.setStrokeWeight(1);
        hintBox.setFill(app.color(255,255,255));
    }
    @Override
    public void functionality(App app) {
        title = "Mana pool\ncost: " + ManaBar.manaPoolCost;
        String hint = "Cost: "+ManaBar.manaPoolCost;
        //ManaBar.manaPoolCost++;
        if (checked) {
            if (ManaBar.manaPoolCost<ManaBar.mana) {
                ManaBar.mana -= ManaBar.manaPoolCost;
                ManaBar.manaPoolCost += App.json.getInt("mana_pool_spell_cost_increase_per_use");
                ManaBar.manaCap *= App.json.getFloat("mana_pool_spell_cap_multiplier");
                ManaBar.manaGain *= App.json.getFloat("mana_pool_spell_mana_gained_multiplier");
            }
            checked = false;
        }
        if (checkHover(app.mouseX,app.mouseY)){
            app.shape(hintBox);
            app.textFont(App.gameFont,15);
            app.text(hint, x-100, y+19);
        }
    }
}
