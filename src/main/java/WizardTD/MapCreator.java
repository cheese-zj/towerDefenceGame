package WizardTD;

import WizardTD.Tiles.Grass;
import WizardTD.Tiles.Path;
import WizardTD.Tiles.Shrub;
import WizardTD.Tiles.WizardHouse;

public class MapCreator {

    private ImageHelper imageHelper;

    public Grass[][] grasses;
    public Shrub[][] shrubs;
    public Path[][] paths;
    public WizardHouse wizardHouse;
    public Grass grassUnderHouse;

    public void CreateMap() {

        String[][] levelArray = Grid.LevelReader();
        this.grasses = new Grass[20][20];
        this.shrubs = new Shrub[20][20];
        this.paths = new Path[20][20];
        this.imageHelper = new ImageHelper();
        //From Grid class method, reading the level txt file
        //And detect all the spaces for grass tiles, appending them into a Grass 2D Array
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (levelArray[i][j] != null && levelArray[i][j].equals(" ")) {
                    grasses[i][j] = new Grass(i * 32, j * 32 + 40, true, false);
                    grasses[i][j].setSprite(App.grasspng);
                }
                if (levelArray[i][j] != null && levelArray[i][j].equals("S")) {
                    shrubs[i][j] = new Shrub(i * 32, j * 32 + 40, false, false);
                    shrubs[i][j].setSprite(App.shrubpng);
                }
                if (levelArray[i][j] != null && levelArray[i][j].equals("W")) {
                    this.wizardHouse = new WizardHouse(i * 32, j * 32 + 40, false, false);
                    wizardHouse.setSprite(App.wizard_housepng);
                    this.grassUnderHouse = new Grass(i * 32, j * 32 + 40, false, false);
                    grassUnderHouse.setSprite(App.grasspng);
                }
                if (levelArray[i][j] != null && levelArray[i][j].equals("X")) {
                    paths[i][j] = new Path(i * 32, j * 32 + 40, false, true);
                    if (i < 19 && levelArray[i + 1][j].equals("X")) {
                        paths[i][j].setEast(true);
                    }
                    if (i > 0 && levelArray[i - 1][j].equals("X")) {
                        paths[i][j].setWest(true);
                    }
                    if (j < 19 && levelArray[i][j + 1].equals("X")) {
                        paths[i][j].setSouth(true);
                    }
                    if (j > 0 && levelArray[i][j - 1].equals("X")) {
                        paths[i][j].setNorth(true);
                    }
                    paths[i][j].setSprite(App.path0png);

                    if (paths[i][j].isNorth() || paths[i][j].isSouth()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path0png, 90));
                    }
                    if (paths[i][j].isSouth() && paths[i][j].isWest()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path1png, 0));
                    } else if (paths[i][j].isSouth() && paths[i][j].isEast()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path1png, 270));
                    } else if (paths[i][j].isNorth() && paths[i][j].isEast()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path1png, 180));
                    } else if (paths[i][j].isNorth() && paths[i][j].isWest()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path1png, 90));
                    }
                    if (paths[i][j].isEast() && paths[i][j].isWest() && paths[i][j].isSouth()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path2png, 0));
                    } else if (paths[i][j].isEast() && paths[i][j].isWest() && paths[i][j].isNorth()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path2png, 180));
                    } else if (paths[i][j].isNorth() && paths[i][j].isSouth() && paths[i][j].isWest()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path2png, 90));
                    } else if (paths[i][j].isNorth() && paths[i][j].isSouth() && paths[i][j].isEast()) {
                        paths[i][j].setSprite(imageHelper.rotateImageByDegrees(App.path2png, 270));
                    }

                    if (paths[i][j].isNorth() && paths[i][j].isSouth() && paths[i][j].isWest() && paths[i][j].isEast()) {
                        paths[i][j].setSprite(App.path3png);
                    }
                }
            }
        }
    }
}
