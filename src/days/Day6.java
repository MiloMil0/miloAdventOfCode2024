package days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import helper.Coordinate.Direction;
import helper.Coordinate;
import helper.Parser;

public class Day6 implements Day {
    private final static char BLOCKER = '#';
    private final static Direction S_DIRECTION = Direction.UP;
    private HashSet<Coordinate> visitedPositions = new HashSet<>();
    private char[][] grid;
    private Direction direction = S_DIRECTION;
    private Coordinate start = new Coordinate();

    private ArrayList<String> lines = new ArrayList<>();
    private int rows;
    private int cols;

    @Override
    public void solvePartOne() {
        String filePath = "inputs/day6.txt";

        lines = Parser.readFile(filePath);

        if (lines == null) {
            return;
        }

        lines.removeIf(String::isEmpty);
        rows = lines.size();
        cols = lines.get(0).length();
        grid = Parser.parseCharGrid(rows, cols, lines);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                if (grid[y][x] == '^') {
                    start.setX(x);
                    start.setY(y);
                    break;
                }
            }
        }

        moveGuard();

        System.out.println("The guard passes: " + visitedPositions.size() + " tiles");
    }

    @Override
    public void solvePartTwo() {
        int count = 0;
        for (Coordinate position : visitedPositions) {
            direction = S_DIRECTION;
            int x = position.getX();
            int y = position.getY();

            if (x == start.getX() && y == start.getY()) {
                continue;
            }

            grid[y][x] = BLOCKER;

            if (areWeLoopYet()) {
                count++;
            }

            grid[y][x] = '.';
        }
        System.out.println("found " + count + " loops");
    }

    private void moveGuard() {
        int x = start.getX();
        int y = start.getY();

        while (x >= 0 && y >= 0 && x < cols && y < rows) {

            Coordinate next = nextMove(x, y);

            if (next.getX() < 0 || next.getY() < 0 || next.getX() >= cols || next.getY() >= rows) {
                break;
            }

            if (grid[next.getY()][next.getX()] == BLOCKER) {
                direction = Coordinate.turn90Right(direction);
            } else {
                x = next.getX();
                y = next.getY();
                visitedPositions.add(new Coordinate(x, y));
            }
        }

    }

    private boolean areWeLoopYet() {
        int x = start.getX();
        int y = start.getY();
        HashMap<Coordinate, Direction> visitedCoordinates = new HashMap<>();

        visitedCoordinates.put(start, direction);

        while (x >= 0 && y >= 0 && x < cols && y < rows) {
            Coordinate next = nextMove(x, y);

            if (next.getX() < 0 || next.getY() < 0 || next.getX() >= cols || next.getY() >= rows) {
                break;
            }

            if (visitedCoordinates.containsKey(next)) {
                Direction previousDirection = visitedCoordinates.get(next);
                if (previousDirection == direction) {
                    return true;
                }
            }

            visitedCoordinates.put(next, direction);

            if (grid[next.getY()][next.getX()] == BLOCKER) {
                direction = Coordinate.turn90Right(direction);
            } else {
                x = next.getX();
                y = next.getY();
            }
        }

        return false;
    }
    
    private Coordinate nextMove(int x, int y) {
        int nextX = x;
        int nextY = y;
            switch (direction) {
                case LEFT:
                    nextX--;
                    break;
                case RIGHT:
                    nextX++;
                    break;
                case UP:
                    nextY--;
                    break;
                case DOWN:
                    nextY++;
                    break;
                default:
                    break;
            }
            
            return new Coordinate(nextX,nextY);

    }

}
