package days;

import java.util.ArrayList;

import helper.Coordinate.Direction;
import helper.Coordinate;
import helper.Parser;

public class Day15 implements Day {
    private static final char BLOCKER = '#';
    private static final char BOX = 'O';
    private static final char ROBOT = '@';
    private static final char TILE = '.';
    private final ArrayList<String> movement = new ArrayList<>();
    private int rows;
    private int cols;
    private char[][] grid;

    @Override
    public void solvePartOne() {
        parseText("inputs/day15.txt");
        int[] start = findRobotStart();

        moveRobot(start[0], start[1]);
        int total = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] == BOX) {
                    int tally = 100 * y + x;
                    total += tally;
                }
            }
        }
        System.out.println("The GPS coordinates are: " + total);
    }

    @Override
    public void solvePartTwo() {
        // Implementation for part two
    }

    private void moveRobot(int startY, int startX) {
        int[] currLoc = { startY, startX };

        for (String move : movement) {
            for (char dir : move.toCharArray()) {
                Direction nextDir = getDirection(dir);
                int[] nextStep = Coordinate.getNoDiagDir(nextDir);
                int[] nextLoc = { currLoc[0] + nextStep[0], currLoc[1] + nextStep[1] };

                if (!canMove(nextLoc[0], nextLoc[1], nextDir)) {
                    continue;
                }

                grid[currLoc[0]][currLoc[1]] = TILE;
                grid[nextLoc[0]][nextLoc[1]] = ROBOT;

                currLoc = nextLoc;
            }
        }
    }

    private boolean canMove(int nextY, int nextX, Direction dir) {
        int[] currDir = Coordinate.getNoDiagDir(dir);

        while (isWithinBounds(nextY, nextX)) {
            if (grid[nextY][nextX] == BLOCKER) {
                return false;
            } else if (grid[nextY][nextX] == TILE) {
                grid[nextY][nextX] = BOX;
                return true;
            }
            nextY += currDir[0];
            nextX += currDir[1];
        }
        return false;
    }

    private static Direction getDirection(char dir) {
        return switch (dir) {
            case '^' -> Direction.UP;
            case '>' -> Direction.RIGHT;
            case 'v' -> Direction.DOWN;
            case '<' -> Direction.LEFT;
            default -> null;
        };
    }

    private int[] findRobotStart() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] == ROBOT) {
                    return new int[] { y, x };
                }
            }
        }
        throw new IllegalStateException("Robot not found in grid");
    }

    private boolean isWithinBounds(int y, int x) {
        return y >= 0 && y < rows && x >= 0 && x < cols;
    }

    private void parseText(String filePath) {
        ArrayList<String> lines = Parser.readFile(filePath);

        ArrayList<String> tempGrid = new ArrayList<>();
        boolean firstPart = true;

        for (String line : lines) {
            if (line.isEmpty()) {
                firstPart = false;
                continue;
            }

            if (firstPart) {
                tempGrid.add(line);
            } else {
                movement.add(line);
            }
        }

        rows = tempGrid.size();
        cols = tempGrid.get(0).length();
        grid = Parser.parseCharGrid(rows, cols, tempGrid);
    }
}
