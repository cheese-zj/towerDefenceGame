package WizardTD.Towers;

import WizardTD.App;
import WizardTD.GameSys.ManaBar;
import WizardTD.GameSys.U1;
import WizardTD.GameSys.U2;
import WizardTD.GameSys.U3;
import WizardTD.Monsters.Monster;
import WizardTD.Monsters.MonsterPresets;
import processing.core.PShape;

public class Tower extends TowerPreset {

    Monster trackedMonster = null;

    public Tower(double x, double y, int towerRangeLv, int towerFireLv, int towerDamageLv) {
        super(x, y, towerRangeLv, towerFireLv, towerDamageLv);
    }

    public int rangeCost = 0;
    public int fireCost = 0;
    public int dmgCost = 0;

    protected boolean inRange(Monster monster) {
        double xDis = monster.getX()+6 - this.x*App.CELLSIZE;
        double yDis = monster.getY()+6 - this.y*App.CELLSIZE;
        return (int)(Math.pow(xDis,2) + Math.pow(yDis,2)) <= Math.pow(towerRange+towerRangeLv*32, 2)+52;
    }
    protected void detectMonster() {
        if (trackedMonster == null) {
            for (Monster monster : App.runningMonsterList) {
            //System.out.println("Detecting");
                if (inRange(monster)) {
                    if (monster.ticking && monster.canTrack && App.GAME_TICKING) {
                        trackedMonster = monster;
                        break;
                        //System.out.println("Track!");
                    }
                }
            }
        } else {
            if (!inRange(trackedMonster) || !trackedMonster.ticking) {
                trackedMonster = null;
            }
        }
    }
    int fireCounter = 0;
    protected void generateFireBall() {
        if (trackedMonster!=null) {
            App.fireBalls.add(
                    new FireBall((this.x)*32+16, (this.y)*32+16+40, 5,
                            (float) (towerDamage+0.5*towerDamage*towerDmgLv), trackedMonster));
        }
    }


    public void monitoring(int mouseX, int mouseY) {
        if (mouseX-(1+this.x)*32<=0 && mouseX-(1+this.x)*32>=-32
                && mouseY-((1+this.y)*32+40)<=0 && mouseY-((1+this.y)*32+40) >=-32){
// Increment counter based on towers' levels
            if (U1.U1checked) rangeCost = (towerRangeLv+2)*10;
            if (U2.U2checked) fireCost = (towerFireLv+2)*10;
            if (U3.U3checked) dmgCost = (towerDmgLv+2)*10;
// Check the mana condition and upgrade accordingly
            if (App.isMousePressed) {
                if (ManaBar.mana > rangeCost && U1.U1checked) {
                    towerRangeLv++; ManaBar.mana -= rangeCost;}
                if (ManaBar.mana > fireCost && U2.U2checked) {
                    towerFireLv++; ManaBar.mana -= fireCost;}
                if (ManaBar.mana > dmgCost && U3.U3checked) {
                    towerDmgLv++; ManaBar.mana -= dmgCost;}
            }
        }
    }

    @Override
    public void tick() {
        {
            detectMonster();
            if (fireCounter == 0 && App.GAME_TICKING) {
                generateFireBall();
            }
            fireCounter++;
            fireCounter = (int) (fireCounter % ((60/(firingSpeed+towerFireLv*0.5)) / App.TICK_Multiplier));
        }
    }
}
