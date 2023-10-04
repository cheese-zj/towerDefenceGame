package WizardTD.Towers;

import WizardTD.App;
import WizardTD.Monsters.Monster;

public class Tower extends TowerPresets{

    Monster trackedMonster = null;

    public Tower(double x, double y, double towerRange, double firingSpeed, double towerDamage) {
        super(x, y, towerRange, firingSpeed, towerDamage);
    }

    private boolean inRange(Monster monster) {
        double xDis = monster.getX() - this.x*App.CELLSIZE;
        double yDis = monster.getY() - this.y*App.CELLSIZE;
        return Math.pow(xDis,2) + Math.pow(yDis,2) <= Math.pow((towerRange * 32), 2);
    }

    private void detectMonster() {

        if (trackedMonster == null) {
            for (Monster monster : App.runningMonsterList) {
            System.out.println("Detecting");
//            System.out.println(x+" "+y);
//            System.out.println(Math.pow(xDis,2)+" "+Math.pow(yDis,2)+" "+Math.pow((towerRange*32),2));
                if (inRange(monster)) {
                    if (monster.ticking) {
                        trackedMonster = monster;
                        System.out.println("Track!");
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
    private void generateFireBall() {
    }
    @Override
    public void tick() {
        detectMonster();
    }
}
