package WizardTD;

import WizardTD.GameSys.*;
import WizardTD.Helpers.WaveManager;
import WizardTD.Monsters.Monster;
import WizardTD.Towers.Tower;
import WizardTD.Towers.TowerBuilder;
import WizardTD.Towers.TowerPreset;

import static java.awt.Font.createFont;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {

    App app;
    private TestApp testApp;
    private Tower towerInstance;
    private Tower towerInstance2;
    private Tower towerInstance3;
    private Tower towerInstance4;
    public static PFont gameFont;
    public static PShape gShape;
    public ManaBar manaBar;
    TowerBuilder towerBuilder;

    @BeforeEach
    public void setUp() {
        Monster trackedMonster = null;
        testApp = new TestApp();
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        PApplet.runSketch(new String[]{"TestApp"}, app);
        manaBar = new ManaBar();
        towerBuilder = new TowerBuilder();
        testApp.setup();
        towerInstance = new Tower(2,2,1,1,1);
        towerInstance2 = new Tower(3,3,3,3,3);
        towerInstance3 = new Tower(1,1,0,0,0);
        towerInstance4 = new Tower(1,3,6,6,6);
        ManaBar.mana = 1000;
        // Assuming Tower has a default constructor
        testApp.loop();

    }

    @AfterEach
    public void done() {
        app.frame.dispose();
        testApp.frame.dispose();
    }

    @RepeatedTest(2)
    public void testDrawUpgrade() {
        // Set the state
        U1.U1checked = true;
        U2.U2checked = false;
        U3.U3checked = true;

        // Call the method
        towerInstance.drawUpgrade(testApp, 30, 0, 30);
        // Now, check what has been captured by our TestApp
        //assertTrue(testApp.wasTextFontCalled);
//        assertEquals("Range:       50", testApp.capturedText);
//        assertEquals("FireSpeed:  60", testApp.capturedText);
//        assertEquals("Damage:    70", testApp.capturedText);
        assertEquals("Total:       60", testApp.capturedText);
    }

    @RepeatedTest(2)
    public void testDrawUpgrade2() {
        // Set the state
        U1.U1checked = false;
        U2.U2checked = true;
        U3.U3checked = true;

        // Call the method
        towerInstance2.drawUpgrade(testApp, 0, 50, 50);
        // Now, check what has been captured by our TestApp
        //assertTrue(testApp.wasTextFontCalled);
//        assertEquals("Range:       50", testApp.capturedText);
//        assertEquals("FireSpeed:  60", testApp.capturedText);
//        assertEquals("Damage:    70", testApp.capturedText);
        assertEquals("Total:       100", testApp.capturedText);
    }

    @RepeatedTest(2)
    public void testDrawUpgrade3() {
        // Set the state
        U1.U1checked = true;
        U2.U2checked = true;
        U3.U3checked = false;

        // Call the method
        towerInstance3.drawUpgrade(testApp, 20, 20, 0);
        // Now, check what has been captured by our TestApp
        //assertTrue(testApp.wasTextFontCalled);
//        assertEquals("Range:       50", testApp.capturedText);
//        assertEquals("FireSpeed:  60", testApp.capturedText);
//        assertEquals("Damage:    70", testApp.capturedText);
        assertEquals("Total:       40", testApp.capturedText);
    }

    @RepeatedTest(2)
    public void testRangeDisplay() {
        // Set the state
        double a = towerInstance.getX();
        double b = towerInstance.getY();
        // Call the method
        gShape = towerInstance.createRangeDisplay(app);
        towerInstance.rangeDisplay(testApp,((int)a+1)*32,((int)b+1)*32+40);
        testApp.delay(100);
        towerInstance2.rangeDisplay(testApp,0,0);
    }



    @RepeatedTest(2)
    public void testDrawTowerFromApp() {
        testApp.DrawTowers();
        towerInstance.draw(testApp,19,19);
        towerInstance2.draw(testApp,19,19);
        towerInstance3.draw(testApp,19,19);
        towerInstance4.draw(testApp,19,19);
    }
    @Test
    public void TowerHoverTest2() {
        for (int i=0; i<760; i++){
            for (int j=0; j <680; j++){
                testApp.mousePressed();
                TestApp.isMousePressed = true;
                U1.U1checked = true;
                U2.U2checked = true;
                U3.U3checked = true;
                ManaBar.mana = 1000;
                towerInstance.monitoring(i, j);
                //app.mouseReleased();
            }
        }
    }

    @Test
    public void TowerHoverTest3() {
        for (int i=0; i<760; i++){
            for (int j=0; j <680; j++){
                testApp.mousePressed();
                TestApp.isMousePressed = true;
                U1.U1checked = false;
                U2.U2checked = false;
                U3.U3checked = false;
                ManaBar.mana = 1000;
                towerInstance.monitoring(i, j);
                app.mouseReleased();
            }
        }
    }

    @Test
    public void TowerHoverTest4() {
        for (int i=0; i<760; i++){
            for (int j=0; j <680; j++){
                testApp.mousePressed();
                TestApp.isMousePressed = true;
                U1.U1checked = true;
                U2.U2checked = true;
                U3.U3checked = true;
                ManaBar.mana = 1;
                towerInstance.monitoring(i, j);
                app.mouseReleased();
            }
        }
    }

    @RepeatedTest(2)
    public void BuilderT() {
        U1.U1checked = true;
        U2.U2checked = true;
        U3.U3checked = true;
        ManaBar.mana = 1000;
        towerBuilder.BuildTower(0,5);
    }

    @RepeatedTest(1)
    public void BuilderT2() {
        U1.U1checked = true;
        U2.U2checked = true;
        U3.U3checked = true;
        ManaBar.mana = 2;
        towerBuilder.BuildTower(0,12);
    }

    @RepeatedTest(1)
    public void BuilderT2B() {
        U1.U1checked = false;
        U2.U2checked = true;
        U3.U3checked = false;
        ManaBar.mana = 102;
        towerBuilder.BuildTower(0,13);
    }

    @RepeatedTest(1)
    public void BuilderT2C() {
        U1.U1checked = false;
        U2.U2checked = true;
        U3.U3checked = true;
        ManaBar.mana = 102;
        towerBuilder.BuildTower(0,14);
    }

    @RepeatedTest(1)
    public void BuilderT2D() {
        U1.U1checked = false;
        U2.U2checked = true;
        U3.U3checked = true;
        ManaBar.mana = 122;
        towerBuilder.BuildTower(0,15);
    }

    @RepeatedTest(1)
    public void BuilderT3() {
        U1.U1checked = false;
        U2.U2checked = false;
        U3.U3checked = false;
        ManaBar.mana = 2;
        towerBuilder.BuildTower(0,16);
    }

    @RepeatedTest(1)
    public void ManaBarExtra() {

        TestApp.TICK_Multiplier = 2;
        manaBar.drawManaBar(testApp);

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
