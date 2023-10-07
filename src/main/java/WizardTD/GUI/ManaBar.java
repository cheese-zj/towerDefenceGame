package WizardTD.GUI;

import WizardTD.App;
import processing.core.PConstants;
import processing.core.PShape;

public class ManaBar {
    public static int mana;
    public static int manaCap;
    public static int manaGain;
    boolean ticking = true;
    int manaTick = 60;
    public ManaBar(int initialMana, int initialManaCap, int initialManaGain){
        mana = initialMana;
        manaCap = initialManaCap;
        manaGain = initialManaGain;
    }

    private void manaUpdate(){
        if (manaTick == 60 && mana <= manaCap) {
            mana += manaGain;
            manaTick%=60;
        }
        manaTick++;
        if (mana > manaCap) mana = manaCap;
    }

    public static void getAttacked(int dmgTaken) {
        mana -= dmgTaken;
    }
    public void drawManaBar(App app) {
        PShape manaBar =
                app.createShape(PConstants.RECT, 340,10,(float)(280*(((double)mana/(double) manaCap))),20);
        PShape manaBarBase =
                app.createShape(PConstants.RECT, 340,10,280,20);
        manaBar.setFill(app.color(40,80,235));
        manaBarBase.setFill(app.color(255,255,255));
        manaBarBase.setStrokeWeight(2);
        app.shape(manaBarBase);
        app.shape(manaBar);
        if (ticking) {
            manaUpdate();
        }
    }
}
