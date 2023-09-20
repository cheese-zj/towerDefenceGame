package WizardTD;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;

public class Grid {

    public String[][] LevelArray;
    public String[][] LevelReader() {
        String Level = App.json.getString("layout");
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
            for (int j = 0; j < line.length(); j++) {
                LevelArray[j][i] = String.valueOf(line.charAt(j));
            }
            i++;
        }
        return LevelArray;
    }
}


