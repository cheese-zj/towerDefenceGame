package WizardTD;

import WizardTD.Monsters.Monster;
import WizardTD.Monsters.MonsterDirection;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import processing.core.PApplet;
import processing.core.PFont;
import WizardTD.GameSys.ButtonClasses.Inventory;
import WizardTD.GameSys.Buttons;
import WizardTD.GameSys.*;
import WizardTD.GameSys.ButtonsCollection;
import WizardTD.GameSys.ButtonClasses.*;
import WizardTD.GameSys.Buttons;
import WizardTD.GameSys.*;
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
        //
        testApp = new TestApp();
        testApp.configPath = "src/test/configtest.json";
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        app.configPath = "src/test/configtest.json";
        PApplet.runSketch(new String[]{"App"}, app);
        //testApp.setup();
        testApp.delay(500);
        towerInstance = new Tower(2,2,1,1,1);
        monsterInstance = new Monster(0,96,1.2F,"gremlin",100,1,10,1);
        monsterInstance2 = new Monster(19*32,4*32,3,"worm",100,1,10,1);
        monsterInstance3 = new Monster(0,96,0,"beetle",100,1,10,1);
        monsterInstance4 = new Monster(8*32,19*32,3,"",100,1,10,1);

        fireBallInstance = new FireBall(0,96,5,10,monsterInstance);
        monstersList = new Monster[1];
        monstersList[0] = monsterInstance;

        ManaBar.manaBarGetShielded();

        TestApp.towers.add(towerInstance);
        TestApp.runningMonsterList.add(monstersList[0]);
        TestApp.fireBalls.add(fireBallInstance);

        testApp.loop();
    }

    @AfterEach
    public void done() {
        testApp.frame.dispose();
    }

    @RepeatedTest(2)
    public void GUIDisplay2() {
        // Set the state
        TestApp.WIN = true;
        TestApp.setWIN(true);
        testApp.DrawGUI();
        testApp.draw();
        TestApp.WIN = false;
        TestApp.setWIN(false);
        testApp.DrawGUI();
        testApp.draw();
    }

    @RepeatedTest(1)
    public void GUIDisplayLOSE() {

        ManaBar.getAttacked(5000);
        // Set the state
        TestApp.LOSE = true;
        testApp.DrawGUI();
        testApp.draw();
        testApp.mousePressed();
        testApp.key = 'r';
        testApp.keyPressed();
        ManaBar.getAttacked(5000);
        // Set the state
        TestApp.LOSE = true;
        testApp.key = 'R';


    }

    @RepeatedTest(1)
    public void WhatDisplayEx() {
        testApp.DrawFireballs();
        testApp.DrawTowers();
        testApp.draw();
        TestApp.towers.add(towerInstance);
        testApp.mouseX = (int)(towerInstance.getX()+1)*32;
        testApp.mouseY = (int)(towerInstance.getY()+1)*32+40;
        testApp.DrawTowerUpgradeInfo();
        //testApp.DrawMonsters();
    }

    @RepeatedTest(1)
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
        monsterInstance.getStonised();
        monsterInstance.getBlasted();
        monsterInstance.getPoisoned(2);

        //monsterInstance.draw(testApp);
        app.delay(3000);
        testApp.delay(3000);
        //monsterInstance.update(testApp);
        monsterInstance.dead = true;

        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
        ManaBar.getAttacked(2000);
    }


    @RepeatedTest(1)
    public void WhatDisplay52() {
        // Set the state

        for (Monster monster : App.runningMonsterList){
            monster.getPoisoned(10);
            app.delay(2);
        }

        monsterInstance.ticking = false;
        monsterInstance.tick();
        monsterInstance.getHit(0);
        monsterInstance.dead = true;
        TestApp.GAME_TICKING = false;
        //monsterInstance.draw(testApp);
        testApp.delay(1000);

        testApp.DrawTowers();
        testApp.draw();

        // Set the state
        monsterInstance2.ticking = true;
        monsterInstance4.ticking = true;
        //monsterInstance2.update(testApp);

        //testApp.DrawMonsters();
    }

    @RepeatedTest(1)
    public void WhatDisplay521() {
        // Set the state

        for (Monster monster : App.runningMonsterList){
            monster.getPoisoned(0);
            app.delay(2);
        }

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
    public void FtoMtest() {
        // Set the state
        monsterInstance.ticking = true;
        FireBall fireBall2 = new FireBall(20,20,5,1,monsterInstance);

        TestApp.fireBalls.add(fireBall2);
        TestApp.fireBalls.add(fireBallInstance);
        App.fireBalls.add(fireBall2);
        App.fireBalls.add(fireBallInstance);

        testApp.delay(1);
        monsterInstance.respawnAfterHit();

        testApp.DrawTowers();
        testApp.draw();
        //testApp.DrawMonsters();
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
