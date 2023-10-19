package WizardTD;

import WizardTD.GameSys.Buttons;
import WizardTD.GameSys.ButtonsCollection;
import WizardTD.GameSys.Inventory;
import WizardTD.GameSys.T;
import WizardTD.Monsters.Monster;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.*;
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
        testApp.configPath = "src/test/configtest.json";
        PApplet.runSketch(new String[]{"TestApp"}, testApp);
        app = new App();
        app.configPath = "src/test/configtest.json";
        PApplet.runSketch(new String[]{"App"}, app);
        //testApp.setup();
        testApp.delay(1000);
        towerInstance = new Tower(2,2,1,1,1);
        monsterInstance = new Monster(0,96,1,"gremlin",100,1,10,1);
        fireBallInstance = new FireBall(0,96,5,10,monsterInstance);

        buttonsCollection = new ButtonsCollection();
        buttonsCollection.generate(testApp);

        Inventory.spellCount = 0;

        testApp.loop();
    }

    @AfterEach
    public void done() {
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
    @Test
    public void TestButtonCheck2() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();

        for (Buttons buttons : buttonsCollection.buttonsArray) {
            TestApp.isMousePressed = true;
            buttons.draw(testApp);
        }
    }

    @Test
    public void TestButtonCheck3() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();
        char[] aaa = {'1','2','3','t','p','f','m','i'};

        for (Buttons buttons : buttonsCollection.buttonsArray) {
            for (char keySelect : aaa) {
                //TestApp.isMousePressed = true;
                testApp.keyPressed();
                testApp.key = keySelect;
                buttons.monitorKey();
                buttons.draw(testApp);
                buttons.functionality(testApp);
                testApp.delay(10);
            }
        }
    }

    @RepeatedTest(1)
    public void TestButtonCheck4() {
        // Set the state
        testApp.DrawGUI();
        testApp.draw();
        char[] aaa = {'1','2','3','t','p','f','m','i'};

        for (Buttons buttons : buttonsCollection.buttonsArray) {
            testApp.mouseX = (int) buttons.getX();
            testApp.mouseY = (int) buttons.getY();
            buttons.draw(testApp);
            for (char k : aaa) {
                testApp.key = k;
                testApp.keyPressed();
                testApp.delay(10);
                buttons.draw(testApp);
                buttons.functionality(testApp);
                testApp.delay(10);
            }
        }
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            testApp.mouseX = (int) buttons.getX();
            testApp.mouseY = (int) buttons.getY();
            buttons.draw(testApp);
            for (char k : aaa) {
                testApp.key = k;
                testApp.keyPressed();
                testApp.delay(10);
                buttons.draw(testApp);
                buttons.functionality(testApp);
                testApp.delay(10);
            }
        }
        app.delay(500);
        testApp.delay(500);
        char[] aab = {'t'};
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            testApp.mouseX = (int) buttons.getX();
            testApp.mouseY = (int) buttons.getY();
            buttons.draw(testApp);
            for (char k : aab) {
                app.key = k;
                app.keyPressed();
                app.delay(10);
                buttons.draw(testApp);
                app.mouseX = 10;
                app.mouseY = 32* 1 + 40;
                TestApp.isMousePressed = true;
                buttons.functionality(app);
                testApp.delay(10);
            }
        }
        app.delay(500);
        testApp.delay(500);
        char[] aac = {'t','1','2','3'};
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            testApp.mouseX = (int) buttons.getX();
            testApp.mouseY = (int) buttons.getY();
            buttons.draw(testApp);
            for (char k : aac) {
                app.key = k;
                app.keyPressed();
                app.delay(10);
                buttons.draw(testApp);
                app.mouseX = 10;
                app.mouseY = 32* 1 + 40;
                TestApp.isMousePressed = true;
                buttons.functionality(app);
                testApp.delay(10);
            }
        }
        app.delay(1000);
        testApp.delay(1000);

        char[] aai = {'i'};
        for (Buttons buttons : buttonsCollection.buttonsArray) {
            testApp.mouseX = (int) buttons.getX();
            testApp.mouseY = (int) buttons.getY();
            buttons.draw(testApp);
            for (char k : aai) {
                app.key = k;
                app.keyPressed();
                app.delay(10);
                buttons.draw(testApp);
                for (int i = 0; i<4; i++) {
                    app.mouseX = 640 + 10 + 10;
                    app.mouseY = 40 + 10 * 8 + 48 * 7 + 15;
                    TestApp.isMousePressed = true;
                    testApp.delay(10);
                    TestApp.isMousePressed = false;
                }
            }
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
