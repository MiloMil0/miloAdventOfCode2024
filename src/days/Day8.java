package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import helper.Parser;

public class Day8 implements Day {
    private ArrayList<String> lines = new ArrayList<>();
    private Map<Character, ArrayList<int[]>> antennas = new HashMap<>();
    private int rows;
    private int cols;
    private char[][] antinodeGrid;

    @Override
    public void solvePartOne() {
        // String filePath = "inputs/example.txt";
        String filePath = "inputs/day8.txt";

        lines = Parser.readFile(filePath);
        lines.removeIf(String::isEmpty);

        rows = lines.size();
        cols = lines.get(0).length();

        char[][] antennaGrid = Parser.parseCharGrid(rows, cols, lines);
        antinodeGrid = antennaGrid.clone();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                char currentPos = antennaGrid[y][x];
                if (currentPos != '.') {
                    ArrayList<int[]> coordinates = antennas.computeIfAbsent(currentPos, _ -> new ArrayList<>());
                    coordinates.add(new int[] { y, x });
                }
            }
        }

        for (Map.Entry<Character, ArrayList<int[]>> entry : antennas.entrySet()) {
            if (entry.getValue().size() < 2) {
                continue;
            }
            ArrayList<int[]> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                for (int j = i + 1; j < value.size(); j++) {
                    int[] coordOne = Arrays.copyOf(value.get(i), 2);
                    int[] coordTwo = Arrays.copyOf(value.get(j), 2);
                    int[] differenceOne = getDifference(coordOne, coordTwo);
                    int[] differenceTwo = getDifference(coordTwo, coordOne);

                    placeAntinodes(coordOne, differenceOne);
                    placeAntinodes(coordTwo, differenceTwo);
                }

            }
        }
        System.out.println("Total antinodes part One: " + countAntinodes());
    }

    @Override
    public void solvePartTwo() {
        for (Map.Entry<Character, ArrayList<int[]>> entry : antennas.entrySet()) {
            if (entry.getValue().size() < 2) {
                continue;
            }
            ArrayList<int[]> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                for (int j = i + 1; j < value.size(); j++) {
                    int[] coordOne = Arrays.copyOf(value.get(i), 2);
                    int[] coordTwo = Arrays.copyOf(value.get(j), 2);

                    int[] differenceOne = getDifference(coordOne, coordTwo);
                    int[] differenceTwo = getDifference(coordTwo, coordOne);

                    antinodeGrid[coordOne[0]][coordOne[1]] = '#';
                    while (placeAntinodes(coordOne, differenceOne)) {}

                    antinodeGrid[coordTwo[0]][coordTwo[1]] = '#';
                    while (placeAntinodes(coordTwo, differenceTwo)) {}
                }
            }
        }
        System.out.println("Total antinodes Part Two: " + countAntinodes());
    }

    private static int[] getDifference(int[] coordOne, int[] coordTwo) {
        return new int[] {
                (coordOne[0] - coordTwo[0]),
                (coordOne[1] - coordTwo[1])
        };
    }

    private boolean placeAntinodes(int[] coordOne, int[] difference) {
        coordOne[0] += difference[0];
        coordOne[1] += difference[1];
        if (isValidAntinodeSpot(coordOne[0], coordOne[1], antinodeGrid)) {
            antinodeGrid[coordOne[0]][coordOne[1]] = '#';
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidAntinodeSpot(int y, int x, char[][] antinodeGrid) {
        return (x >= 0 && x < cols && y >= 0 && y < rows);
    }

    private int countAntinodes() {
        int count = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (antinodeGrid[y][x] != '#') {
                    continue;
                }
                count++;
            }
        }
        return count;
    }
}
