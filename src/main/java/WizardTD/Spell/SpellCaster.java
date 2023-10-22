package WizardTD.Spell;

import WizardTD.App;
import WizardTD.GameSys.*;
import WizardTD.GameSys.ButtonClasses.*;
import WizardTD.Helpers.CheckHover;
import WizardTD.Monsters.Monster;

import java.util.ArrayList;

public class SpellCaster implements CheckHover {
   public static int coolDown = 0;
    ArrayList<Particle> particles = new ArrayList<>();
    public void DrawParticles(App app) {
        if (hoverInMap(app.mouseX, app.mouseY) && App.isMousePressed && Inventory.InvChecked) {
            if (coolDown == 0) {
                for (int i = 0; i < 15; i++) {
                    particles.add(new Particle(app.mouseX, app.mouseY, app.random));
                }
                coolDown = 25;
                Inventory.spellCount --;
            }
            for (Monster monster : App.runningMonsterList) {
                if (monster.getX()<=app.mouseX+32*3 && monster.getX()>=app.mouseX-32*3
                && monster.getY()+40<=app.mouseY+32*3 && monster.getY()+40>=app.mouseY-32*3 && monster.canTrack){
                    if (Inventory.SpellChoice.equals(SpellType.POISON)) monster.getPoisoned(10);
                    if (Inventory.SpellChoice.equals(SpellType.SLOW)) monster.getStonised();
                    if (Inventory.SpellChoice.equals(SpellType.BLAST)) monster.getBlasted();
                }
                if (Inventory.SpellChoice.equals(SpellType.PROTECTION)) ManaBar.manaBarGetShielded();
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
            particles.get(i).display(app);
            if (particles.get(i).lifespan <= 0){
                particles.remove(i);
            }
        }
    }
}
