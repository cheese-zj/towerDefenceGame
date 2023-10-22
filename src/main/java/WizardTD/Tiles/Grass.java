package WizardTD.Tiles;
import org.checkerframework.checker.units.qual.A;
import processing.core.PImage;
import WizardTD.*;

/**
 * Grass tiles can be built on and occupied
 */
public class Grass extends Tiles{

    //An occupation variable to determine if there's any tower on it to prevent building at the same spot

    private boolean occupied;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Grass(int x, int y) {
        super(x, y);
        this.occupied = false;
    }

}
