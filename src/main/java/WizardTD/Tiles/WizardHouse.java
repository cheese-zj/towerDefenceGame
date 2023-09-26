package WizardTD.Tiles;

public class WizardHouse extends Tiles{

    public boolean UnderAttack;
    public WizardHouse(int x, int y) {
        super(x, y, false,false);
    }

    public void setUnderAttack(boolean underAttack) {
        UnderAttack = underAttack;
    }

    public boolean isUnderAttack() {
        return UnderAttack;
    }
}
