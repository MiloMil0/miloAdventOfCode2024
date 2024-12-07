package days;

import java.util.ArrayList;

import helper.Parser;

public class Day7 implements Day {

    private ArrayList<String> lines = new ArrayList<>();
    private ArrayList<ArrayList<Long>> longnumbers = new ArrayList<>();
    private ArrayList<Long> targets = new ArrayList<>();

    public void solvePartOne() {
        // String filePath = "inputs/example.txt";
        String filePath = "inputs/day7.txt";
        lines = Parser.readFile(filePath);
        if (lines == null) {
            return;
        }

        long value = 0;

        for (String line : lines) {
            String[] parts = line.split(":");

            long target = Long.parseLong(parts[0].trim());
            ArrayList<Long> numbers = new ArrayList<>();

            String[] rightNumbers = parts[1].trim().split(" ");
            for (String num : rightNumbers) {
                numbers.add(Long.parseLong(num));
            }

            targets.add(target);
            longnumbers.add(numbers);
        }
        boolean isPart2 = false;

        for (int i = 0; i < longnumbers.size(); i++) {

            if (canReachTarget(targets.get(i), longnumbers.get(i), 0, 0, isPart2)) {
                value += targets.get(i);
            }

        }

        System.out.println("Final Value part one: " + value);
    }

    @Override
    public void solvePartTwo() {
        boolean isPart2 = true;
        long value = 0;

        for (int i = 0; i < longnumbers.size(); i++) {
            if (canReachTarget(targets.get(i), longnumbers.get(i), 0, 0, isPart2)) {
                value += targets.get(i);
            }

        }
        System.out.println("Final Value part two: " + value);

    }

    private static boolean canReachTarget(long target, ArrayList<Long> numbers, long currentResult, int pos,
            boolean isPart2) {
        if (pos == numbers.size()) {
            return currentResult == target;
        }

        long currValue = numbers.get(pos);

        if (canReachTarget(target, numbers, currentResult + currValue, pos + 1, isPart2)) {
            return true;
        }
        if (canReachTarget(target, numbers, (currentResult == 0) ? currValue : currentResult * currValue, pos + 1, isPart2)) {
            return true;
        }
        if (isPart2) {
            if (canReachTarget(target, numbers, Long.parseLong(currentResult + "" + currValue), pos + 1, isPart2)) {
                return true;
            }

        }

        return false;
    }

}
