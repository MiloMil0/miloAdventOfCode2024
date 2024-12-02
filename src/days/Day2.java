package days;

import java.util.ArrayList;
import java.util.Iterator;

import helper.Parser;

public class Day2 implements Day {

    ArrayList<String> lines = new ArrayList<>();

    @Override
    public void solvePartOne() {

        String filePath = "inputs/day2_1.txt";
        // String filePath = "inputs/example.txt";
        
        lines = Parser.readFile(filePath);
        
        int safe = 0;

        if(lines != null) {
            for(String line : lines) {
                String[] numbers = line.split(" ");
                boolean ascending = (Integer.parseInt(numbers[0]) < Integer.parseInt(numbers[numbers.length -1]));
                

                boolean allSafe = isValidSequence(ascending, convertString(numbers));
 
                if(allSafe) {
                    safe++;
                }
            }
        } 

        System.out.println("there are " + safe + " safe reports in part1");
    }

    @Override
    public void solvePartTwo() {
        int safe = 0;
        if(lines != null) {
            for(String line : lines) {
                String[] numbers = line.split(" ");
                 
                int ascendingCount = 0;
                int descendingCount = 0;
                for (int i = 0; i < numbers.length - 1; i++) {
                    int curr = Integer.parseInt(numbers[i]);
                    int nextInt = Integer.parseInt(numbers[i+1]);
                    if (curr < nextInt) {
                        ascendingCount++;
                    } else {
                        descendingCount++;
                  }
                }

                boolean ascending = (ascendingCount > descendingCount);

                boolean allSafe = checkSequence(ascending, convertString(numbers));
                if(allSafe) {
                    safe++;
                }

            }
        } 
        System.out.println("there are " + safe + " safe reports in part2");
    }

    private static boolean checkSequence(boolean ascending, ArrayList<Integer> numbers) {
        if(isValidSequence(ascending, numbers)) {
            return true;
        }
        for(int i = 0; i<numbers.size(); i++) {
            ArrayList<Integer> tempList = new ArrayList<>(numbers);
            tempList.remove(i);
            if(isValidSequence(ascending, tempList)) {
                return true;
            }  
        }
        return false;

    }

    private static boolean isValidSequence(boolean ascending, ArrayList<Integer> numbers) {
        for(int i = 0; i<numbers.size() - 1; i++) {
            int current = numbers.get(i);
            int next = numbers.get( i + 1);
            int difference = Math.abs(current - next);
            boolean validDiff = (difference > 0 && difference <= 3);
            
            if(ascending) {
                if(!(next > current && validDiff)) {
                    return false;
                }
            } else {
                if(!(next < current && validDiff)) {
                    return false;
                }
            }
        }
        return true;
    }

    private ArrayList<Integer> convertString(String[] line) {
        ArrayList<Integer> numberList = new ArrayList<>();

            for(String num: line) {
                numberList.add(Integer.parseInt(num));
            }

        return numberList;
    }

    

}
