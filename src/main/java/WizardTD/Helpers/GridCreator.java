package WizardTD.Helpers;

import WizardTD.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GridCreator {

    public static String[][] LevelArray;
    public static String Level = App.json.getString("layout");
    public static String[][] LevelReader() {

        File LevelFile = new File(Level);
        LevelArray = new String[20][20];  // Initialization

        Scanner sc;
        try {
            sc = new Scanner(LevelFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (int j = 0; j < 20; j++) {
                if (j >= line.length()) {
                    LevelArray[j][i] = " ";
                } else {
                    LevelArray[j][i] = String.valueOf(line.charAt(j));
                }
            }
            i++;
        }
        return LevelArray;
    }
}


