package WizardTD;


import WizardTD.Tiles.*;
import org.junit.jupiter.api.Test;

import processing.data.JSONObject;

import java.util.Map;

public class MapTesting {

    MapCreator mapCreator;
    @Test
    public void GridTest() {
        String[][] levelResult = Grid.LevelReader();
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
