package WizardTD.Monsters;

import WizardTD.Tiles.*;

import WizardTD.App;

public class Monster extends MonsterPresets {


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

    private double adjustPosition(double currentPosition, double speed, int inv) {
        if (((int)currentPosition)%32!=0) {
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
            this.x += this.speed * this.verticalInv - adjustPosition(this.x, this.speed, this.verticalInv);
            if ((int)this.x %32 == 0){
                this.x = (int) this.x;
            }
        } else {
            this.y += this.speed * this.horizontalInv - adjustPosition(this.y, this.speed, this.horizontalInv);
            if ((int)this.y %32 == 0){
                this.y = (int) this.y;
            }
        }
    }

    double hold = 0;
    public void tick() {
        if (ticking) {
            this.hold++;
            if (this.hold >= this.spawnTick) {

                if (this.x - this.desX <= 6 && this.x - this.desX >= 0.0 &&
                        this.y - this.desY <= 6 && this.y - this.desY >= 0.0
                ) {
                    speed=0;
                    ticking = false;
                }
                if (goVertical) {
                    if (this.x % 32 ==0) {
                        //updatePosition();
                        this.monsterPathReader.Read(this);
                    }
                } else {
                    if ((this.y) % 32 ==0) {
                        //updatePosition();
                        this.monsterPathReader.Read(this);
                    }
                }

                updatePosition();

                //this.timeStack += this.speed;

//                int fixPos = 0;

//                if (goVertical) {
//                    if (this.verticalInv == 1) {
//                        if ((int)(this.speed + this.x)/32 > (int)this.x/32){
//                            System.out.println(this.x);
//                            fixPos = (int)((this.speed + this.x)%32 * this.verticalInv);
//                        }
//                    } else {
//                        if ((int)(-this.speed + this.x)/32 < (int)this.x/32){
//                            System.out.println(this.x);
//                            System.out.println("update");
//                            fixPos = (int) -((this.speed + this.x)%32 * this.verticalInv);
//                        }
//                    }
//                    this.x += (int) this.speed * this.verticalInv - fixPos;
//                    System.out.println(this.x);
//                } else {
//                    if (this.horizontalInv == 1) {
//                        if ((int)(this.speed + this.y)/32 > (int)this.y/32) {
//                            System.out.println(this.y);
//                            fixPos = (int)((this.speed + this.y)%32 * this.horizontalInv);
//                        }
//                    } else {
//                        if ((int)(-this.speed + this.y)/32 < (int)this.y/32){
//                            System.out.println(this.y);
//                            fixPos = (int) -((this.speed + this.y)%32 * this.horizontalInv);
//                        }
//                    }
//                    this.y += (int) this.speed * this.horizontalInv - fixPos;
//                }

            }
        }
    }
}
