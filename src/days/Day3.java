package days;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.Parser;

public class Day3 implements Day {

    private ArrayList<String> lines = new ArrayList<>();
    private static String regex = "mul\\((\\d+),(\\d+)\\)";

    @Override
    public void solvePartOne() {
        String filePath = "inputs/day3_1.txt";
        // String filePath = "inputs/example.txt";

        lines = Parser.readFile(filePath);
        int total = 0;
        Pattern pattern = Pattern.compile(regex);

        if(lines != null) {
            for(String line: lines) {
                Matcher matcher = pattern.matcher(line);

                while(matcher.find()) {
                    total += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                }
            }
        } 

        System.out.println("the total sum = " + total);

    }

    @Override
    public void solvePartTwo() {
        int total = 0;

        ArrayList<String> subStrings = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);

        boolean doIt = true;
        if(lines != null) {
            for(String line: lines) {
                String splitAt = "(?=do\\(\\))|(?=don't\\(\\))";

                String[] subs = line.split(splitAt);
                for(String s: subs) {
                    subStrings.add(s);
                }
            }
        }
        
        for(String line: subStrings) {
            if (line.length() >= 4 && line.substring(0, 4).equals("do()")) {
            doIt = true;
            } else if (line.length() >= 7 && line.substring(0, 7).equals("don't()")) {
            doIt = false;
            }

            Matcher matcher = pattern.matcher(line);

            while(doIt && matcher.find()) {
                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(2));
                total += num1 * num2;
                System.out.println("multiplying: " + num1 + " * " + num2);
            }

        }

        
        System.out.println("the total of Day 3 part 2 = " +total);
    }
}
