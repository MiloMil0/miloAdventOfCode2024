package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> fileLines = null;
        try {
            fileLines = (ArrayList<String>) Files.readAllLines(Paths.get(filePath));

        } catch (IOException e) {
            System.out.println("Error reading the file: " + filePath);
            e.printStackTrace();
        }

        return fileLines;
    }

    public static ArrayList<Integer> parseIntsFromLine(String line, String delimiter) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String num : line.split(delimiter)) {
            numbers.add(Integer.parseInt(num.trim()));
        }
        return numbers;
    }
    public static ArrayList<Long> parseLongFromLine(String line, String delimiter) {
        ArrayList<Long> numbers = new ArrayList<>();
        for (String num : line.split(delimiter)) {
            numbers.add(Long.parseLong(num.trim()));
        }
        return numbers;
    }

    public static char[][] parseCharGrid(int rows, int cols, ArrayList<String> lines) {
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        return grid;
    }

    public static int[][] parseIntGrid(int rows, int cols, ArrayList<String> lines) {
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        return grid;
    }
}
