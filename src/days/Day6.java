package days;

import java.util.ArrayList;

import helper.Parser;

public class Day6 implements Day {

    private ArrayList<String> lines = new ArrayList<>();

    @Override
    public void solvePartOne() {
        String filePath = "inputs/example.txt";
        //String filePath = "inputs/day6.txt";
        
        lines = Parser.readFile(filePath);

        if(lines == null) {
            return;
        }

        for(String line: lines) {
            //do the lines!
        }
    }

    @Override
    public void solvePartTwo() {
    }

}
