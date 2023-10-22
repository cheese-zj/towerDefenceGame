package WizardTD.Helpers;

import WizardTD.App;
import WizardTD.Tiles.*;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a map creator for the WizardTD game.
 * <p>
 * This class is responsible for generating the game map based on a predefined level layout.
 * It populates the map with various tiles like grass, shrubs, paths, and the wizard's house.
 * Additionally, this class determines and sets spawn points on the game map.
 * </p>
 */
public class MapCreator {

    public Grass[][] grasses;
    public Shrub[][] shrubs;
    public Path[][] paths;
    public WizardHouse wizardHouse;
    public Grass grassUnderHouse;
    /** A list of spawn points for monsters on the game map. */
    public static ArrayList<String> spawnPoints = new ArrayList<>();

    /**
     * Creates the game map using the predefined level layout.
     * <p>
     * This method reads the level layout, identifies different tiles and their types,
     * sets the appropriate images for each tile, and initializes the game map.
     * </p>
     */
    public void CreateMap() {

        String[][] levelArray = GridCreator.LevelReader();
        this.grasses = new Grass[20][20];
        this.shrubs = new Shrub[20][20];
        this.paths = new Path[20][20];
        ImageHelper imageHelper = new ImageHelper();
        //From Grid class method, reading the level txt file
        //And detect all the spaces for grass tiles, appending them into separate 2D Arrays
        //*The implementation here is really weird, I may choose polymorphism based Array in future*
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (levelArray[i][j] != null) {
                    switch (levelArray[i][j]) {
                        case " ":
                            grasses[i][j] = new Grass(i * App.CELLSIZE, j * App.CELLSIZE );
                            grasses[i][j].setSprite(App.grasspng);
                            break;
                        case "S":
                            shrubs[i][j] = new Shrub(i * App.CELLSIZE, j * App.CELLSIZE );
                            shrubs[i][j].setSprite(App.shrubpng);
                            break;
                        case "W":
                            this.wizardHouse =
                                    new WizardHouse(i * App.CELLSIZE - 5, j * App.CELLSIZE  - 10);
                            App.wizardX = i * App.CELLSIZE;
                            App.wizardY = j * App.CELLSIZE;
                            wizardHouse.setSprite(App.wizard_housepng);

                            this.grassUnderHouse = new Grass(i * App.CELLSIZE, j * App.CELLSIZE );
                            grassUnderHouse.setSprite(App.grasspng);
                            break;
                    }
                }
                if (levelArray[i][j] != null && (levelArray[i][j].equals("X") || levelArray[i][j].equals("W"))) {
                    Path path = new Path(i * App.CELLSIZE, j * App.CELLSIZE );

                    // Check surrounding tiles
                    boolean east  = i < 19 && (levelArray[i + 1][j].equals("X") || levelArray[i + 1][j].equals("W"));
                    boolean west  = i > 0  && (levelArray[i - 1][j].equals("X") || levelArray[i - 1][j].equals("W"));
                    boolean south = j < 19 && (levelArray[i][j + 1].equals("X") || levelArray[i][j + 1].equals("W"));
                    boolean north = j > 0  && (levelArray[i][j - 1].equals("X") || levelArray[i][j - 1].equals("W"));

                    // Check if path is on the boundary and make it a spawn point
                    if (i == 0 || j == 0 || i == 19 || j == 19) {
                        spawnPoints.add(i + " " + j);
                    }

                    path.setEast(east);
                    path.setWest(west);
                    path.setSouth(south);
                    path.setNorth(north);

                    path.setSprite(App.path0png);

                    {
                        // Determine image based on surrounding tiles
                        if ((east || i==19) || (west || i==0))
                            path.setSprite(App.path0png);
                        if ((north || j==0) || (south || j==19))
                            path.setSprite(imageHelper.rotateImageByDegrees(App.path0png, 90));
                    }
                    {
                        // L-shaped paths
                        if ((south || j==19) && (west || i==0))
                            path.setSprite(App.path1png);
                        else if ((north || j==0) && (west || i==0))
                            path.setSprite(imageHelper.rotateImageByDegrees(App.path1png, 90));
                        else if ((north || j==0) && (east || i==19))
                            path.setSprite(imageHelper.rotateImageByDegrees(App.path1png, 180));
                        else if ((south || j==19) && (east || i==19))
                            path.setSprite(imageHelper.rotateImageByDegrees(App.path1png, 270));
                    }

                    {
                        // T-shaped paths
                        if ((east || i==19) && (west || i==0) && (south || j==19))
                            path.setSprite(App.path2png);
                        else if ((north || j==0) && (south || j==19) && (west || i==0))
                            path.setSprite(imageHelper.rotateImageByDegrees(App.path2png, 90));
                        else if ((east || i==19) && (west || i==0) && (north || j==0))
                            path.setSprite(imageHelper.rotateImageByDegrees(App.path2png, 180));
                        else if ((north || j==0) && (south || j==19) && (east || i==19))
                            path.setSprite(imageHelper.rotateImageByDegrees(App.path2png, 270));
                    }
                    // Cross-shaped paths
                    if ((north || j==0) && (south || j==19) && (west || i==0) && (east || i==19)) {
                        path.setSprite(App.path3png);
                    }

                    paths[i][j] = path;
                }
            }
        }
    }
}
