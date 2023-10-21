package WizardTD.Helpers;

public interface CheckHover {
    default boolean checkHover(int mouseX, int mouseY, float x, float y) {
        if (mouseX <= x+48 && mouseX >= x && mouseY <= y+48 && mouseY >= y){
            return true;
        }
        return false;
    }
    default boolean checkHoverTower(int mouseX, int mouseY, float x, float y) {
        if (mouseX-(1+x)*32<=0 && mouseX-(1+x)*32>=-32
                && mouseY-((1+y)*32+40)<=0 && mouseY-((1+y)*32+40) >=-32){
            return true;
        }
        return false;
    }
}
