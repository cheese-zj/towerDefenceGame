package WizardTD;

import WizardTD.GameSys.ButtonClasses.Inventory;
import WizardTD.GameSys.Buttons;
import WizardTD.GameSys.*;
import WizardTD.GameSys.ButtonsCollection;
import WizardTD.Monsters.Monster;
import WizardTD.Spell.Particle;
import WizardTD.Spell.SpellCaster;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.Random;

public class DrawTestV2C {

    App app;
    private TestApp testApp;
    private Tower towerInstance;
    private FireBall fireBallInstance;
    private Monster monsterInstance;
    private Monster[] monstersList;
    private SpellCaster spellCaster;
    private Inventory inventory;

    private Particle particle;

    public static PFont gameFont;

    @BeforeEach
    public void setUp() {
        Inventory.spellCount = 100;
        testApp = new TestApp();
        testApp.configPath = "src/test/configtestV2C.json";
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        app.configPath = "src/test/configtestV2C.json";
        PApplet.runSketch(new String[]{"App"}, app);
        //testApp.setup();
        testApp.delay(500);
        towerInstance = new Tower(2,2,1,1,1);
        monsterInstance = new Monster(0,96,1.2F,"gremlin",100,1,10,1);
        spellCaster = new SpellCaster();
        inventory = new Inventory(100,100,testApp);
        fireBallInstance = new FireBall(0,96,5,10,monsterInstance);
        monstersList = new Monster[1];

        particle = new Particle(100,100,new Random());
        monstersList[0] = monsterInstance;

        TestApp.towers.add(towerInstance);
        TestApp.runningMonsterList.add(monstersList[0]);
        TestApp.fireBalls.add(fireBallInstance);

        testApp.loop();
    }

    @AfterEach
    public void done() {
        testApp.frame.dispose();
    }
    @Test
    public void SitInv() {
        //inventory.draw(testApp);
        testApp.DrawSpell();
        testApp.mouseX = 0;
        testApp.mouseY = 0;
        testApp.mousePressed();
        testApp.delay(100);
        testApp.mouseReleased();
        testApp.mouseX = 100;
        testApp.mouseY = 100;
        testApp.mousePressed();

        particle.lifespan = 100;
        particle.update();
        particle.display(testApp);
        testApp.delay(100);
        particle.lifespan = 0;
        particle.update();
    }

    @Test
    public void justLetItRun() {
        App.LASTWAVE = true;
        testApp.delay(2000);
        app.delay(2000);
    }
    @Test
    public void justLetItRun22() {
        App.LASTWAVE = true;
        App.WIN = false;
        App.setWIN(false);
        testApp.delay(50);
        app.delay(50);
    }


    private class TestApp extends App {
        boolean wasTextFontCalled = false;
        String capturedText = null;


        @Override
        public void text(String str, float x, float y) {
            capturedText = str;
        }
        @Override
        public void draw(){

        }

    }
}
