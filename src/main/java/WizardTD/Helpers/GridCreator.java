package WizardTD.Helpers;

import WizardTD.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GridCreator {

    private static final String Level = App.json.getString("layout");

    /**
     * @return A 2D Array containing String data type for MapCreator class to .
     */
    public static String[][] LevelReader() {

        File LevelFile = new File(Level);
        String[][] levelArray = new String[20][20];  // Initialization

        Scanner sc;
        try {
            sc = new Scanner(LevelFile);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (int j = 0; j < 20; j++) {
                if (j >= line.length()) {
                    levelArray[j][i] = " ";
                } else {
                    levelArray[j][i] = String.valueOf(line.charAt(j));
                }
            }
            i++;
        }
        return levelArray;
    }
}


