package WizardTD;

import WizardTD.Monsters.Monster;
import WizardTD.Monsters.MonsterDirection;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PFont;

public class DrawTestV2B {

    App app;
    private TestApp testApp;
    private Tower towerInstance;
    private FireBall fireBallInstance;
    private Monster monsterInstance;
    private Monster monsterInstance2;
    private Monster monsterInstance3;
    private Monster monsterInstance4;
    private Monster[] monstersList;

    public static PFont gameFont;

    @BeforeEach
    public void setUp() {
        testApp = new TestApp();
        testApp.configPath = "configtest2.json";
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        app.configPath = "configtest2.json";
        PApplet.runSketch(new String[]{"App"}, app);
        //testApp.setup();
        towerInstance = new Tower(2,2,1,1,1);
        monsterInstance = new Monster(0,96,1.2F,"gremlin",100,1,10,1);
        monsterInstance2 = new Monster(0,96,1.2F,"worm",100,1,10,1);
        monsterInstance3 = new Monster(0,96,0,"beetle",100,1,10,1);
        monsterInstance4 = new Monster(9*32,19*32,3,"",100,1,10,1);

        fireBallInstance = new FireBall(0,96,5,10,monsterInstance);
        monstersList = new Monster[1];
        monstersList[0] = monsterInstance;

        TestApp.towers.add(towerInstance);
        TestApp.runningMonsterList = monstersList;
        TestApp.fireBalls.add(fireBallInstance);

        testApp.loop();
    }

    @AfterEach
    public void done() {
        app.frame.dispose();
        testApp.frame.dispose();
    }

    @Test
    public void GUIDisplay() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();
    }
    @Test
    public void GUIDisplay2() {
        // Set the state
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
