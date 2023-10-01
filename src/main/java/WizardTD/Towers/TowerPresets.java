package WizardTD.Towers;

import processing.core.PImage;

public abstract class TowerPresets {
    protected double x;
    protected double y;
    protected double speed;
    protected String type;
    protected int hp;
    protected int armour;
    protected int mana_gained_on_kill;
    private PImage sprite;
    protected boolean ticking = true;
}
