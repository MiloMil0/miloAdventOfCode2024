package days;

import java.util.ArrayList;
import java.util.Collections;

import helper.Parser;

public class Day1 implements Day{

    private ArrayList<Integer> leftColumn = new ArrayList<>();
    private ArrayList<Integer> rightColumn = new ArrayList<>();

    @Override
    public void solvePartOne() {
        String filePath = "inputs/day1_1.txt";
        importPuzzle(filePath);

        Collections.sort(leftColumn);
        Collections.sort(rightColumn);
    
        int total = 0;

        for(int i = 0; i<leftColumn.size(); i++) {
            total += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }

        System.out.println("The total distance = " + total);
    }

    @Override
    public void solvePartTwo() {
        int total = 0;
        int multiplier = 0;
        int nextJIndex = 0;

        for(int i = 0; i < leftColumn.size(); i++) {
            int left = leftColumn.get(i);
            for(int j = nextJIndex; j < rightColumn.size(); j++) {
                int right = rightColumn.get(j);
                if(right == left) {
                    multiplier += 1;
                } else if (right > left) {
                    nextJIndex = j;
                    break;
                } 
                total += left * multiplier;
                multiplier = 0;
            }
        } 

        System.out.println("the total score = " + total);
    }

    private void importPuzzle(String filePath) {
        ArrayList<String> fileLines = Parser.readFile(filePath);
        if (fileLines != null) {
            for(String line : fileLines) {
                String[] parts = line.trim().split("\\s+");
                if(parts.length == 2) {
                    try {
                        int left = Integer.parseInt(parts[0]);
                        int right = Integer.parseInt(parts[1]);

                        leftColumn.add(left);
                        rightColumn.add(right);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing line:" + line);
                    }
                }
            }
        }
    }
}
