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

    public Grid grid = new Grid();


    public static PImage
            grasspng,
            shrubpng,
            path0png, path1png, path2png, path3png,
            wizard_housepng
            ;


    // Feel free to add any additional methods or attributes you want. Please put classes in different files.
    public App() {
        this.configPath = "config.json";
    }

    public void CreateMap() {
        String[][] levelArray = grid.LevelReader();
        Grass[][] grasses = new Grass[20][20];
        Shrub[][] shrubs = new Shrub[20][20];
        Path[][] paths = new Path[20][20];
        //From Grid class method, reading the level txt file
        //And detect all the spaces for grass tiles, appending them into a Grass 2D Array
        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                if (levelArray[i][j] != null && levelArray[i][j].equals(" ")) {
                    grasses[i][j] = new Grass(i*32,j*32+40, true, false);
                    grasses[i][j].setSprite(grasspng);
                }
                if (levelArray[i][j] != null && levelArray[i][j].equals("S")) {
                    shrubs[i][j] = new Shrub(i*32,j*32+40, false, false);
                    shrubs[i][j].setSprite(shrubpng);

                }
                if (levelArray[i][j] != null && levelArray[i][j].equals("X")) {
                    paths[i][j] = new Path(i*32,j*32+40, false, true);
                    paths[i][j].setSprite(path0png);
                }
            }
        }
        //Drawing out all the grasses from the Grass 2D-Array
        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                //System.out.println(grasses[i][j]);
                if (grasses[i][j] != null){
                    grasses[i][j].draw(this);
                }
                if (shrubs[i][j] != null){
                    shrubs[i][j].draw(this);
                }
                if (paths[i][j] != null){
                    paths[i][j].draw(this);
                }
            }
        }
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
        CreateMap();
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
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = this.createImage(newWidth, newHeight, RGB);
        //BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }

        return result;
    }
}
