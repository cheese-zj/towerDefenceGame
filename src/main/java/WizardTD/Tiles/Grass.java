package WizardTD.Tiles;
import org.checkerframework.checker.units.qual.A;
import processing.core.PImage;
import WizardTD.*;

public class Grass extends Tiles{

    public boolean occupied;
    public Grass(int x, int y) {
        super(x, y);
        this.occupied = false;
    }
    public void setOccupied(){
        occupied = true;
    }
    public boolean isOccupied(){
        return occupied;
    }
}
