package helper;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<Integer> convertString(String[] line) {
        ArrayList<Integer> numberList = new ArrayList<>();

            for(String num: line) {
                numberList.add(Integer.parseInt(num));
            }

        return numberList;
    }


    public static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static char[][] createCharGrid(int rows, int cols, ArrayList<String> lines) {
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        return grid;
    }

}


