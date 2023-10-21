package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.Tiles.*;

import java.util.*;

public class MonsterPathReader {

    HashMap<MonsterDirection, Integer> determine = new HashMap<>();
    private void ReadSurround(Path path, Path[][] paths, Monster monster) {
        int X = path.getX()/App.CELLSIZE;
        int Y = (path.getY())/App.CELLSIZE;
        determine.put(MonsterDirection.SOUTH,Integer.MAX_VALUE);
        determine.put(MonsterDirection.NORTH,Integer.MAX_VALUE);
        determine.put(MonsterDirection.EAST,Integer.MAX_VALUE);
        determine.put(MonsterDirection.WEST,Integer.MAX_VALUE);
        MonsterDirection direction = monster.getCurrentDirection();
        HashSet<String> visitedPaths = new HashSet<String>();
        visitedPaths.add(X+","+Y);

        MonsterDirection[] directions =
                {MonsterDirection.NORTH, MonsterDirection.SOUTH, MonsterDirection.EAST, MonsterDirection.WEST};
        boolean[] canMoveConditions =
                {path.isNorth(), path.isSouth(), path.isEast(), path.isWest()};
        MonsterDirection[] oppositeDirections =
                {MonsterDirection.SOUTH, MonsterDirection.NORTH, MonsterDirection.WEST, MonsterDirection.EAST};
        int[][] offsets =
                {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < 4; i++) {
            if (canMoveConditions[i] && (direction != oppositeDirections[i])) {
                int distancePow =
                        ReadExpand(paths[X + offsets[i][0]][Y + offsets[i][1]], paths, new HashSet<>(visitedPaths), 0);
                determine.put(directions[i], distancePow);
            }
        }

    }



    private String getKey(int X, int Y) {
        return X + "," + Y; // Returns a string like "5,7"
    }
    private int ReadExpand(Path path, Path[][] paths, Set<String> visitedPaths, int count) {
        if (path == null) return Integer.MAX_VALUE - count;

        int X = path.getX() / App.CELLSIZE;
        int Y = path.getY() / App.CELLSIZE;

        // If this path leads to the WizardHouse, return immediately
        if (X == App.wizardX / App.CELLSIZE && Y == App.wizardY / App.CELLSIZE) {
            //System.out.println("Connected to WizardHouse");
            return count;
        }

        visitedPaths.add(getKey(X, Y));

        int min = Integer.MAX_VALUE;

        if (path.isNorth() && Y-1 >= 0 && !visitedPaths.contains(getKey(X, Y-1))) {
            int northResult = ReadExpand(paths[X][Y - 1], paths, visitedPaths, count + 2);
            min = Math.min(min, northResult);
            visitedPaths.remove(getKey(X, Y - 1));
        }
        if (path.isEast() && X+1 < 20 && !visitedPaths.contains(getKey(X+1, Y))) {
            int eastResult = ReadExpand(paths[X + 1][Y], paths, visitedPaths, count + 2);
            min = Math.min(min, eastResult);
            visitedPaths.remove(getKey(X + 1, Y));
        }
        if (path.isSouth() && Y+1 < 20 && !visitedPaths.contains(getKey(X, Y+1))) {
            int southResult = ReadExpand(paths[X][Y + 1], paths, visitedPaths, count + 2);
            min = Math.min(min, southResult);
            visitedPaths.remove(getKey(X, Y + 1));
        }
        if (path.isWest() && X-1 >= 0 && !visitedPaths.contains(getKey(X-1, Y))) {
            int westResult = ReadExpand(paths[X - 1][Y], paths, visitedPaths, count + 2);
            min = Math.min(min, westResult);
            visitedPaths.remove(getKey(X - 1, Y));
        }

        return min;
    }


    public void Read(Monster monster) {


        int monsterX = ((int) monster.getX())/App.CELLSIZE;  // Monsters' X and Y co in index num system instead of the actual ones
        int monsterY = ((int) monster.getY())/App.CELLSIZE;


        //MonsterDirection currentDirection = monster.getCurrentDirection();
//        System.out.println(monsterX);
//        System.out.println(monsterY);
        //if (monsterX < 20 && monsterX > 0 && monsterY < 20 && monsterY > 0) {
        if (monster.pathsMem[monsterX][monsterY] != null) {
            ReadSurround(monster.pathsMem[monsterX][monsterY], monster.pathsMem, monster);
        }
        //}

        int min = Integer.MAX_VALUE;
        for (MonsterDirection direction : MonsterDirection.values()) {
            int i = determine.get(direction);
            if (i < min) {
                min = i;
            }
        }

        List<MonsterDirection> directionsMatchingMin = new ArrayList<>();

        for (MonsterDirection direction : MonsterDirection.values()) {
            if (determine.get(direction) == min) {
                //System.out.println(determine.get(direction));
                directionsMatchingMin.add(direction);
            }
        }

        Random random = new Random();
        MonsterDirection moveChoose = null;
        if (!directionsMatchingMin.isEmpty()) {
            moveChoose=directionsMatchingMin.get(random.nextInt(directionsMatchingMin.size()));
        }
        if (moveChoose == MonsterDirection.SOUTH) {
            monster.goSouth();
        }
        if (moveChoose == MonsterDirection.NORTH) {
            monster.goNorth();
        }
        if (moveChoose == MonsterDirection.EAST) {
            monster.goEast();
        }
        if (moveChoose == MonsterDirection.WEST) {
            monster.goWest();
        }

        //System.out.println(monster.currentDirection());
    }

}
