package WizardTD;


import WizardTD.Helpers.GridCreator;
import WizardTD.Helpers.MapCreator;
import WizardTD.Tiles.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

public class MapTesting {

    MapCreator mapCreator;

    @BeforeEach
    public void Setup() {
        App app = new App();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        //assertEquals(expected, app.powerUp(500));
    }

    @Test
    public void GridTest() {
        String[][] levelResult = GridCreator.LevelReader();
    }

    @Test
    public void AppCreateMap() {
        this.mapCreator = new MapCreator();
        mapCreator.CreateMap();
    }

    @Test
    public void CheckGrass() {
        Grass grass = new Grass(10,10);
        Grass[][] grasses = new Grass[20][20];
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
}
