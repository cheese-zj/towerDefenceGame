package WizardTD.Helpers;

import WizardTD.Monsters.Monster;

import java.util.ArrayList;
import java.util.Random;

import static WizardTD.Helpers.MapCreator.spawnPoints;

public class WaveManager {
    MonsterCreator monsterCreator;
    public ArrayList<Monster[]> waves;
    private int waveCount = 0;

    public WaveManager() {
        this.monsterCreator = new MonsterCreator();
        waves = new ArrayList<>();
    }

    public void WaveSetup(int wavesNum){


        for (int i=0; i < wavesNum+1; i++) {
            Monster[] wave = monsterCreator.CreateMonsters(
                    100,2,100,1,30,30,"Gremlin");
            waves.add(wave);
        }
    }

    public Monster[] WaveRunControl(int startingTime){
        Monster[] output = waves.get(waveCount);
        waveCount++;
        return output;
    }

}
