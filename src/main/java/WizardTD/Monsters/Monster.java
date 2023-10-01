package WizardTD.Monsters;

import WizardTD.Tiles.*;

import WizardTD.App;

import WizardTD.MapCreator;

import java.util.Map;

public class Monster extends Monsters{


    private boolean goVertical = true;
    private int verticalInv = 1;
    private int horizontalInv = 1;

    private Path[][] deepCopyPaths(Path[][] original) {
        if (original == null) {
            return null;
        }

        final Path[][] result = new Path[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }


    protected Path[][] pathsMem = deepCopyPaths(App.paths);

    MonsterPathReader monsterPathReader = new MonsterPathReader();

    public MonsterDirection getCurrentDirection() {
        if (speed != 0) {
            if (goVertical && verticalInv == 1) {return MonsterDirection.EAST;}
            if (goVertical && verticalInv == -1) {return MonsterDirection.WEST;}
            if (!goVertical && horizontalInv == 1) {return MonsterDirection.SOUTH;}
            if (!goVertical && horizontalInv == -1) {return MonsterDirection.NORTH;}
        }
        return null;
    }

    private final int desX = App.wizardX;
    private final int desY = App.wizardY;
    public Monster(double x, double y, double speed, String type, int hp, int armour, int mana_gained_on_kill, int spawnTick) {
        super(x, y, speed, type, hp, armour, mana_gained_on_kill, spawnTick);
    }

    public void goNorth() {
        this.goVertical = false;
        this.horizontalInv = -1;
    }
    public void goSouth() {
        this.goVertical = false;
        this.horizontalInv = 1;
    }
    public void goWest() {
        this.goVertical = true;
        this.verticalInv = -1;
    }
    public void goEast() {
        this.goVertical = true;
        this.verticalInv = 1;
    }

    private double hold =0;
    private double timeStack =0;
    public void tick() {
        if (ticking) {
            this.hold++;
            if (this.hold >= this.spawnTick) {

                if (this.timeStack % App.CELLSIZE == 0) {

                    this.timeStack = 0;

                    this.monsterPathReader.Read(this);

                    if (this.x - this.desX == 6 && this.y - this.desY == 6) {
                        speed=0;
                        ticking = false;
                    }
                }

                this.timeStack += this.speed;

                if (goVertical) {
                    this.x += this.speed * this.verticalInv;
                } else {
                    this.y += this.speed * this.horizontalInv;

                }

            }
        }
    }
}
