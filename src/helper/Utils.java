package helper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Utils {

    public static ArrayList<Integer> convertString(String[] line) {
        ArrayList<Integer> numberList = new ArrayList<>();

        for (String num : line) {
            numberList.add(Integer.parseInt(num));
        }

        return numberList;
    }

    public static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static int ggd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int kgv(int a, int b) {
        return (a * b) / ggd(a, b);
    }

    public static void floodFill(char[][] grid, int x, int y, char target, char replacement) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != target
                || grid[x][y] == replacement) {
            return;
        }

        grid[x][y] = replacement;
        for (int[] dir : Coordinate.DIRECTIONS_NO_DIAG) {
            floodFill(grid, x + dir[0], y + dir[1], target, replacement);
        }
    }

    public static int shortestCharPathNoDiag(char[][] grid, int[] start, int[] end, char path) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[] { start[1], start[0], 0 });
        visited[start[1]][start[0]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.pollFirst();
            int x = current[1], y = current[0], dist = current[2];

            for (int[] dir : Coordinate.DIRECTIONS_NO_DIAG) {
                int nextX = x + dir[1];
                int nextY = y + dir[0];
                if (nextX == end[1] && nextY == end[0])
                    return dist;
                if (nextX >= 0 && nextY >= 0 && nextX < cols && nextY < rows && !visited[nextY][nextX]
                        && grid[nextY][nextX] == path) {
                    visited[nextY][nextX] = true;
                    queue.addLast(new int[] { nextY, nextX, dist + 1 });
                }
            }
        }

        return -1;
    }

    public static int shortestIntPathNoDiag(int[][] grid, int[] start, int[] end, int path) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[] { start[0], start[1], 0 });
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.pollFirst();
            int x = current[0], y = current[1], dist = current[2];

            if (x == end[0] && y == end[1])
                return dist;

            for (int[] dir : Coordinate.DIRECTIONS_NO_DIAG) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                if (nextX >= 0 && nextY >= 0 && nextX < rows && nextY < cols && !visited[nextX][nextY]
                        && grid[nextX][nextY] == path) {
                    visited[nextX][nextY] = true;
                    queue.addLast(new int[] { nextX, nextY, dist + 1 });
                }
            }
        }

        return -1;
    }

}
