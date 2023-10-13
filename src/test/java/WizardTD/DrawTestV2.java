package WizardTD;

import WizardTD.GameSys.M;
import WizardTD.GameSys.U1;
import WizardTD.GameSys.U2;
import WizardTD.GameSys.U3;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrawTestV2 {

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
        testApp.configPath = "configtest.json";
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        app.configPath = "configtest.json";
        PApplet.runSketch(new String[]{"App"}, app);
        //testApp.setup();
        towerInstance = new Tower(2,2,1,1,1);
        monsterInstance = new Monster(0,96,1.2F,"gremlin",100,1,10,1);
        monsterInstance2 = new Monster(19*32,4*32,3,"worm",100,1,10,1);
        monsterInstance3 = new Monster(0,96,0,"beetle",100,1,10,1);
        monsterInstance4 = new Monster(8*32,19*32,3,"",100,1,10,1);

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

    @RepeatedTest(2)
    public void GUIDisplay() {
        // Set the state
        TestApp.WIN = false;
        TestApp.setWIN(false);
        testApp.DrawGUI();
        testApp.draw();
    }
    @RepeatedTest(2)
    public void GUIDisplay2() {
        // Set the state
        TestApp.WIN = true;
        TestApp.setWIN(true);
        testApp.DrawGUI();
        testApp.draw();
    }

    @RepeatedTest(1)
    public void WhatDisplay() {
        // Set the state
        testApp.DrawFireballs();
        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
    }

    @RepeatedTest(1)
    public void WhatDisplayEx() {
        // Set the state
        TestApp.towers.add(towerInstance);
        testApp.mouseX = (int)(towerInstance.getX()+1)*32;
        testApp.mouseY = (int)(towerInstance.getY()+1)*32+40;
        testApp.DrawTowerUpgradeInfo();
        //testApp.DrawMonsters();
    }

    @RepeatedTest(2)
    public void WhatDisplay2() {
        // Set the state
        fireBallInstance.tick();
        testApp.DrawFireballs();
        towerInstance.tick();
        monsterInstance.tick();
        MonsterDirection direction = monsterInstance.getCurrentDirection();
        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
    }

    @RepeatedTest(2)
    public void WhatDisplay3() {
        // Set the state
        monsterInstance.ticking = true;
        fireBallInstance.tick();
        testApp.DrawFireballs();
        monsterInstance.ticking = false;
        monsterInstance.tick();
        towerInstance.tick();
        testApp.DrawTowers();
        testApp.draw();
    }

    @RepeatedTest(2)
    public void WhatDisplay4() {
        // Set the state
        monsterInstance.ticking = true;
        fireBallInstance.tick();
        testApp.DrawFireballs();
        towerInstance.tick();
        testApp.delay(100);
        monsterInstance.ticking = false;
        monsterInstance.tick();
        towerInstance.tick();
        fireBallInstance.ticking = false;

        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
    }

    @RepeatedTest(1)
    public void WhatDisplay5() {
        // Set the state
        monsterInstance.ticking = true;
        monsterInstance.tick();
        monsterInstance.getHit(0);
        monsterInstance.dead = true;
        //monsterInstance.draw(testApp);
        testApp.delay(9000);

        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
    }

    @RepeatedTest(1)
    public void WhatDisplay6() {
        // Set the state
        monsterInstance.ticking = false;
        monsterInstance.tick();
        monsterInstance.getHit(0);
        monsterInstance.dead = true;
        TestApp.GAME_TICKING = false;
        //monsterInstance.draw(testApp);
        testApp.delay(9000);

        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
    }

    @Test
    public void WhatDisplay26() {
        // Set the state
        monsterInstance2.ticking = true;
        monsterInstance2.tick();

        //testApp.DrawMonsters();
    }

    @Test
    public void WhatDisplay46() {
        // Set the state
        monsterInstance4.ticking = true;
        monsterInstance4.tick();

        //testApp.DrawMonsters();
    }

    @RepeatedTest(1)
    public void WhatDisplay7() {
        // Set the state

        monsterInstance.ticking = false;
        monsterInstance.tick();
        monsterInstance.goNorth();
        MonsterDirection direction = monsterInstance.getCurrentDirection();
        monsterInstance.getHit(0);
        monsterInstance.dead = true;

        monsterInstance2.ticking = false;
        monsterInstance2.tick();
        monsterInstance2.goSouth();
        direction = monsterInstance2.getCurrentDirection();
        monsterInstance2.getHit(0);
        monsterInstance2.dead = true;

        monsterInstance3.ticking = false;
        monsterInstance3.tick();
        monsterInstance3.goWest();
        direction = monsterInstance3.getCurrentDirection();
        monsterInstance3.getHit(0);
        monsterInstance3.dead = true;

        monsterInstance4.ticking = false;
        monsterInstance4.tick();
        monsterInstance4.goNorth();
        direction = monsterInstance4.getCurrentDirection();
        monsterInstance4.getHit(1);
        monsterInstance4.dead = true;

        testApp.delay(100);
    }

    @RepeatedTest(1)
    public void TowerMonitoring() {
        // Set the state
        monsterInstance.ticking = false;
        monsterInstance.tick();
        monsterInstance.getHit(0);
        monsterInstance.dead = true;
        //monsterInstance.draw(testApp);
        testApp.delay(100);

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
