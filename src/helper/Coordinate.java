package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {

    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    public static final int[][] DIRECTIONS_DIAG = {
            { 1, 0 },
            { 0, 1 },
            { -1, 0 },
            { 0, -1 },
            { -1, -1 },
            { 1, -1 },
            { -1, 1 },
            { 1, 1 }
    };

    public static final int[][] DIRECTIONS_NO_DIAG = {
            { 1, 0 },
            { 0, 1 },
            { -1, 0 },
            { 0, -1 },
    };

    public static int[] getNoDiagDir(Direction next) {
        if(next.equals(Direction.UP)) {
            return new int[] {-1, 0};
        } else if (next.equals(Direction.RIGHT)) {
            return new int[] {0, 1};
        } else if (next.equals(Direction.DOWN)) {
            return new int[] {1, 0};
        } else if (next.equals(Direction.LEFT)) {
            return new int[] {0, -1};
        }

        return new int[] {0,0};
    }

    public static Direction getDirection(int[] next) {
    if (Arrays.equals(next, new int[]{-1, 0})) {
        return Direction.UP;
    } else if (Arrays.equals(next, new int[]{0, 1})) {
        return Direction.RIGHT;
    } else if (Arrays.equals(next, new int[]{1, 0})) {
        return Direction.DOWN;
    } else if (Arrays.equals(next, new int[]{0, -1})) {
        return Direction.LEFT;
    }
    return null;
}

    public static boolean checkPerimeter(int x, int y, int rows, int cols, int size, char[][] grid) {
        return (x + size < cols && x - size >= 0 && y + size < rows && y - size >= 0);
    }

    public static ArrayList<int[]> getNeighbors(int y, int x, int[][] directions, int rows, int cols) {
        ArrayList<int[]> neighbors = new ArrayList<>();
        for (int[] dir : directions) {
            int nx = x + dir[1];
            int ny = y + dir[0];
            if (nx >= 0 && ny >= 0 && nx < cols && ny < rows) {
                neighbors.add(new int[] { ny, nx });
            }
        }
        return neighbors;
    }

    public static Direction turn90Left(Direction dir) {
        switch (dir) {
            case UP:
                return Direction.LEFT;
            case LEFT:
                return Direction.DOWN;
            case DOWN:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.UP;
            default:
                throw new IllegalArgumentException("Unsupported direction");
        }
    }

    public static Direction turn90Right(Direction dir) {
        switch (dir) {
            case UP:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.DOWN;
            case DOWN:
                return Direction.LEFT;
            case LEFT:
                return Direction.UP;
            default:
                throw new IllegalArgumentException("Unsupported direction");
        }
    }

}
