package WizardTD.GameSys;

import WizardTD.App;
import WizardTD.Towers.Tower;
import WizardTD.Towers.TowerBuilder;

public class T extends Buttons{

    TowerBuilder towerBuilder;
    public T(float x, float y, App app) {
        super(x, y, app,"T", "Build\ntower");
        this.towerBuilder = new TowerBuilder();
    }
    private void MouseBuildTower(int mouseX, int mouseY) {

        if (mouseX/32 < 20 && (mouseY-40)/32 < 20) {
            if (
                    App.grasses[mouseX/32][(mouseY-40) / 32] != null &&
                    !App.grasses[mouseX/32][(mouseY-40) / 32].isOccupied()
            ) {
                towerBuilder.BuildTower(mouseX/32, (mouseY-40)/32);
                App.grasses[mouseX/32][(mouseY-40) / 32].setOccupied();
            }
        }
    }
    @Override
    public void functionality(App app) {
        if (App.isMousePressed && checked){
            MouseBuildTower(app.mouseX, app.mouseY);
            App.isMousePressed = false;
        } else if (App.isMousePressed) {
            App.isMousePressed = false;
        }
    }
}
