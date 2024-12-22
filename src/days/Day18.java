package days;

import java.util.ArrayList;
import java.util.Arrays;

import helper.Parser;
import helper.Utils;

public class Day18 implements Day {
    private final static int WIDTH = 71;
    private final static int HEIGHT = 71;
    private char[][] grid = new char[HEIGHT][WIDTH];
    private int[] start = new int[] { 0, 0 };
    private int[] end = new int[] { HEIGHT - 1, WIDTH - 1 };
    private boolean partTwo = false;
    private ArrayList<String> lines;

    @Override
    public void solvePartOne() {
        int total = parseFile("inputs/day18.txt", 1024);

        System.out.println("Only " + total + " steps in part one");
    }

    @Override
    public void solvePartTwo() {
        partTwo = true;

        int total = parseFile("inputs/day18.txt", 0);
        if(total>lines.size()) {
        System.out.println(total);
        System.out.println(lines.size());
        } else {

        System.out.println(" the first blocker is at " + lines.get(total));
        }
    }

    private int parseFile(String filePath, int amount) {
        lines = Parser.readFile(filePath);
        if (lines == null) {
            return -1;
        }
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }

        int limit = partTwo ? lines.size() : amount;
        for (int i = 0; i < limit; i++) {
            String[] parts = lines.get(i).trim().split(",");
            int row = Integer.parseInt(parts[1]);
            int col = Integer.parseInt(parts[0]);
            grid[row][col] = '#';
            if (partTwo) {
                if (Utils.shortestCharPathNoDiag(grid, start, end, '.') == -1) {
                    return i;
                }
            }
        }

        return Utils.shortestCharPathNoDiag(grid, start, end, '.') + 1;
    }
}
