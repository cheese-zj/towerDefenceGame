package WizardTD.Helpers;

import WizardTD.App;
import WizardTD.GameSys.ButtonClasses.Inventory;
import WizardTD.Monsters.Monster;
import WizardTD.Monsters.MonsterCreator;
import processing.data.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Manages the creation and running of waves of monsters in the game.
 * <p>
 * This class handles the setup of waves based on provided wave information,
 * and can control the running of these waves during gameplay.
 * </p>
 */
public class WaveManager {
    MonsterCreator monsterCreator;
    public ArrayList<Monster[]> waves;
    public HashMap<Integer,Float> wavePauseInfoMap;
    private HashMap<Integer,Integer> waveDuration;
    public int waveCount = 0;

    /**
     * Constructs a new WaveManager with default configurations.
     */
    public WaveManager() {
        this.monsterCreator = new MonsterCreator();
        waves = new ArrayList<>();
        wavePauseInfoMap = new HashMap<>();
        waveDuration = new HashMap<>();
        waveDuration.put(-1,0);
    }

    /**
     * Sets up waves of monsters based on the predefined wave information.
     * <p>
     * This method processes the wave information to create respective waves of monsters,
     * each with its own set of properties such as type, health, speed, and so on.
     * </p>
     */
    public void WaveSetup(){
        for (int i=0; i < App.wavesInfo.size(); i++) {
            JSONObject waveObj = App.wavesInfo.getJSONObject(i);
            JSONObject monsterInfo = waveObj.getJSONArray("monsters").getJSONObject(0);

            int duration = waveObj.getInt("duration");
            waveDuration.put(i,duration);

            float preWavePause = waveObj.getFloat("pre_wave_pause") + waveDuration.get(i-1);
            wavePauseInfoMap.put(i,preWavePause);

            String type = monsterInfo.getString("type");
            int hp = monsterInfo.getInt("hp");
            float speed = monsterInfo.getFloat("speed");
            float armour = monsterInfo.getFloat("armour");
            int manaGOK = monsterInfo.getInt("mana_gained_on_kill");
            int quantity = monsterInfo.getInt("quantity");
            int spawnTick = (int)(((double)duration/(double)quantity)*60);

            Monster[] wave = monsterCreator.CreateMonsters(
                    quantity,speed,hp,armour,manaGOK,spawnTick,preWavePause,type);

            waves.add(wave);
        }
    }

    /**
     * Controls the running of waves during gameplay.
     * <p>
     * This method determines which wave of monsters should be currently active and
     * running in the game. It also handles scenarios when all waves have been run.
     * </p>
     */
    public void WaveRunControl(){
        if (waveCount<waves.size()) {
            App.runningMonsterList.addAll(Arrays.asList(waves.get(waveCount)));
            Inventory.spellCount+=waveCount+1;
        } else {
            App.LASTWAVE = true;
        }
    }

}
