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
            if (canMoveConditions[i] && direction != oppositeDirections[i]) {
                int distancePow =
                        ReadExpand(paths[X + offsets[i][0]][Y + offsets[i][1]], paths, new HashSet<>(visitedPaths), 2);
                determine.put(directions[i], distancePow);
            }
        }

//        if (path.isNorth() && direction!=MonsterDirection.SOUTH) {
//            //System.out.println("North != null");
//
//            int distancePow = -ReadExpand(paths[X][Y-1],paths,(Set<String>) visitedPaths.clone(),0);
//            determine.put(MonsterDirection.NORTH, distancePow);
//        }
//        if (path.isSouth() && direction!=MonsterDirection.NORTH) {
//            //System.out.println("South != null");
//
//            int distancePow = -ReadExpand(paths[X][Y+1],paths,(Set<String>) visitedPaths.clone(),0);
//            determine.put(MonsterDirection.SOUTH, distancePow);
//        }
//        if (path.isEast() && direction!=MonsterDirection.WEST) {
//            //System.out.println("East != null");
//
//
//            int distancePow = -ReadExpand(paths[X+1][Y],paths,(Set<String>) visitedPaths.clone(),0);
//            determine.put(MonsterDirection.EAST, distancePow);
//        }
//        if (path.isWest() && direction!=MonsterDirection.EAST) {
//            //System.out.println("West != null");
//
//            int distancePow = -ReadExpand(paths[X-1][Y],paths,(Set<String>) visitedPaths.clone(),0);
//            determine.put(MonsterDirection.WEST, distancePow);
//        }

    }


    private String getKey(int X, int Y) {
        return X + "," + Y; // Returns a string like "5,7"
    }
    private int ReadExpand(Path path, Path[][] paths, Set<String> visitedPaths, int count) {

        int X = path.getX() / App.CELLSIZE;
        int Y = (path.getY() ) / App.CELLSIZE;

        // Mark the path as visited
        visitedPaths.add(getKey(X, Y));
        //System.out.println(count);
        List<MonsterDirection> validDirections = new ArrayList<>();

        if (path.isNorth() && !visitedPaths.contains(getKey(X, Y-1))) {
            validDirections.add(MonsterDirection.NORTH);
        }
        if (path.isSouth() && !visitedPaths.contains(getKey(X, Y+1))) {
            validDirections.add(MonsterDirection.SOUTH);
        }
        if (path.isEast() && !visitedPaths.contains(getKey(X+1, Y))) {
            validDirections.add(MonsterDirection.EAST);
        }
        if (path.isWest() && !visitedPaths.contains(getKey(X-1, Y))) {
            validDirections.add(MonsterDirection.WEST);
        }


        if (X == App.wizardX/App.CELLSIZE && Y == (App.wizardY)/App.CELLSIZE) {
            //System.out.println("Connected to WizardHouse");
            return count;
        }
        else
        if (!validDirections.isEmpty()) {

            count+=2;
            for (MonsterDirection dir : validDirections) {
                int branchResult = 1;
                HashSet<Integer> results = new HashSet<>();
                switch (dir) {
                    case NORTH:
                        branchResult += ReadExpand(paths[X][Y-1], paths, visitedPaths, count);
                        results.add(branchResult);
                        break;
                    case EAST:
                        branchResult += ReadExpand(paths[X+1][Y], paths, visitedPaths, count);
                        results.add(branchResult);
                        break;
                    case SOUTH:
                        branchResult += ReadExpand(paths[X][Y+1], paths, visitedPaths, count);
                        results.add(branchResult);
                        break;
                    case WEST:
                        branchResult += ReadExpand(paths[X-1][Y], paths, visitedPaths, count);
                        results.add(branchResult);
                        break;
                }

                int min = Integer.MAX_VALUE;
                if (!results.isEmpty()) {
                    for (int i : results) {
                        if (i < min) {
                            min = i;
                        }
                    }
                    return min;
                }
            }
        }
        return Integer.MAX_VALUE-count;
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
