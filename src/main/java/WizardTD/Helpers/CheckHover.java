package WizardTD.Helpers;

import WizardTD.App;
import WizardTD.GameSys.ButtonClasses.*;

public interface CheckHover {
    default boolean checkHover(int mouseX, int mouseY, float x, float y) {
        if (mouseX <= x+48 && mouseX >= x && mouseY <= y+48 && mouseY >= y){
            return true;
        }
        return false;
    }
    default boolean checkHoverTower(int mouseX, int mouseY, float x, float y) {
        if (
            mouseX-(1+x)*App.CELLSIZE<=0
            && mouseX-(1+x)*App.CELLSIZE>=-App.CELLSIZE
            && mouseY-((1+y)*App.CELLSIZE+App.TOPBAR)<=0
            && mouseY-((1+y)*App.CELLSIZE+App.TOPBAR)>=-App.CELLSIZE
        ) {
            return true;
        }
        return false;
    }

    default boolean checkHoverInventorySpec(int mouseX, int mouseY, float x, float y) {
        if (mouseX >= x && mouseX <= x+70
                && mouseY >= y+60 && mouseY <= y+90){
            return true;
        }
        return false;
    }

    default boolean hoverInMap(int mouseX, int mouseY) {
        if (mouseX/ App.CELLSIZE < 20 && (mouseY-App.TOPBAR)/App.CELLSIZE < 20) {
            return true;
        }
        return false;
    }

}
