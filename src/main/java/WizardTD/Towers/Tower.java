package WizardTD.Towers;

import WizardTD.App;
import WizardTD.GameSys.ButtonClasses.*;
import WizardTD.Monsters.Monster;

public class Tower extends TowerPreset implements costCheck {

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
                if (inRange(monster)) {
                    if (monster.ticking && monster.canTrack && App.GAME_TICKING) {
                        trackedMonster = monster;
                        break;
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
        if (checkHoverTower(mouseX,mouseY,(float) this.x,(float) this.y)) {
            // Increment counter based on towers' levels
            towerCostManage(this);
            // Check the mana condition and upgrade accordingly
            if (App.isMousePressed) {
                towerConsume(this);
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
