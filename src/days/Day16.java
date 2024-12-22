package days;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

import helper.Coordinate;
import helper.Parser;

public class Day16 implements Day {
    private final static char START = 'S';
    private final static char PATH = '.';
    private final static char END = 'E';
    private char[][] grid;
    private int rows, cols;
    private int start[] = { 0, 0 };
    private int end[] = { 0, 0 };
    int[][] bestScore;

    @Override
    public void solvePartOne() {
        parseText("inputs/day16.txt");
        findStartandEnd();
        int distance = letTheReindeerMazeBegin();
        System.out.println("the best seats are next to path: " + distance);

    }

    @Override
    public void solvePartTwo() {
        for(int[] i: bestScore) {
        }
    }

private int letTheReindeerMazeBegin() {
    bestScore = new int[rows][cols];
    for (int[] row : bestScore) {
        Arrays.fill(row, Integer.MAX_VALUE); 
    }

    Deque<int[]> queue = new ArrayDeque<>();
    queue.addLast(new int[] { start[0], start[1], 1, 1 }); 
    bestScore[start[0]][start[1]] = 0;

    int lowestScore = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
        int[] current = queue.pollFirst();
        int y = current[0], x = current[1], dist = current[2], prevDir = current[3];

        for (int i = 0; i < Coordinate.DIRECTIONS_NO_DIAG.length; i++) {
            int[] dir = Coordinate.DIRECTIONS_NO_DIAG[i];
            int nextY = y + dir[0];
            int nextX = x + dir[1];

            if(nextX == end[1] && nextY == end[0]) {
            lowestScore = Math.min(lowestScore, dist);
            continue;
            }

            if (nextX >= 0 && nextY >= 0 && nextX < cols && nextY < rows && grid[nextY][nextX] == PATH) {
                int newDist = dist + 1;
                if (prevDir != i) {
                    newDist += 1000; 
                }

                if (newDist < bestScore[nextY][nextX]) {
                    bestScore[nextY][nextX] = newDist;
                    queue.addLast(new int[] { nextY, nextX, newDist, i });
                }
            }
        }
    }

    return lowestScore;
}

    private void findStartandEnd() {
        boolean foundStart = false;
        boolean foundEnd = false;
        boolean done = false;
        for (int y = 0; y < rows && !done; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] == START) {
                    start[0] = y;
                    start[1] = x;
                    foundStart = true;
                }
                if (grid[y][x] == END) {
                    end[0] = y;
                    end[1] = x;
                    foundEnd = true;
                }
                if (foundStart && foundEnd) {
                    done = true;
                    break;
                }
            }
        }
    }

    private void parseText(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        lines = Parser.readFile(filePath);
        rows = lines.size();
        cols = lines.get(0).length();
        grid = Parser.parseCharGrid(rows, cols, lines);
    }

}
