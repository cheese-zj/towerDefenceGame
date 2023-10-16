package WizardTD.GameSys;

import WizardTD.App;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShape;

public class ManaBar {
    public static int mana;
    public static int manaCap;
    public static int manaGain;
    public static int manaPoolCost;
    protected static boolean shielded = false;
    //boolean ticking = true;
    int manaTick = 60;
    public ManaBar(){
        mana = App.json.getInt("initial_mana");
        manaCap = App.json.getInt("initial_mana_cap");
        manaGain = App.json.getInt("initial_mana_gained_per_second");
        manaPoolCost = App.json.getInt("mana_pool_spell_initial_cost");
    }

    protected void manaUpdate(){
        if (App.GAME_TICKING) {
            if (manaTick >= 60 && manaTick <= 61 &&mana <= manaCap) {
                mana += manaGain;
                manaTick %= 60;
            }
            manaTick += App.TICK_Multiplier;
            if (mana > manaCap) mana = manaCap;
        }

    }

    public static void getAttacked(int dmgTaken) {
        if (!shielded) {
            mana -= dmgTaken;
        } else {
            shielded = false;
        }
        if (mana<0){
            mana = 0;
        }
    }
    public void drawManaBar(App app) {
        PShape manaBar =
                app.createShape(PConstants.RECT, 340,10,(float)(280*(((double)mana/(double) manaCap))),20);
        PShape manaBarBase =
                app.createShape(PConstants.RECT, 340,10,280,20);
        PFont tFont = app.createFont("Arial",16,true);
        manaBar.setFill(app.color(40,80,235));
        if (shielded) manaBar.setFill(app.color(255,255,100));
        manaBarBase.setFill(app.color(255,255,255));
        manaBarBase.setStrokeWeight(2);
        app.fill(0);
        app.shape(manaBarBase);
        app.shape(manaBar);
        String manaBarText = mana + "/" + manaCap;
        app.textFont(tFont,16);
        app.text(manaBarText,445,27);
        manaUpdate();

    }
    public void manaBarReset() {
        mana = App.json.getInt("initial_mana");
        manaCap = App.json.getInt("initial_mana_cap");
        manaGain = App.json.getInt("initial_mana_gained_per_second");
        manaPoolCost = App.json.getInt("mana_pool_spell_initial_cost");
    }

    public static void manaBarGetShielded() {
        shielded = true;
    }
}
