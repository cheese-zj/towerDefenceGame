package WizardTD.GameSys;

import WizardTD.App;
import WizardTD.GameSys.ButtonClasses.*;

import java.util.ArrayList;

/**
 * A collection of buttons in the WizardTD game.
 * <p>
 * The ButtonsCollection class maintains an array of buttons that are used in the game's interface.
 * This collection simplifies the process of creating, organizing, and managing multiple buttons
 * within the game.
 * </p>
 */
public class ButtonsCollection {

    /** List containing all the buttons in the collection. */
    public ArrayList<Buttons> buttonsArray = new ArrayList<>();

    /**
     * Generates and initializes the buttons for the game's interface.
     * <p>
     * This method populates the buttonsArray with instances of various button subclasses.
     * Each button is positioned and configured based on game's UI layout.
     * </p>
     *
     * @param app The App instance to interface with the game's rendering system.
     */
    public void generate(App app) {
        buttonsArray.add(new Inventory(640 + 10, 40 + 10*8 + 48*7, app)); // Inventory button
        buttonsArray.add(new FF(640 + 10, 40 + 10*1 + 48*0, app)); // FF button
        buttonsArray.add(new P(640 + 10, 40 + 10*2 + 48*1, app)); // P button
        buttonsArray.add(new M(640 + 10, 40 + 10*7 + 48*6, app)); // M button
        buttonsArray.add(new U1(640 + 10, 40 + 10*4 + 48*3, app)); // U1 button
        buttonsArray.add(new U2(640 + 10, 40 + 10*5 + 48*4, app)); // U2 button
        buttonsArray.add(new U3(640 + 10, 40 + 10*6 + 48*5, app)); // U3 button
        buttonsArray.add(new T(640 + 10, 40 + 10*3 + 48*2, app)); // T button
    }
}
