package days;

import java.util.*;

import helper.Coordinate;
import helper.Parser;

public class Day12 implements Day {
    private ArrayList<String> lines = new ArrayList<>();
    private Map<Coordinate, Boolean> visited = new HashMap<>();
    private char[][] grid;
    private int rows;
    private int cols;

    @Override
    public void solvePartOne() {
        String filePath = "inputs/example.txt";
        lines = Parser.readFile(filePath);
        if (lines == null) {
            return;
        }
        rows = lines.size();
        cols = lines.get(0).length();
        grid = Parser.parseCharGrid(rows, cols, lines);
        char[][] gridPartOne = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            gridPartOne[i] = grid[i].clone();
        }
        int total = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (Boolean.TRUE.equals(visited.get(new Coordinate(x,y)))) {
                    continue;
                }
                int[] garden = countFence(gridPartOne, y, x, grid[y][x]);
                total += (garden[0] * garden[1]);
            }
        }

        System.out.println("the price for all the fence is: " + total);
    }

    @Override
    public void solvePartTwo() {

    }

    private int[] countFence(char[][] grid, int y, int x, char target) {
        Deque<Coordinate> stack = new ArrayDeque<>();
        stack.add(new Coordinate(x, y));
        int plot = 0;
        int fence = 0;

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();
            int curX = current.getX();
            int curY = current.getY();

            if (Boolean.TRUE.equals(visited.get(current))) {
                continue;
            }

            plot++;

            visited.put(current, true);

            for (int[] dir : Coordinate.DIRECTIONS_NO_DIAG) {
                int nextX = curX + dir[1];
                int nextY = curY + dir[0];
                Coordinate next = new Coordinate(nextX, nextY);

                if (nextX >= 0 && nextY >= 0 && nextX < cols && nextY < rows
                        && grid[nextY][nextX] == target
                        && !Boolean.TRUE.equals(visited.get(next))) {
                    stack.push(next);
                } 
                if(nextX<0 || nextY<0 || nextY >= rows || nextX >= cols || grid[nextY][nextX] != target){    
                    fence++;
                }
            }
        }
        // System.out.println(gardenLines);
        return new int[] { plot, fence};
    }

}
