package WizardTD.Managers;

import WizardTD.App;
import WizardTD.Monsters.Monster;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

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
            Monster[] wave = monsterCreator.CreateMonsters(1, "Gremlin");
            waves.add(wave);
        }
    }

    public Monster[] WaveRunControl(int startingTime){
        Monster[] output = waves.get(waveCount);
        waveCount++;
        return output;
    }

}
