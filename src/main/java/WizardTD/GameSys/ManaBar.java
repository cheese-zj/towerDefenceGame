package WizardTD.GameSys;

import WizardTD.App;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShape;

/**
 * Represents the mana bar in the WizardTD game.
 * <p>
 * The ManaBar class manages the player's in-game currency/player's hp, referred to as mana.
 * The class offers functionalities such as updating the mana, displaying the mana bar,
 * handling damage to the mana, and managing effects like shielding.
 * </p>
 */
public class ManaBar {
    public static float mana;
    public static int manaCap;
    public static float manaGain;
    public static int manaPoolCost;
    protected static boolean shielded = false;
    //boolean ticking = true;
    int manaTick = 60;

    /**
     * Constructs a new ManaBar with values fetched from game settings.
     */
    public ManaBar(){
        mana = App.json.getInt("initial_mana");
        manaCap = App.json.getInt("initial_mana_cap");
        manaGain = App.json.getInt("initial_mana_gained_per_second");
        manaPoolCost = App.json.getInt("mana_pool_spell_initial_cost");
    }

    /**
     * Updates the player's mana over time based on the game's ticking mechanism.
     */
    protected void manaUpdate(){
        if (mana > manaCap) mana = manaCap;
        if (App.GAME_TICKING) {
            if (manaTick >= 60 && manaTick <= 61 && mana <= manaCap) {
                mana += manaGain;
                manaTick %= 60;
            }
            manaTick += App.TICK_Multiplier;
        }
    }
    /**
     * Handles when the player's mana is attacked or reduced.
     * <p>
     * Reduces the player's mana by the specified damage value. If shielded, the shield absorbs the damage.
     * </p>
     *
     * @param dmgTaken The amount of damage to reduce from the mana.
     */
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
    /**
     * Draws the mana bar on the game's screen.
     *
     * @param app The App instance to interface with the game's rendering system.
     */
    public void drawManaBar(App app) {
        manaUpdate();
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
        String manaBarText = (int)mana + "/" + manaCap;
        app.textFont(tFont,16);
        app.text(manaBarText,445,27);
    }

    /**
     * Resets the mana bar to its initial values.
     */
    public void manaBarReset() {
        mana = App.json.getInt("initial_mana");
        manaCap = App.json.getInt("initial_mana_cap");
        manaGain = App.json.getInt("initial_mana_gained_per_second");
        manaPoolCost = App.json.getInt("mana_pool_spell_initial_cost");
        shielded = false;
    }

    /**
     * Activates a shield for the mana bar, protecting it from the next damage source.
     */
    public static void manaBarGetShielded() {
        shielded = true;
    }
}
