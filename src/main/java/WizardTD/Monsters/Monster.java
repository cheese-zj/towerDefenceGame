package WizardTD.Monsters;

import WizardTD.Tiles.*;

import WizardTD.App;

public class Monster extends Monsters{

    private int hold =0;
    private int timeStack =0;
    private boolean goVertical = true;
    private int verticalInv = 1;
    private int horizontalInv = 1;

    MonsterPathReader monsterPathReader = new MonsterPathReader();

    public String currentDirection() {
        if (speed != 0) {
            if (goVertical && verticalInv == 1) {
                return "East";
            }
            if (goVertical && verticalInv == -1) {
                return "West";
            }
            if (!goVertical && horizontalInv == 1) {
                return "South";
            }
            if (!goVertical && horizontalInv == -1) {
                return "North";
            }
        }
        return "";
    }


    private int desX = 9*32;
    private int desY = 8*32 + 40;
    public Monster(int x, int y, int speed, String type, int hp, int armour, int mana_gained_on_kill, int spawnTick) {
        super(x, y, speed, type, hp, armour, mana_gained_on_kill, spawnTick);
    }

    //Test, write Setter later:
    //this.desX = ;
    //this.desY = ;

    public void tick() {
        hold++;
        if (hold >= spawnTick) {
            this.timeStack += speed;
            if (goVertical) {
                this.x += speed * verticalInv;
            } else {
                this.y += speed * horizontalInv;
            }

            if (timeStack % 32 == 0) {
                int xDis = this.x - this.desX;
                int yDis = this.y - this.desY;
                System.out.println(xDis);
                if (Math.abs(xDis) < Math.abs(yDis)) {
                    goVertical = false;
                } else {
                    goVertical = true;
                }
                if (xDis > 0) {
                    verticalInv = -1;
                }
                if (yDis > 0) {
                    horizontalInv = -1;
                }
                if (xDis == 6 && yDis == 6) {
                    speed=0;
                }
                monsterPathReader.Read(this);
            }
            //System.out.println(x);
        }
    }
}
