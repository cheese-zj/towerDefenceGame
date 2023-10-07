package WizardTD.Towers;

import WizardTD.App;
import WizardTD.Monsters.Monster;

public class Tower extends TowerPreset {

    Monster trackedMonster = null;

    public Tower(double x, double y, double towerRange, double firingSpeed, double towerDamage) {
        super(x, y, towerRange, firingSpeed, towerDamage);
    }

    private boolean inRange(Monster monster) {
        double xDis = monster.getX()+6 - this.x*App.CELLSIZE;
        double yDis = monster.getY()+6 - (this.y*App.CELLSIZE);
        //System.out.println(Math.pow(xDis,2)+" "+Math.pow(yDis,2)+" "+Math.pow((towerRange*32),2));
        return (int)(Math.pow(xDis,2) + Math.pow(yDis,2)) <= Math.pow(towerRange, 2)+52;

    }

    private void detectMonster() {

        if (trackedMonster == null) {
            for (Monster monster : App.runningMonsterList) {
            //System.out.println("Detecting");
                if (inRange(monster)) {
                    if (monster.ticking && App.GAME_TICKING) {
                        trackedMonster = monster;
                        //System.out.println("Track!");
                    }
                }
            }
        } else {
            //System.out.println(!inRange(trackedMonster));
            if (!inRange(trackedMonster) || !trackedMonster.ticking) {
                trackedMonster = null;
            }
        }
    }
    int fireCounter = 0;
    private void generateFireBall() {
        if (trackedMonster!=null) {
            App.fireBalls.add(
                    new FireBall((this.x)*32+16, (this.y)*32+16+40, 5, (float) towerDamage, trackedMonster));
        }
    }
    @Override
    public void tick() {
        detectMonster();
        if (fireCounter==0 && App.GAME_TICKING) {
            generateFireBall();
        }
        fireCounter++;
        fireCounter= (int) (fireCounter%(firingSpeed*60/App.TICK_Multiplier));
    }
}
