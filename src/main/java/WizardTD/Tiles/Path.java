package WizardTD.Tiles;

public class Path extends Tiles{

    private boolean North;
    private boolean South;
    private boolean East;
    private boolean West;
    public Path(int x, int y, boolean buildOn, boolean walkOn) {
        super(x, y, false, true);
    }

    public boolean isNorth() {
        return North;
    }
    public void setNorth(boolean north) {
        North = north;
    }
    public boolean isSouth() {
        return South;
    }
    public void setSouth(boolean south) {
        South = south;
    }
    public boolean isEast() {
        return East;
    }
    public void setEast(boolean east) {
        East = east;
    }
    public boolean isWest() {
        return West;
    }
    public void setWest(boolean west) {
        West = west;
    }

}
