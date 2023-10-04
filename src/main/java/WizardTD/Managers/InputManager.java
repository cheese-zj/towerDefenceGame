package WizardTD.Managers;
import WizardTD.App;
import WizardTD.Towers.*;

public class InputManager{
    TowerBuilder towerBuilder;

    public InputManager() {
        this.towerBuilder = new TowerBuilder();
    }

    private void MouseBuildTower(int mouseX, int mouseY) {

        if (mouseX/32 < 20 && (mouseY-40)/32 < 20) {
            System.out.println(mouseX+" "+mouseY);
            if (App.grasses[mouseX/32][(mouseY-40) / 32] != null) {
                System.out.println("is Grass from Monitoring");
                towerBuilder.BuildTower(mouseX/32, (mouseY-40)/32);
            }
        }

    }


    public void Monitoring(int mouseX, int mouseY) {

        if (App.isMousePressed){

            MouseBuildTower(mouseX, mouseY);

            App.isMousePressed = false;
        }
    }
}
