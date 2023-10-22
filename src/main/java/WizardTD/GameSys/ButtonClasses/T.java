package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;
import WizardTD.Towers.TowerBuilder;

public class T extends Buttons {

    TowerBuilder towerBuilder;
    public T(float x, float y, App app) {
        super(x, y, app,"T", "Build\ntower",'t');
        this.towerBuilder = new TowerBuilder();
    }
    protected void MouseBuildTower(int mouseX, int mouseY) {

        if (hoverInMap(mouseX,mouseY)) {
            if (App.grasses[mouseX/32][(mouseY-40) / 32] != null &&
                    !App.grasses[mouseX/32][(mouseY-40) / 32].isOccupied()) {
                towerBuilder.BuildTower(mouseX/32, (mouseY-40)/32);
            }
        }
    }
    @Override
    public void functionality(App app) {
        if (App.isMousePressed && checked && !Inventory.InvChecked){
            MouseBuildTower(app.mouseX, app.mouseY);
        }
    }
}
