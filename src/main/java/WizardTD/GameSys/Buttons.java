package WizardTD.GameSys;
import WizardTD.App;
import WizardTD.Helpers.CheckHover;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShape;

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


    public void monitorKey() {
        checked = !checked;
    }

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
    public abstract void functionality(App app);

}
