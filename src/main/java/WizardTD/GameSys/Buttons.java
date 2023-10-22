package WizardTD.GameSys;
import WizardTD.App;
import WizardTD.Helpers.CheckHover;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShape;

/**
 * Abstract representation of a button in the WizardTD game.
 * <p>
 * The Buttons class provides the foundation for all clickable buttons in the game.
 * It handles button visuals, hover effects, click detection, and defines the core functionalities
 * associated with the button.
 * </p>
 */
public abstract class Buttons implements CheckHover {

    protected float x;
    protected float y;
    protected String buttonText;
    protected String title;
    protected PShape shapeRec;
    protected PShape hoverBase;
    protected PShape selectBase;
    protected PFont titleFont;
    protected boolean checked = false;
    public char triggerCode;

    /**
     * Constructs a new Button with the specified position, text, title, and trigger code.
     *
     * @param x X-coordinate of the button.
     * @param y Y-coordinate of the button.
     * @param app The App instance to interface with the game's rendering system.
     * @param buttonText Text displayed on the button.
     * @param title Title of the button.
     * @param triggerCode Key code that triggers the button's action.
     */
    public Buttons(float x, float y, App app, String buttonText, String title, char triggerCode){
        this.x = x;
        this.y = y;
        this.buttonText = buttonText;
        this.title = title;
        this.triggerCode = triggerCode;
        titleFont = app.createFont("Arial",16,true);
        shapeRec = app.createShape(PConstants.RECT, x, y, 48,48);
        shapeRec.setFill(false);
        shapeRec.setStrokeWeight(2);
        app.fill(0);
        hoverBase = app.createShape(PConstants.RECT, x, y, 48,48);
        selectBase = app.createShape(PConstants.RECT, x, y, 48,48);
        hoverBase.setFill(app.color(255,255,255,100));
        selectBase.setFill(app.color(240,247,0));
    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Toggles the checked state of the button.
     */
    public void monitorKey() {
        checked = !checked;
    }

    /**
     * Draws the button on the game's screen, displaying hover or selected effects as necessary.
     *
     * @param app The App instance to interface with the game's rendering system.
     */
    public void draw(App app) {
        if (checkHover(app.mouseX,app.mouseY, this.x, this.y)) {
            app.shape(hoverBase);
            if (App.isMousePressed){
                checked = !checked;
            }
        }
        if (checked) {
            app.shape(selectBase);
        }
        app.shape(shapeRec);
        app.fill(0);
        app.textFont(titleFont,24);
        app.text(buttonText,x+5,y+25);
        app.textFont(titleFont, 11.5F);
        app.text(title, x+48+5, y+13);

    }

    /**
     * Defines the core functionality of the button.
     * <p>
     * This method is intended to be overridden by subclasses to provide
     * specific functionality when the button is pressed or activated.
     * </p>
     *
     * @param app The App instance for accessing game-related functionalities.
     */
    public abstract void functionality(App app);

}
