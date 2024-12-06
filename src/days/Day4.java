package days;

import java.util.ArrayList;

import helper.Coordinate;
import helper.Parser;
import helper.Utils;

public class Day4 implements Day {

    private ArrayList<String> lines = new ArrayList<>();
    private final static String WORD = "XMAS";
    private int rows = 0;
    private int cols = 0;

    @Override
    public void solvePartOne() {
        String filePath = "inputs/day4.txt";
        lines = Parser.readFile(filePath);
        lines.removeIf(String::isEmpty);

        rows = lines.size();
        cols = lines.get(0).length();

        char[][] grid = Parser.parseCharGrid(rows, cols, lines);

        int count = 0;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] == 'X') {
                    count += checkDirections(x, y, grid);
                }
            }
        }

        System.out.println("XMAS occurs " + count + " times");
    }

    @Override
    public void solvePartTwo() {

        char[][] grid = Parser.parseCharGrid(rows, cols, lines);

        int count = 0;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] == 'A') {
                    count += unoMAS(x, y, grid);
                }
            }
        }
        System.out.println("X-MAS occurs " + count + " times");
    }

    private int unoMAS(int x, int y, char[][] grid) {
        if (!Coordinate.checkPerimeter(x, y, rows, cols, 1, grid)) {
            return 0;
        }

        int count = 0;

        if (grid[y - 1][x - 1] == 'M' && grid[y - 1][x + 1] == 'S' &&
            grid[y + 1][x - 1] == 'M' && grid[y + 1][x + 1] == 'S') {
            count++;
        }
        if (grid[y - 1][x - 1] == 'S' && grid[y - 1][x + 1] == 'M' &&
            grid[y + 1][x - 1] == 'S' && grid[y + 1][x + 1] == 'M') {
            count++;
        }
        if (grid[y - 1][x - 1] == 'M' && grid[y - 1][x + 1] == 'M' &&
            grid[y + 1][x - 1] == 'S' && grid[y + 1][x + 1] == 'S') {
            count++;
        }
        if (grid[y - 1][x - 1] == 'S' && grid[y - 1][x + 1] == 'S' &&
            grid[y + 1][x - 1] == 'M' && grid[y + 1][x + 1] == 'M') {
            count++;
        }

        return count;
    }

    private int checkDirections(int x, int y, char[][] grid) {
        int count = 0;

        for (int[] direction : Coordinate.DIRECTIONS_DIAG) {
            int dirX = direction[1];
            int dirY = direction[0];
            boolean isValid = true;

            for (int i = 1; i < WORD.length(); i++) {
                int xMas = x + i * dirX;
                int yMas = y + i * dirY;
                
                if (xMas < 0 || yMas < 0 || xMas >= cols || yMas >= rows || grid[yMas][xMas] != WORD.charAt(i)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                count++;
            }
        }

        return count;
    }

}
