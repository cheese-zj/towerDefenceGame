package WizardTD.Helpers;

import WizardTD.App;
import WizardTD.GameSys.ButtonClasses.*;

/**
 * A collection of hover checking methods to be implemented by multiple elements in the game.
 * <p>
 * Provides various checking range, all specified in the interface. They are all extending from the base
 * method of isWithinBounds which is taking args.
 * Aiming for clearer structure and simplify hover detection in multiple classes
 * </p>
 */
public interface CheckHover {
    // Base method to check if mouseX and mouseY are within the specified boundaries
    default boolean isWithinBounds(int mouseX, int mouseY, float minX, float minY, float maxX, float maxY) {
        return mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY <= maxY;
    }

    default boolean checkHover(int mouseX, int mouseY, float x, float y) {
        // For button hovering checks
        return isWithinBounds(mouseX, mouseY, x, y, x + 48, y + 48);
    }

    default boolean checkHoverTower(int mouseX, int mouseY, float x, float y) {
        float minX = x * App.CELLSIZE;
        float maxX = (x + 1) * App.CELLSIZE;
        float minY = y * App.CELLSIZE + App.TOPBAR;
        float maxY = (y + 1) * App.CELLSIZE + App.TOPBAR;
        return isWithinBounds(mouseX, mouseY, minX, minY, maxX, maxY);
    }

    default boolean checkHoverInventorySpec(int mouseX, int mouseY, float x, float y) {
        return isWithinBounds(mouseX, mouseY, x, y + 60, x + 70, y + 90);
    }

    default boolean hoverInMap(int mouseX, int mouseY) {
        float maxX = 20 * App.CELLSIZE;
        float maxY = 20 * App.CELLSIZE + App.TOPBAR;
        return isWithinBounds(mouseX, mouseY, 0, App.TOPBAR, maxX, maxY);
    }


}
