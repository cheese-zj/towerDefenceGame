package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;
import WizardTD.Towers.TowerBuilder;

/**
 * The {@code T} class represents the Tower Building button in the WizardTD game.
 * Extending the abstract {@code Buttons} class, the {@code T} class provides
 * functionality for players to build towers within the game's map.
 *
 * <p>
 * Upon activation, this button allows players to place a tower at a valid
 * location on the map, determined by the mouse position. The placement is
 * valid if the chosen cell is not already occupied and if it lies within
 * the game's map boundaries.
 * </p>
 *
 * <p>
 * The class collaborates with the {@code TowerBuilder} class, which provides
 * mechanisms for creating and placing towers.
 * </p>
 *
 * // U's are upgrade option
 * @see Buttons
 * @see TowerBuilder
 * @see U1
 * @see U2
 * @see U3
 */
public class T extends Buttons {

    TowerBuilder towerBuilder;

    /**
     * Constructs a new {@code T} button (Tower Building button) with the specified
     * position and reference to the main application.
     *
     * @param x   The x-coordinate of the button's position.
     * @param y   The y-coordinate of the button's position.
     * @param app The main application context.
     */
    public T(float x, float y, App app) {
        super(x, y, app,"T", "Build\ntower",'t');
        this.towerBuilder = new TowerBuilder();
    }

    /**
     * Attempts to build a tower at the specified mouse position.
     *
     * <p>
     * A tower is built if the location indicated by the mouse cursor is
     * unoccupied and within the game map's bounds.
     * </p>
     *
     * @param mouseX The x-coordinate of the mouse cursor.
     * @param mouseY The y-coordinate of the mouse cursor.
     */
    protected void MouseBuildTower(int mouseX, int mouseY) {

        if (hoverInMap(mouseX,mouseY)) {
            if (App.grasses[mouseX/32][(mouseY-40) / 32] != null &&
                    !App.grasses[mouseX/32][(mouseY-40) / 32].isOccupied()) {
                towerBuilder.BuildTower(mouseX/32, (mouseY-40)/32);
            }
        }
    }
    /**
     * Implements the functionality of the Tower Building button.
     *
     * <p>
     * When activated, and if the mouse button is pressed, this method tries
     * to build a tower at the mouse cursor's location.
     * </p>
     *
     * @param app The main application context.
     */
    @Override
    public void functionality(App app) {
        if (App.isMousePressed && checked && !Inventory.InvChecked){
            MouseBuildTower(app.mouseX, app.mouseY);
        }
    }
}
