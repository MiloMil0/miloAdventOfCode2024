package days;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import helper.Coordinate;
import helper.Parser;

public class Day10 implements Day {
    private final static int TARGET = 9;
    private ArrayList<String> lines = new ArrayList<>();
    private ArrayList<int[]> trailHeads = new ArrayList<>();
    private int[][] grid;
    private int rows;
    private int cols;

    @Override
    public void solvePartOne() {
        String filePath = "inputs/day10.txt";
        lines = Parser.readFile(filePath);
        rows = lines.size();
        cols = lines.get(0).length();
        grid = Parser.parseIntGrid(rows, cols, lines);
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                if (grid[y][x] == 0) {
                    trailHeads.add(new int[] { y, x });
                }
            }
        }
        int amount = 0;
        for (int[] trails : trailHeads) {
            amount += countReachableTrails(trails);
        }

        System.out.println("part 1 has " + amount + " valid trails");
    }

    @Override
    public void solvePartTwo() {
        int amount = 0;
        for (int[] trails : trailHeads) {
            amount += findUniqueTrail(trails);
        }

        System.out.println("part 2 has " + amount + " valid trails");

    }

    private int countReachableTrails(int[] start) {
        int count = 0;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();

        queue.addLast(new int[] { start[0], start[1] });
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.pollFirst();
            int y = current[0];
            int x = current[1];

            if (isTarget(y, x)) {
                count++;
            }

            ArrayList<int[]> neighbors = Coordinate.getNeighbors(y, x, Coordinate.DIRECTIONS_NO_DIAG, rows, cols);
            for (int[] neighbor : neighbors) {
                int nextY = neighbor[0];
                int nextX = neighbor[1];

                if (!visited[nextY][nextX] && grid[nextY][nextX] == grid[y][x] + 1) {
                    visited[nextY][nextX] = true;
                    queue.addLast(new int[] { nextY, nextX });
                }
            }
        }
        return count;
    }

    private int findUniqueTrail(int[] start) {
        int count = 0;
        Deque<int[]> queue = new ArrayDeque<>();

        queue.addLast(new int[] { start[0], start[1] });

        while (!queue.isEmpty()) {
            int[] current = queue.pollFirst();
            int y = current[0]; 
            int x = current[1];

            if (isTarget(y, x)) {
                count++; 
                continue; 
            }

            ArrayList<int[]> neighbors = Coordinate.getNeighbors(y, x, Coordinate.DIRECTIONS_NO_DIAG, grid.length,
                    grid[0].length);
            for (int[] neighbor : neighbors) {
                int nextY = neighbor[0];
                int nextX = neighbor[1];

                if (grid[nextY][nextX] == grid[y][x] + 1) {
                    queue.addLast(new int[] { nextY, nextX});
                }
            }
        }
        return count;
    }

    private boolean isTarget(int y, int x) {
        return grid[y][x] == TARGET;
    }

}