package WizardTD;

import WizardTD.GameSys.ButtonsCollection;
import WizardTD.GameSys.M;
import WizardTD.GameSys.ManaBar;
import WizardTD.Helpers.GridCreator;
import WizardTD.Helpers.ImageHelper;
import WizardTD.Helpers.MapCreator;
import WizardTD.Helpers.WaveManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

public class AppDrawTesting extends App {

    App app;
    WaveManager waveManager;
    MapCreator mapCreator;
    ImageHelper imageHelper;


    @BeforeEach
    public void Setup() {
        app = new App();
        App app = new App();
        app.configPath = "src/test/configtest.json";
        this.waveManager = new WaveManager();
        this.mapCreator = new MapCreator();
        this.imageHelper = new ImageHelper();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        //app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        ManaBar manaBar = new ManaBar();
        ButtonsCollection buttonsCollection = new ButtonsCollection();
        app.delay(2000);
    }


    @Override
    public void DrawMap() {}
    @Override
    public void DrawMonsters() {}
    @Override
    public void DrawTowerRange() {}

    @Override
    public void DrawFireballs() {}

    @Override
    public void DrawGUI() {}

    @Override
    public void gameReset() {}

    @Override
    public void DrawTowerUpgradeInfo() {}

    @Override
    public void DrawSpell() {}

    @RepeatedTest(2)
    public void TestDraw() {

        DrawMap();
        DrawMonsters();
        DrawTowerRange();
        DrawTowerUpgradeInfo();
        DrawTowers();
        DrawFireballs();
        WIN = true;
        DrawGUI();
        WIN = false;
        DrawGUI();
    }
}
