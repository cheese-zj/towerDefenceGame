package WizardTD.Tiles;


/**
 * Path tiles forms routes, allows monster to be walked on.
 * Contains connection information for image choosing and route formation / monsters' path finding
 */
public class Path extends Tiles{

    //direction variables for path-finding

    private boolean North;
    private boolean South;
    private boolean East;
    private boolean West;

    public Path(int x, int y) {
        super(x, y);
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
