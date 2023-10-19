package WizardTD;


import WizardTD.GameSys.*;
import WizardTD.Helpers.GridCreator;
import WizardTD.Helpers.MapCreator;
import WizardTD.Helpers.WaveManager;
import WizardTD.Monsters.Monster;
import WizardTD.Tiles.Grass;
import WizardTD.Tiles.Path;
import WizardTD.Tiles.Shrub;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import WizardTD.Towers.TowerBuilder;
import WizardTD.Towers.TowerPreset;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.*;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TowerTesting {

    App app;
    WaveManager waveManager;
    ButtonsCollection buttonsCollection;
    TowerBuilder towerBuilder;
    Tower tower;


    @BeforeEach
    public void Setup() {
        app = new App();
        this.towerBuilder = new TowerBuilder();
        app.configPath = "src/test/configtest.json";
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        //app.setup();
        tower = new Tower(2,2,2,2,2);
        ManaBar.mana = 1000;
        app.delay(1000); // to give time to initialise stuff before drawing begins
    }


    @Test
    public void TowerBasics(){
        tower.tick();
        tower.rangeDisplay(app,10,10);
        double a = tower.getX();
        a = tower.getY();
        a = tower.dmgCost;
        a = tower.fireCost;
        a = tower.rangeCost;
    }

    @Test
    public void TowerBuilderTest() {
        TowerBuilder towerBuilder = new TowerBuilder();
        ArrayList<Tower> towerArray = new ArrayList<>();
        towerBuilder.BuildTower(3,0);
        for (Tower tower : App.towers){
            tower.tick();
            double a = tower.getX();
            a = tower.getY();
        }
    }
    @Test
    public void TowerHoverTest() {
        for (int i=0; i<760; i++){
            for (int j=0; j <680; j++){
                app.mousePressed();
                U1.U1checked = true;
                U2.U2checked = true;
                U3.U3checked = true;
                tower.monitoring(i, j);
                app.mouseReleased();
                U1.U1checked = false;
                U2.U2checked = false;
                U3.U3checked = false;
                tower.monitoring(i, j);
                tower.monitoring(i,j);
            }
        }
    }

    @Test
    public boolean ManabarConsumption() {
        int originalMana = ManaBar.mana;
        U1.U1checked = true;
        U2.U2checked = true;
        U3.U3checked = true;
        ManaBar.mana = 1000;
        towerBuilder.BuildTower(10,10);
        return (originalMana-(TowerBuilder.towerDmgLv+2)*10-(TowerBuilder.towerFireLv+2)*10-(TowerBuilder.towerRangeLv+2)*10 == ManaBar.mana);
    }




}
