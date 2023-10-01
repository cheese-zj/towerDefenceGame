package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.MapCreator;
import WizardTD.Tiles.*;

import java.util.*;

public class MonsterPathReader {

    HashMap<MonsterDirection, Integer> determine = new HashMap<>();
    private void ReadSurround(Path path, Path[][] paths, Monster monster) {
        int X = path.getX()/App.CELLSIZE;
        int Y = (path.getY()-App.TOPBAR)/App.CELLSIZE;
        determine.put(MonsterDirection.SOUTH,0);
        determine.put(MonsterDirection.NORTH,0);
        determine.put(MonsterDirection.EAST,0);
        determine.put(MonsterDirection.WEST,0);
        MonsterDirection direction = monster.getCurrentDirection();
        //[N,S,E,W]
//        System.out.println(X);
//        System.out.println(Y);
        HashSet<String> visitedPaths = new HashSet<String>();
        visitedPaths.add(X+","+Y);

        if (path.isNorth() && direction!=MonsterDirection.SOUTH) {
            System.out.println("North != null");

            int distancePow = -ReadExpand(paths[X][Y-1],paths,(Set<String>) visitedPaths.clone(),0);
            determine.put(MonsterDirection.NORTH, distancePow);
        }
        if (path.isSouth() && direction!=MonsterDirection.NORTH) {
            System.out.println("South != null");

            int distancePow = -ReadExpand(paths[X][Y+1],paths,(Set<String>) visitedPaths.clone(),0);
            System.out.println(distancePow);
            determine.put(MonsterDirection.SOUTH, distancePow);
        }
        if (path.isEast() && direction!=MonsterDirection.WEST) {
            System.out.println("East != null");


            int distancePow = -ReadExpand(paths[X+1][Y],paths,(Set<String>) visitedPaths.clone(),0);
            System.out.println(distancePow);
            determine.put(MonsterDirection.EAST, distancePow);
        }
        if (path.isWest() && direction!=MonsterDirection.EAST) {
            System.out.println("West != null");


            int distancePow = -ReadExpand(paths[X-1][Y],paths,(Set<String>) visitedPaths.clone(),0);
            System.out.println(distancePow);
            determine.put(MonsterDirection.WEST, distancePow);
        }

    }  //待办项：用勾股来计算可选路径的坐标和目的地的间隔，将hashmap的-1替换为-distance，
    // 通过枚举得到量最小的方向，然后添加到一个新的表，在这个表里面随机选择（如果只有一个选项就只会有一个输出）



    private String getKey(int X, int Y) {
        return X + "," + Y; // Returns a string like "5,7"
    }
    private int ReadExpand(Path path, Path[][] paths, Set<String> visitedPaths, int count) {

        int X = path.getX() / App.CELLSIZE;
        int Y = (path.getY() - App.TOPBAR) / App.CELLSIZE;

//        if (visitedPaths.contains(getKey(X, Y))) {
//            return 0;
//        }


        // Mark the path as visited
        visitedPaths.add(getKey(X, Y));
        count++;
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


        if (X == App.wizardX/App.CELLSIZE && Y == (App.wizardY-App.TOPBAR)/App.CELLSIZE) {
            System.out.println("Connected to WizardHouse");
            return count;
        }

        if (!validDirections.isEmpty()) {
            for (MonsterDirection dir : validDirections) {
                int branchResult = 0;
                switch (dir) {
                    case NORTH:
                        branchResult = ReadExpand(paths[X][Y-1], paths, visitedPaths, count);
                        break;
                    case SOUTH:
                        branchResult = ReadExpand(paths[X][Y+1], paths, visitedPaths, count);
                        break;
                    case EAST:
                        branchResult = ReadExpand(paths[X+1][Y], paths, visitedPaths, count);
                        break;
                    case WEST:
                        branchResult = ReadExpand(paths[X-1][Y], paths, visitedPaths, count);
                        break;
                }
                if (branchResult != 0) {
                    return branchResult;
                }
            }
        }
        return 0;
    }

    public void Read(Monster monster) {


        int monsterX = ((int) monster.getX())/App.CELLSIZE;  // Monsters' X and Y co in index num system instead of the actual ones
        int monsterY = ((int) monster.getY()-App.TOPBAR)/App.CELLSIZE;
        //MonsterDirection currentDirection = monster.getCurrentDirection();
        System.out.println(monsterX);
        System.out.println(monsterY);
        if (monster.pathsMem[monsterX][monsterY] != null){
            ReadSurround(monster.pathsMem[monsterX][monsterY], monster.pathsMem, monster);
        }

        int min = Integer.MIN_VALUE;
        for (MonsterDirection direction : MonsterDirection.values()) {
            int i = determine.get(direction);
            if (i > min && i!=0) {
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
        for (MonsterDirection i : directionsMatchingMin ) {
            System.out.println(i);
        }

        MonsterDirection moveChoose = MonsterDirection.EAST;
        if (!directionsMatchingMin.isEmpty()) {
            moveChoose=directionsMatchingMin.get(random.nextInt(directionsMatchingMin.size()));
            System.out.println(moveChoose);
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
