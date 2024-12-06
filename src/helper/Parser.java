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
    
    public static char[][] parseCharGrid(int rows, int cols, ArrayList<String> lines) {
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        return grid;
    }
}
