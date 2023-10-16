package WizardTD;

import WizardTD.GameSys.Buttons;
import WizardTD.GameSys.ButtonsCollection;
import WizardTD.GameSys.T;
import WizardTD.Monsters.Monster;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import processing.core.PApplet;
import processing.core.PFont;

public class ButtonTestVXNEWGEN {

    App app;
    private TestApp testApp;
    private Tower towerInstance;
    private FireBall fireBallInstance;
    private Monster monsterInstance;
    private ButtonsCollection buttonsCollection;

    public static PFont gameFont;

    @BeforeEach
    public void setUp() {
        testApp = new TestApp();
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        PApplet.runSketch(new String[]{"TestApp"}, app);
        //testApp.setup();
        towerInstance = new Tower(2,2,1,1,1);
        monsterInstance = new Monster(0,96,1,"gremlin",100,1,10,1);
        fireBallInstance = new FireBall(0,96,5,10,monsterInstance);

        buttonsCollection = new ButtonsCollection();
        buttonsCollection.generate(testApp);

        testApp.loop();
    }

    @AfterEach
    public void done() {
        app.frame.dispose();
        testApp.frame.dispose();
    }

    @RepeatedTest(1)
    public void TestButtonCheck() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            buttons.draw(testApp);
        }
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            TestApp.isMousePressed = true;
            buttons.draw(testApp);
        }
    }
    @RepeatedTest(2)
    public void TestButtonCheck2() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();

        for (Buttons buttons : buttonsCollection.buttonsArray) {
            TestApp.isMousePressed = true;
            buttons.draw(testApp);
        }
    }

    @RepeatedTest(1)
    public void TestButtonCheck3() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();
        char[] aaa = {'1','2','3','t','p','f','m'};

        for (Buttons buttons : buttonsCollection.buttonsArray) {
            for (char keySelect : aaa) {
                //TestApp.isMousePressed = true;
                testApp.keyPressed();
                testApp.key = keySelect;
                buttons.monitorKey();
                buttons.draw(testApp);
            }
        }
    }

    @RepeatedTest(1)
    public void TestButtonCheck4() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();
        char[] aaa = {'1','2','3','t','p','f','m'};

        for (Buttons buttons : buttonsCollection.buttonsArray) {
            testApp.mouseX = (int) buttons.getX();
            testApp.mouseY = (int) buttons.getY();
            buttons.draw(testApp);
        }
    }

    @RepeatedTest(1)
    public void TestButtonCheck5() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            buttons.draw(testApp);
        }
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            TestApp.isMousePressed = true;
            testApp.mouseX = 15*32;
            testApp.mouseY = 32+40;
            buttons.draw(testApp);
            buttons.functionality(testApp);
        }
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
