package WizardTD;

import WizardTD.GameSys.*;
import WizardTD.Helpers.MapCreator;
import WizardTD.Helpers.WaveManager;
import WizardTD.Monsters.*;
import WizardTD.Spell.SpellCaster;
import WizardTD.Tiles.*;
import WizardTD.Towers.*;

import processing.core.*;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.util.*;

public class App extends PApplet{

    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE+TOPBAR;

    public static final int FPS = 60;

    public static int TICK_Multiplier = 1;
    public static boolean GAME_TICKING = true;

    public String configPath;
    public static JSONObject json;
    public static JSONArray wavesInfo;

    public Random random = new Random();

    private final MapCreator mapCreator;
    public WaveManager waveManager;
    private final SpellCaster spellCaster;

    private final ButtonsCollection buttonsCollection;

    public static Path[][] paths;
    public static Grass[][] grasses;

    public static int wizardX;
    public static int wizardY;

    public static boolean isMousePressed;
    protected int timeCounter = 0;

    public static ArrayList<Monster> runningMonsterList;
    public static ArrayList<FireBall> fireBalls;
    public static HashSet<Tower> towers;
    public ManaBar manaBar;

    public static PImage
            grasspng,
            shrubpng,
            path0png, path1png, path2png, path3png,
            wizard_housepng,
            gremlinpng,
            gremlin1png, gremlin2png, gremlin3png, gremlin4png, gremlin5png,
            wormpng, beetlepng,
            tower0png, tower1png, tower2png,
            fireballpng
            ;

    public static PShape topBar, sideBar;
    public static PFont gameFont;

    public static void setWIN(boolean WIN) {
        App.WIN = WIN;
    }
    public static void setLOSE(boolean LOSE) {
        App.LOSE = LOSE;
    }

    protected static boolean WIN = false;
    protected static boolean LOSE = false;
    public static boolean LASTWAVE = false;

    public App() {
        configPath = "config.json";
        mapCreator = new MapCreator();
        waveManager = new WaveManager();
        buttonsCollection = new ButtonsCollection();
        towers = new HashSet<>();
        fireBalls = new ArrayList<>();
        spellCaster = new SpellCaster();
    }

    protected void DrawMap() {
        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                //System.out.println(grasses[i][j]);
                if (grasses[i][j] != null){
                    grasses[i][j].draw(this);
                }
                if (mapCreator.shrubs[i][j] != null){
                    mapCreator.shrubs[i][j].draw(this);
                }
                if (paths[i][j] != null){
                    paths[i][j].draw(this);
                }
            }
        }
        mapCreator.grassUnderHouse.draw(this);
    }

    protected void DrawMonsters() {
        if (!WIN) {
            boolean waveDone = true;
            for (int i = 0; i < runningMonsterList.size(); i++) {
                runningMonsterList.get(i).tick();
                runningMonsterList.get(i).update(this);
                if (runningMonsterList.get(i).selfDelete) {
                    runningMonsterList.remove(i);
                }
                if (!runningMonsterList.isEmpty()) {
                    waveDone = waveDone && runningMonsterList.get(i).dead;
                }

            }
            if (waveDone && LASTWAVE){
                setWIN(true);
            }
        }
    }

    protected void DrawTowerUpgradeInfo() {
        for (Tower tower : towers) {
            if (mouseX - (1 + tower.getX()) * 32 <= 0 && mouseX - (1 + tower.getX()) * 32 >= -32
                    && mouseY - ((1 + tower.getY()) * 32 + 40) <= 0 && mouseY - ((1 + tower.getY()) * 32 + 40) >= -32) {
                tower.drawUpgrade(this,tower.rangeCost,tower.fireCost,tower.dmgCost);
            }
        }
    }
    protected void DrawTowerRange() {
        for (Tower tower : towers) {
            tower.rangeDisplay(this,mouseX,mouseY);
        }
    }
    protected void DrawTowers() {
        for (Tower tower : towers) {
            tower.monitoring(mouseX,mouseY);
            tower.tick();
            tower.draw(this, mouseX, mouseY);
        }
    }
    protected void DrawFireballs() {
        for (int i = 0; i < fireBalls.size(); i++){
            fireBalls.get(i).tick();
            fireBalls.get(i).draw(this);
        }
    }
    protected void DrawGUI() {

        if (GAME_TICKING) timeCounter+=TICK_Multiplier;
        float startsIn = -1;

        if (waveManager.waveCount < waveManager.wavePauseInfoMap.size()) {
            startsIn = waveManager.wavePauseInfoMap.get(waveManager.waveCount) - (timeCounter / 60);
        }

        shape(topBar);
        shape(sideBar);
        manaBar.drawManaBar(this);
        for (int i = 0; i<buttonsCollection.buttonsArray.size(); i++) {
            buttonsCollection.buttonsArray.get(i).draw(this);
            buttonsCollection.buttonsArray.get(i).functionality(this);
        }

        textFont(gameFont,25);
        if (!LASTWAVE) {
            text("Wave " + (1 + waveManager.waveCount), 10, 30);
        } else if (!WIN) {
            text("Last Wave!", 10, 30);
        }
        if (startsIn >= 0) {
            text("starts: " + (int) (startsIn), 110, 30);
        } else {
            if (!WIN) {
                waveManager.waveCount++;
                waveManager.WaveRunControl();
                timeCounter = 0;
            }
        }
        if (WIN) {
            fill(50,100,50);
            strokeWeight(4);
            rect(125,285,400,80);
            strokeWeight(1);
            fill(color(100, 255, 100));
            textFont(gameFont,40);
            text("YOU WIN!!!", 220,340);
            fill(0);
        }
        if (LOSE) {
            fill(100,50,50);
            strokeWeight(4);
            rect(125,285,400,80);
            strokeWeight(1);
            fill(color(255, 100, 100));
            textFont(gameFont,40);
            text("YOU LOSE!", 227,337);
            textFont(gameFont,12);
            text("Press R to restart", 280,355);
            fill(0);
        }
    }

    protected void DrawSpell() {
        if (SpellCaster.coolDown > 0 ) SpellCaster.coolDown--;
        spellCaster.DrawParticles(this);
    }

    /**
     * Reset the game
     * Resetting all the variables that were set from the beginning of the game
     * Clearing all the running lists of Objects in order to get rid of all the created Objects:
     * Eg: Towers, Monsters, Fireballs
     */
    public void gameReset() {

        manaBar.manaBarReset();
        towers.clear();
        fireBalls.clear();
        for (Monster monster : runningMonsterList){
            monster.ticking = false;
            monster.dead = true;
        }

        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                if (grasses[i][j] != null){
                    grasses[i][j].setOccupied(false);
                }
            }
        }
        waveManager = new WaveManager();
        waveManager.WaveSetup();
        waveManager.waveCount=0;
        waveManager.WaveRunControl();
        timeCounter = 0;
        Inventory.spellCount = json.getInt("initial_spell_amount");

        GAME_TICKING = true;
        LOSE = false;
        WIN = false;
    }

    /**
     * Initialise the setting of the window size.
     */
	@Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
     * Also initialise helpers to deal with various Objects.
     */
	@Override
    public void setup() {
        json = loadJSONObject(configPath);
        wavesInfo = json.getJSONArray("waves");
        frameRate(FPS);

        //images loading
        grasspng = loadImage("src/main/resources/WizardTD/grass.png");
        shrubpng = loadImage("src/main/resources/WizardTD/shrub.png");
        wizard_housepng = loadImage("src/main/resources/WizardTD/wizard_house.png");
        tower0png = loadImage("src/main/resources/WizardTD/tower0.png");
        tower1png = loadImage("src/main/resources/WizardTD/tower1.png");
        tower2png = loadImage("src/main/resources/WizardTD/tower2.png");
        { // paths
            path0png = loadImage("src/main/resources/WizardTD/path0.png");
            path1png = loadImage("src/main/resources/WizardTD/path1.png");
            path2png = loadImage("src/main/resources/WizardTD/path2.png");
            path3png = loadImage("src/main/resources/WizardTD/path3.png");
        }
        gremlinpng = loadImage("src/main/resources/WizardTD/gremlin.png");
        { // gremlin killed
            gremlin1png = loadImage("src/main/resources/WizardTD/gremlin1.png");
            gremlin2png = loadImage("src/main/resources/WizardTD/gremlin2.png");
            gremlin3png = loadImage("src/main/resources/WizardTD/gremlin3.png");
            gremlin4png = loadImage("src/main/resources/WizardTD/gremlin4.png");
            gremlin5png = loadImage("src/main/resources/WizardTD/gremlin5.png");
        }
        wormpng = loadImage("src/main/resources/WizardTD/worm.png");
        beetlepng = loadImage("src/main/resources/WizardTD/beetle.png");
        fireballpng = loadImage("src/main/resources/WizardTD/fireball.png");

        {//GUI related
            topBar = createShape(RECT, 0,0,760,40);
            sideBar = createShape(RECT, 640,40,120,640);
            topBar.setFill(color(150,140,115));
            topBar.setStroke(false);
            sideBar.setFill(color(150,140,115));
            sideBar.setStroke(false);
            buttonsCollection.generate(this);
            gameFont = createFont("Cambridge",16,true);
        }

        {//Map related
            mapCreator.CreateMap();
            paths = mapCreator.paths;
            grasses = mapCreator.grasses;
        }

        manaBar = new ManaBar();
        waveManager = new WaveManager();

        waveManager.WaveSetup();

        runningMonsterList = new ArrayList<Monster>();
        waveManager.WaveRunControl();

    }

    /**
     * Receive key pressed signal from the keyboard.
     * Comparing signal to char variables assigned to each button class in a button collection to trigger them
     */
	@Override
    public void keyPressed(){
        for (Buttons button : buttonsCollection.buttonsArray) {
            if (this.key == button.triggerCode || this.key == Character.toUpperCase(button.triggerCode)){
                button.monitorKey();
            }
        }
        if (LOSE) {
            if (this.key == 'r' || this.key == 'R'){
                gameReset();
            }
        }
    }


    /**
     * @param e
     * The whole mousePressed logic is now based on the variable
     * This is distributing works, so they are to be done by each clickable classes instead here
     * PREVENTING OVER CLICKING!! WORKS WELL!!
     */
    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;

    }


    /**
     * Draw all elements in the game by current frame.
     */
	@Override
    public void draw() {

        //background(152,140,100);
        DrawMap();
        DrawMonsters();

        DrawTowers();
        DrawFireballs();
        //noLoop();

        mapCreator.wizardHouse.draw(this);
        DrawTowerRange();
        DrawSpell();
        DrawGUI();
        DrawTowerUpgradeInfo();
        //inputManager.Monitoring(mouseX, mouseY);

        if (ManaBar.mana <= 0) {
            ManaBar.mana = 0;
            GAME_TICKING = false;
            setLOSE(true);
        }
        isMousePressed = false;
    }

    public static void main(String[] args) {
        PApplet.main("WizardTD.App");
    }

    /**
     * Source: https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * @param pimg The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */

}
