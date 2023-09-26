package WizardTD.Tiles;

public class WizardHouse extends Tiles{

    public boolean underAttack;
    public WizardHouse(int x, int y) {
        super(x, y, false,false);
    }

    public boolean isUnderAttack() {
        return this.underAttack;
    }
}
