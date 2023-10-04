package WizardTD;

import WizardTD.Managers.InputManager;
import WizardTD.Managers.MapCreator;
import WizardTD.Managers.MonsterCreator;
import WizardTD.Managers.WaveManager;
import WizardTD.Monsters.*;
import WizardTD.Tiles.*;
import WizardTD.Towers.*;

import processing.core.PApplet;
import processing.core.PImage;
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

    public String configPath;
    public static JSONObject json;

    public Random random = new Random();

    private final MapCreator mapCreator;
    private final WaveManager waveManager;

    private final InputManager inputManager;

    public static Path[][] paths;
    public static Grass[][] grasses;

    public static int wizardX;
    public static int wizardY;

    public static boolean isMousePressed;

    public static Monster[] runningMonsterList;

    public static HashSet<Tower> towers;

    public static PImage
            grasspng,
            shrubpng,
            path0png, path1png, path2png, path3png,
            wizard_housepng,
            gremlinpng,
            gremlin1png, gremlin2png, gremlin3png, gremlin4png, gremlin5png,
            tower0png, tower1png, tower2png
            ;


    // Feel free to add any additional methods or attributes you want. Please put classes in different files.
    public App() {
        configPath = "config.json";
        mapCreator = new MapCreator();
        waveManager = new WaveManager();
        inputManager = new InputManager();
        towers = new HashSet<>();
    }

    private void DrawMap() {


        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                //System.out.println(grasses[i][j]);
                if (mapCreator.grasses[i][j] != null){
                    mapCreator.grasses[i][j].draw(this);
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

    private void DrawMonsters() {
        //System.out.println(timer);
        for (int i=0; i< runningMonsterList.length; i++) {
            runningMonsterList[i].tick();
            runningMonsterList[i].draw(this);
        }

    }


    private void DrarTowers() {
        for (Tower tower : towers) {
            tower.tick();
            tower.draw(this);
        }
    }

    public void DrawGUI() {

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
     */
	@Override
    public void setup() {
        json = loadJSONObject(configPath);
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


        //Map related
        mapCreator.CreateMap();
        paths = mapCreator.paths;
        grasses = mapCreator.grasses;
        waveManager.WaveSetup(1);
        this.runningMonsterList = waveManager.WaveRunControl(0);


    }

    /**
     * Receive key pressed signal from the keyboard.
     */
	@Override
    public void keyPressed(){
        
    }

    /**
     * Receive key released signal from the keyboard.
     */
	@Override
    public void keyReleased(){

    }

    @Override
    public void mousePressed(MouseEvent e) {

        isMousePressed = true;
        System.out.println("Mouse Pressed (from App)");
        System.out.println(mouseX/32 + " " + (mouseY-40)/32);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /*@Override
    public void mouseDragged(MouseEvent e) {

    }*/

    /**
     * Draw all elements in the game by current frame.
     */
	@Override
    public void draw() {
        background(152,140,100);
        DrawMap();
        DrawMonsters();

        inputManager.Monitoring(mouseX, mouseY);
        DrarTowers();
        //noLoop();

        mapCreator.wizardHouse.draw(this);
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
