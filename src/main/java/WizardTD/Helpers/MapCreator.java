package WizardTD.Helpers;

import WizardTD.App;
import WizardTD.Tiles.*;

public class MapCreator {

    private ImageHelper imageHelper;

    public Grass[][] grasses;
    public Shrub[][] shrubs;
    public Path[][] paths;
    public WizardHouse wizardHouse;
    public Grass grassUnderHouse;

    public void CreateMap() {

        String[][] levelArray = GridCreator.LevelReader();
        this.grasses = new Grass[20][20];
        this.shrubs = new Shrub[20][20];
        this.paths = new Path[20][20];
        this.imageHelper = new ImageHelper();
        //From Grid class method, reading the level txt file
        //And detect all the spaces for grass tiles, appending them into a Grass 2D Array
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
                    paths[i][j] = new Path(i * App.CELLSIZE, j * App.CELLSIZE );

                    // Check surrounding tiles
                    boolean east  = i < 19 && (levelArray[i + 1][j].equals("X") || levelArray[i + 1][j].equals("W"));
                    boolean west  = i > 0  && (levelArray[i - 1][j].equals("X") || levelArray[i - 1][j].equals("W"));
                    boolean south = j < 19 && (levelArray[i][j + 1].equals("X") || levelArray[i][j + 1].equals("W"));
                    boolean north = j > 0  && (levelArray[i][j - 1].equals("X") || levelArray[i][j - 1].equals("W"));

                    paths[i][j].setEast(east);
                    paths[i][j].setWest(west);
                    paths[i][j].setSouth(south);
                    paths[i][j].setNorth(north);
                    paths[i][j].setSprite(App.path0png);

                    {
                        // Determine image based on surrounding tiles
                        if (east || west)
                            paths[i][j].setSprite(App.path0png);
                        if (north || south)
                            paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path0png, 90));
                    }
                    {
                        // L-shaped paths
                        if (south && west)
                            paths[i][j].setSprite(App.path1png);
                        else if (north && west)
                            paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path1png, 90));
                        else if (north && east)
                            paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path1png, 180));
                        else if (south && east)
                            paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path1png, 270));
                    }

                    {
                        // T-shaped paths
                        if (east && west && south)
                            paths[i][j].setSprite(App.path2png);
                        else if (north && south && west)
                            paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path2png, 90));
                        else if (east && west && north)
                            paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path2png, 180));
                        else if (north && south && east)
                            paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path2png, 270));
                    }
                    // Cross-shaped paths
                    if (north && south && west && east) {
                        paths[i][j].setSprite(App.path3png);
                    }
                }
            }
        }
    }
}
