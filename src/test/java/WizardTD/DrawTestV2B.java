package WizardTD;

import WizardTD.GameSys.Inventory;
import WizardTD.GameSys.P;
import WizardTD.GameSys.T;
import WizardTD.Monsters.Monster;
import WizardTD.Monsters.MonsterDirection;
import WizardTD.Spell.Particle;
import WizardTD.Spell.SpellCaster;
import WizardTD.Spell.SpellType;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.Random;

public class DrawTestV2B {

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
        testApp.configPath = "src/test/configtest2.json";
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        app.configPath = "src/test/configtest2.json";
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
    public void HoverInvExtend() {
        inventory.draw(testApp);
        testApp.mouseX = 100;
        testApp.mouseY = 100;
        testApp.mousePressed();
        Inventory.InvChecked = true;
        testApp.mouseReleased();
        inventory.drawChoices(testApp);
        testApp.delay(100);
        testApp.mouseReleased();
        testApp.mouseX = 100 + 20;
        testApp.mouseY = 100 + 80;
        testApp.mousePressed();
        TestApp.isMousePressed = true;
    }

    @RepeatedTest(1)
    public void HoverInvExtend2() {
        for (int i = 0; i<4; i++) {
            inventory.draw(testApp);
            Inventory.SpellChoice = Inventory.SpellChoice.next();
            testApp.mouseX = 100;
            testApp.mouseY = 100;
            testApp.mousePressed();
            Inventory.InvChecked = true;
            testApp.delay(200);
            TestApp.isMousePressed = false;
            inventory.drawChoices(testApp);
            testApp.mouseX = 100 + 20;
            testApp.mouseY = 100 + 80;
            TestApp.isMousePressed = true;
            testApp.delay(200);
            TestApp.isMousePressed = false;
            testApp.delay(200);
            testApp.mouseX = 0;
            testApp.mouseY = 136;
            TestApp.isMousePressed = true;
            testApp.delay(200);
            TestApp.isMousePressed = false;
            testApp.delay(200);
            testApp.mouseX = 500;
            testApp.mouseY = 500;
            TestApp.isMousePressed = true;
            testApp.delay(200);
            TestApp.isMousePressed = false;
            testApp.delay(200);
        }

    }

    @RepeatedTest(1)
    public void SPT() {
        SpellCaster.coolDown = 0;
        spellCaster.DrawParticles(testApp);
        SpellCaster.coolDown = 60;
        spellCaster.DrawParticles(testApp);
        Monster monsterA = new Monster(100,100,0,"gremlin",200,1,10,0);
        String a;
        for (int i = 0; i < 4; i++){
            Inventory.SpellChoice = SpellType.BLAST.next();
            a = Inventory.SpellChoice.toString();
            inventory.drawChoices(testApp);
            spellCaster.DrawParticles(testApp);
            particle.display(testApp);
            testApp.delay(200);
            Inventory.InvChecked = true;
            testApp.mouseX = 0;
            testApp.mouseY = 140;
            app.mouseX = 0;
            app.mouseY = 140;
            TestApp.isMousePressed = true;
            App.isMousePressed = true;
            testApp.delay(200);
            //
        }
        Inventory.SpellChoice = SpellType.BLAST.next().next().next().next();

        TestApp.isMousePressed = false;
        testApp.delay(50);
        testApp.mouseX = 0;
        testApp.mouseY = 136;
        TestApp.isMousePressed = true;
        SpellCaster.coolDown = 0;
        spellCaster.DrawParticles(testApp);

    }

    @RepeatedTest(1)
    public void SPT2inApp() {
        for (int i = 0; i<4; i++) {
            Inventory.SpellChoice = Inventory.SpellChoice.next();
            app.mouseX = 0;
            app.mouseY = 136;
            TestApp.isMousePressed = true;
            SpellCaster.coolDown = 0;
            spellCaster.DrawParticles(app);
            monsterInstance.getHit(-1000);
        }
    }

    @Test
    public void GUIDisplay() {
        // Set the state
        TestApp.WIN = false;
        testApp.DrawGUI();
        testApp.draw();
        TestApp.WIN = true;
        TestApp.setWIN(true);
        testApp.DrawGUI();
        testApp.draw();
    }

    @Test
    public void WhatDisplay() {
        // Set the state
        testApp.DrawFireballs();
        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
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
