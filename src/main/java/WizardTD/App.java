package WizardTD;

import WizardTD.*;
import WizardTD.Tiles.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.*;
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

    public static PImage
            grasspng,
            shrubpng,
            path0png, path1png, path2png, path3png,
            wizard_housepng
            ;


    // Feel free to add any additional methods or attributes you want. Please put classes in different files.
    public App() {
        this.configPath = "config.json";
        this.mapCreator = new MapCreator();
    }

    public void DrawMap() {
        mapCreator.CreateMap();

        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                //System.out.println(grasses[i][j]);
                if (mapCreator.grasses[i][j] != null){
                    mapCreator.grasses[i][j].draw(this);
                }
                if (mapCreator.shrubs[i][j] != null){
                    mapCreator.shrubs[i][j].draw(this);
                }
                if (mapCreator.paths[i][j] != null){
                    mapCreator.paths[i][j].draw(this);
                }
            }
        }
        mapCreator.grassUnderHouse.draw(this);
        mapCreator.wizardHouse.draw(this);
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
        // Load images during setup
		// Eg:
        // loadImage("src/main/resources/WizardTD/tower0.png");
        // loadImage("src/main/resources/WizardTD/tower1.png");
        // loadImage("src/main/resources/WizardTD/tower2.png");
        grasspng = loadImage("src/main/resources/WizardTD/grass.png");
        shrubpng = loadImage("src/main/resources/WizardTD/shrub.png");
        path0png = loadImage("src/main/resources/WizardTD/path0.png");
        path1png = loadImage("src/main/resources/WizardTD/path1.png");
        path2png = loadImage("src/main/resources/WizardTD/path2.png");
        path3png = loadImage("src/main/resources/WizardTD/path3.png");
        wizard_housepng = loadImage("src/main/resources/WizardTD/wizard_house.png");
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

        noLoop();
        DrawMap();
        //loop();
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
