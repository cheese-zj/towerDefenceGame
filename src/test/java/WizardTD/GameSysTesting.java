package WizardTD;

import WizardTD.GameSys.*;
import WizardTD.Helpers.WaveManager;
import WizardTD.Monsters.Monster;
import WizardTD.Monsters.MonsterDirection;
import WizardTD.Towers.FireBall;
import WizardTD.Towers.Tower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;

public class GameSysTesting extends App{
    App app;
    @BeforeEach
    public void Setup() {
        app = new App();
        App app = new App();
        app.configPath = "src/test/configtest.json";
        this.waveManager = new WaveManager();
        app.loop();
        PApplet.runSketch(new String[] { "App" }, app);
        //app.setup();
        app.delay(1000); // to give time to initialise stuff before drawing begins
        ManaBar manaBar = new ManaBar();
        ButtonsCollection buttonsCollection = new ButtonsCollection();
        //app.draw();
        app.gameReset();
        app.delay(2000);
    }



    @Test
    public void WinAndLostTest(){
        //app.setup();
        boolean bl = App.WIN;
        bl = App.LOSE;
        boolean bla = App.GAME_TICKING;
        int s = App.TICK_Multiplier;
    }

    Buttons button;

    @Test
    public void ButtonTest() {
        ButtonsCollection buttonsCollection = new ButtonsCollection();
        for (Buttons buttons : buttonsCollection.buttonsArray){
            this.button = buttons;
            button.functionality(this);
            button.monitorKey();
        }
    }

    @Test
    public void ManaBarTest() {
        int a = ManaBar.mana;
        a = ManaBar.manaGain;
        a = ManaBar.manaCap;
        a = ManaBar.manaPoolCost;
        ManaBar.getAttacked(10);
        a = ManaBar.mana;
    }

    @Test
    public void WaveSysTest() {
        int a = waveManager.waveCount;
        waveManager.WaveSetup();
        ArrayList<Monster[]> wave = waveManager.waves;
        Monster[] monsters = wave.get(0);
        Monster monster = monsters[0];
        boolean bl = monster.ticking;
        bl = monster.dead;
        bl = monster.canTrack;
        monster.tick();
        monster.goNorth();
        monster.goSouth();
        monster.goWest();
        monster.goEast();
        double x = monster.getX();
        double y = monster.getY();
        monster.updatePosition();
        MonsterDirection direction = monster.getCurrentDirection();
        monster.getHit(1);
        FireBall fireBall = new FireBall(20,20,5,10, monster);
        fireBall.tick();
        //fireBall.draw(this);

        Monster hitMonster = new Monster(1,1,0,"gremlin",30,1,5,0);
        FireBall fireBall2 = new FireBall(13,43,5,10, monster);
        fireBall2.tick();
        delay(100);

        //gameReset();

    }

    @RepeatedTest(3)
    public void HoverTest() {
        Tower tower;
        tower = new Tower(0,0,0,0,0);
        for (int i=0; i<760; i++){
            for (int j=0; j <680; j++){
                DrawTowerRange();
                DrawTowerUpgradeInfo();
            }
        }
    }

    @Test
    public boolean GameResetTest() {
        ManaBar.mana += 1000;
        gameReset();
        boolean compare = (ManaBar.mana == 200);
        delay(2000);
        return (compare);
    }

    @Test
    public void ButtonTests() {
    }

    @Test
    public void ButtonTestsWithT() {
        this.key = 't';
        app.keyPressed();
    }
}
