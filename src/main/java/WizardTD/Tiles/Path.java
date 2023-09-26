package WizardTD.Tiles;

public class Path extends Tiles{

    private boolean North;
    private boolean South;
    private boolean East;
    private boolean West;
    private boolean Edge;
    public Path(int x, int y) {
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

    public void setEdge(boolean edge) {
        Edge = edge;
    }
    public boolean isEdge() {
        return Edge;
    }
}