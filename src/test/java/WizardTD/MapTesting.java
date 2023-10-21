package WizardTD;


import WizardTD.GameSys.ButtonsCollection;
import WizardTD.GameSys.ManaBar;
import WizardTD.GameSys.P;
import WizardTD.Helpers.GridCreator;
import WizardTD.Helpers.MapCreator;
import WizardTD.Helpers.WaveManager;
import WizardTD.Monsters.Monster;
import WizardTD.Tiles.*;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PShape;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class MapTesting {

    MapCreator mapCreator;
    App app;
    WaveManager waveManager;

    @BeforeEach
    public void Setup() {
        App app = new App();
        app.configPath = "src/test/configtest.json";
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        //app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        //assertEquals(expected, app.powerUp(500));
    }




    @Test
    public void GridTest() {
        GridCreator gridCreator = new GridCreator();
        String[][] levelResult = GridCreator.LevelReader();
        levelResult = GridCreator.LevelReader();
    }

    @Test
    public void AppCreateMap() {
        this.mapCreator = new MapCreator();
        mapCreator.CreateMap();
        //GridCreator.Level = ".txt";
        String[][] levelResult = GridCreator.LevelReader();
        levelResult = GridCreator.LevelReader();
        mapCreator.CreateMap();
    }

    @Test
    public void CheckGrass() {
        Grass grass = new Grass(10,10);
        Grass[][] grasses = new Grass[20][20];
        grass.setOccupied(false);

    }
    @Test
    public void CheckShrub() {
        Shrub shrub = new Shrub(20,20);
    }

    @Test
    public void CheckExtending() {
        class Shrub2 extends Grass {
            Shrub2(int x, int y, boolean buildOn, boolean walkOn) {
                super(x, y);
            }
        }
    }

    @Test
    public void CheckPath(){
        PImage testImage = App.path0png;
        Path path = new Path(10,10);
        int a = path.getX();
        a = path.getY();
        path.setSprite(testImage);
        boolean dl = path.isEast();
        dl = path.isWest();
        dl = path.isNorth();
        dl = path.isSouth();
    }

}
