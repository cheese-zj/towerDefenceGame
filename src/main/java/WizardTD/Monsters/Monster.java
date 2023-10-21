package WizardTD.Monsters;

import WizardTD.Tiles.*;

import WizardTD.App;
import WizardTD.Towers.FireBall;

import java.util.Objects;

import static WizardTD.Helpers.MapCreator.spawnPoints;
import static WizardTD.Monsters.MonsterCreator.getRandomCoordinates;

public class Monster extends MonsterPresets implements MonsterPathReader {


    private boolean goVertical = true;
    private int verticalInv = 1;
    private int horizontalInv = 1;


    protected Path[][] pathsMem = App.paths;


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
    public Monster(double x, double y, double speed, String type, float hp,
                   float armour, int mana_gained_on_kill, float spawnTick) {
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

    private double adjustPosition(double currentPosition, double speed, int inv) {
        if ((currentPosition)%32!=0) {
            if (inv == 1) {
                if ((int) (speed + currentPosition) / 32 > (int) currentPosition / 32) {
                    return (float) ((speed + currentPosition) % 32);
                }
            } else if (inv == -1) {
                if ((int) (-speed + currentPosition) / 32 < (int) currentPosition / 32) {
                    return (float) (-speed + ((currentPosition) % 32));
                }
            }
        }
        return 0;
    }
    public void updatePosition() {
        if (goVertical) {
            this.x += (this.speed*App.TICK_Multiplier) * this.verticalInv -
                    adjustPosition(this.x, (this.speed*App.TICK_Multiplier), this.verticalInv);
            if (this.x %32 == 0){
                this.x = (int) this.x;

            }

        } else {
            this.y += (this.speed*App.TICK_Multiplier) * this.horizontalInv -
                    adjustPosition(this.y, (this.speed*App.TICK_Multiplier), this.horizontalInv);
            if (this.y %32 == 0){
                this.y = (int) this.y;
            }
        }
    }

    float hold = 0;

    public void respawnAfterHit() {
        ticking = false;
        int posFixSpawnX = 0, posFixSpawnY = 0;
        int[] coordinates = getRandomCoordinates(spawnPoints);
        int gridX = coordinates[0];
        int gridY = coordinates[1];
        {
            if (gridX == 0) posFixSpawnX = -31;
            else if (gridX == 19) posFixSpawnX = 31;
            else if (gridY == 0) posFixSpawnY = -31;
            else if (gridY == 19) posFixSpawnY = 31;
        }
        {
            if (gridX == 0) this.goEast();
            else if (gridX == 19) this.goWest();
            else if (gridY == 0) this.goSouth();
            else if (gridY == 19) this.goNorth();
        }

        x = (gridX)*(App.CELLSIZE) + posFixSpawnX;
        y = (gridY)*(App.CELLSIZE) + posFixSpawnY;

        for (FireBall fireBall : App.fireBalls) {
            if (fireBall.target.equals(this)){
                fireBall.ticking = false;
            }
        }

        ticking = true;
    }

    public void tick() {
        if (App.GAME_TICKING && ticking) {
            this.hold+= (float) App.TICK_Multiplier;
            if (this.hold >= this.spawnTick) {
                canTrack = true;
                if (this.x - this.desX <= 6 && this.x - this.desX >= 0.0 &&
                        this.y - this.desY <= 6 && this.y - this.desY >= 0.0) {
                    this.ticking = false;
                    canTrack = false;
                    hitWizard();
                    respawnAfterHit();
                }
                if (goVertical) {
                    if ((int)this.x % 32 ==0) {
                        Read(this);
                    }
                } else {
                    if ((int)(this.y) % 32 ==0) {
                        Read(this);
                    }
                }
                updatePosition();
            }
        }
    }
}
