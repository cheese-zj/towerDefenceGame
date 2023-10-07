package WizardTD.GameSys;

import WizardTD.App;

import java.util.ArrayList;


public class ButtonsCollection {

    public ArrayList<Buttons> ButtonsArray = new ArrayList<>();
    public void generate(App app) {
        ButtonsArray.add(new U1(640 + 10, 40 + 10*4 + 48*3, app)); // U1 button
        ButtonsArray.add(new U2(640 + 10, 40 + 10*5 + 48*4, app)); // U2 button
        ButtonsArray.add(new U3(640 + 10, 40 + 10*6 + 48*5, app)); // U3 button
        ButtonsArray.add(new M(640 + 10, 40 + 10*7 + 48*6, app)); // M button
        ButtonsArray.add(new FF(640 + 10, 40 + 10*1 + 48*0, app)); // FF button
        ButtonsArray.add(new P(640 + 10, 40 + 10*2 + 48*1, app)); // P button
        ButtonsArray.add(new T(640 + 10, 40 + 10*3 + 48*2, app)); // T button

    }


}
