package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;

/**
 * The FF (Fast Forward) button in the WizardTD game interface.
 * <p>
 * The FF button allows players to adjust the speed of the game, toggling between
 * normal speed and a 2x (fast forward) speed. When activated, it doubles the game's
 * tick rate, making events in the game proceed twice as fast.
 * </p>
 */
public class FF extends Buttons {

    /**
     * Constructs an FF button with the specified position and associated game application.
     *
     * @param x  The x-coordinate for the position of the button.
     * @param y  The y-coordinate for the position of the button.
     * @param app The App instance used for interfacing with the game's rendering and logic systems.
     */
    public FF(float x, float y, App app) {
        super(x, y, app,"FF", "2x\nSpeed",'f');
    }

    /**
     * Defines the functionality of the FF button.
     * <p>
     * When the FF button is checked (activated), the game's TICK_Multiplier is set to 2,
     * causing the game to proceed at 2x speed. When unchecked (deactivated), the game
     * reverts to its normal speed with a TICK_Multiplier of 1.
     * </p>
     *
     * @param app The App instance representing the current game state.
     */
    @Override
    public void functionality(App app) {
        if (checked) {
            App.TICK_Multiplier = 2;
        } else {
            App.TICK_Multiplier = 1;
        }
    }
}
