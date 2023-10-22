package WizardTD;

import WizardTD.GameSys.ButtonsCollection;
import WizardTD.GameSys.ButtonClasses.Inventory;
import WizardTD.GameSys.Buttons;
import WizardTD.GameSys.*;
import WizardTD.GameSys.ButtonsCollection;
import WizardTD.Helpers.ImageHelper;
import WizardTD.Helpers.MapCreator;
import WizardTD.Helpers.WaveManager;
import WizardTD.Monsters.Monster;
import WizardTD.Monsters.MonsterCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

public class MonsterEdgeTesting {
    ImageHelper imageHelper;
    MapCreator mapCreator;
    WaveManager waveManager;
    MonsterCreator monsterCreator;
    App app;
    Monster monster;

    @BeforeEach
    public void Setup() {
        App app;
        app = new App();
        this.app = new App();
        app.configPath = "src/test/configtest.json";
        this.waveManager = new WaveManager();
        this.mapCreator = new MapCreator();
        this.imageHelper = new ImageHelper();
        this.monsterCreator = new MonsterCreator();
        this.monster = new Monster(
                (0)*(App.CELLSIZE) - 31,
                (3)*(App.CELLSIZE),
                2.4F, "gremlin", 10, 1, 20, 60);
        app.loop();
        PApplet.runSketch(new String[]{"App"}, app);
        //app.setup();
        app.delay(2000); // to give time to initialise stuff before drawing begins
        //assertEquals(expected, app.powerUp(500));
        ManaBar manaBar = new ManaBar();
        ButtonsCollection buttonsCollection = new ButtonsCollection();
    }

    @Test
    public void TestMonsterGeneraton() {
        monster.tick();
        monster.getHit(20);

        monster.dead = true;
        monster.ticking = false;
        app.delay(5000);
        //monster.drawDeathAnimation(app);
        monsterCreator.CreateMonsters(1,1,1,1,1,1,1,"worm");
        monsterCreator.CreateMonsters(1,1,1,1,1,1,1,"beetle");
    }
    @Test
    public void TestMonsterGeneraton2() {
        monster.tick();
        monster.getHit(20);
        monster.getPoisoned(5);
        monster.update(app);
        app.delay(500);
    }
}
