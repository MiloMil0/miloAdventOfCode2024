package days;

import helper.Parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Deque;

public class Day5 implements Day {

    private ArrayList<String> lines = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> inCorrectSeq = new ArrayList<>();
    private HashMap<Integer, ArrayList<Integer>> index = new HashMap<>();

    @Override
    public void solvePartOne() {
        // String filePath = "inputs/example.txt";
        String filePath = "inputs/day5.txt";

        lines = Parser.readFile(filePath);

        if (lines == null) {
            return;
        }

        ArrayList<String> firstPart = new ArrayList<>();
        ArrayList<String> secondPart = new ArrayList<>();

        boolean findSecondPart = false;
        for (String line : lines) {
            if (line.contains("|")) {
                firstPart.add(line);
            }

            if (!findSecondPart && !line.contains("|")) {
                findSecondPart = true;
            }

            if (findSecondPart) {
                secondPart.add(line);
            }
        }

        index = parseFirstPart(firstPart);
        ArrayList<ArrayList<Integer>> correctSequences = new ArrayList<>();

        for (String line : secondPart) {
            String trimmedLine = line.trim();
            if (!trimmedLine.isEmpty()) {
                ArrayList<Integer> parts = new ArrayList<>();
                for (String part : trimmedLine.split(",")) {
                    parts.add(Integer.parseInt(part.trim()));
                }

                if (isCorrectSequence(parts)) {
                    correctSequences.add(parts);
                } else {
                    inCorrectSeq.add(parts);
                }
            }
        }

        int sum = sumMiddleNumbers(correctSequences);
        System.out.println("The sum of all the middle numbers = " + sum);
    }

    @Override
    public void solvePartTwo() {
        ArrayList<ArrayList<Integer>> reorderedSequences = new ArrayList<>();

        for (ArrayList<Integer> nums : inCorrectSeq) {

            ArrayList<Integer> reordered = reorderSequence(nums);

            while (!isCorrectSequence(reordered)) {
                reordered = reorderSequence(reordered);
            }

            reorderedSequences.add(reordered);
        }

        int sum = sumMiddleNumbers(reorderedSequences);
        System.out.println("The sum of all the reordered middle numbers = " + sum);
    }

    private boolean isCorrectSequence(ArrayList<Integer> parts) {
        boolean correctSequence = true;
        for (int j = 0; j < parts.size() - 1; j++) {
            int num = parts.get(j);

            if (index.containsKey(num)) {
                ArrayList<Integer> values = index.get(num);
                int nextNum = parts.get(j + 1);
                if (!values.contains(nextNum)) {
                    correctSequence = false;
                    break;
                }
            } else {
                correctSequence = false;
                break;
            }
        }
        return correctSequence;
    }

    private int sumMiddleNumbers(ArrayList<ArrayList<Integer>> sequences) {
        int sum = 0;

        for (ArrayList<Integer> nums : sequences) {
            if (nums.isEmpty()) {
                continue;
            }
            int stuckInTheMiddle = nums.size() / 2;
            int numberInTheMiddle = nums.get(stuckInTheMiddle);
            sum += numberInTheMiddle;

        }

        return sum;
    }

    public ArrayList<Integer> reorderSequence(ArrayList<Integer> numbers) {
        Deque<Integer> deque = new ArrayDeque<>();

        deque.add(numbers.get(0));

        for (int x = 1; x < numbers.size(); x++) {
            int num = numbers.get(x);
            ArrayList<Integer> values = index.get(num);

            if (values != null && values.contains(deque.peekLast())) {
                deque.addFirst(num);
            } else {
                deque.addLast(num);
            }
        }

        ArrayList<Integer> orderedSeq = new ArrayList<>();
        for (int num : deque) {
            orderedSeq.add(num);
        }

        return orderedSeq;
    }

    public static HashMap<Integer, ArrayList<Integer>> parseFirstPart(ArrayList<String> firstPart) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (String line : firstPart) {
            String[] parts = line.split("\\|");
            int key = Integer.parseInt(parts[0].trim());
            int value = Integer.parseInt(parts[1].trim());

            map.computeIfAbsent(key, _ -> new ArrayList<>()).add(value);
        }
        return map;
    }
}
