package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;
import WizardTD.Spell.SpellType;

public class Inventory extends Buttons {

    public static boolean InvChecked;
    public static int spellCount = App.json.getInt("initial_spell_amount");
    public static SpellType SpellChoice = SpellType.POISON;
    public Inventory(float x, float y, App app) {
        //spellCount = 0;
        super(x, y, app,"I", "Inventory\n" + "     Charges",'i');
    }
    @Override
    public void functionality(App app) {
        title = "Inventory\n" + spellCount +  " Spells";
        if (checked) {
            InvChecked = true;
            drawChoices(app);
        } else {
            InvChecked = false;
        }

        if (spellCount == 0){
            checked = false;
        }
    }

    public void drawChoices(App app) {
        if (SpellChoice.equals(SpellType.BLAST)) app.fill(255,0,0);
        if (SpellChoice.equals(SpellType.POISON)) app.fill(200,80,255);
        if (SpellChoice.equals(SpellType.SLOW)) app.fill(0,30,255);
        if (SpellChoice.equals(SpellType.PROTECTION)) app.fill(230,230,80);
        app.strokeWeight(2);
        app.ellipse(this.x+15, this.y+70,20,20);
        app.strokeWeight(1);
        app.fill(0);
        app.textFont(App.gameFont, 15);
        app.text(SpellChoice.toString(),this.x+30, this.y+75);
        if (App.isMousePressed && checkHoverInventorySpec(app.mouseX, app.mouseY,this.x,this.y)){
            SpellChoice = SpellChoice.next();
        }
    }

}
