package days;

import helper.Parser;

import java.util.*;

public class Day11 implements Day {
    ArrayList<Long> starterStones = new ArrayList<>();
    Map<Long, ArrayList<Long>> memo = new HashMap<>();
    Map<Long, Long> stoneCounts = new HashMap<>(); 

    @Override
    public void solvePartOne() {
        String filePath = "inputs/day11.txt";
        ArrayList<String> lines = Parser.readFile(filePath);
        if (lines == null) {
            return;
        }

        lines.removeIf(String::isEmpty);
        starterStones = Parser.parseLongFromLine(lines.get(0), " ");

        for (Long stone : starterStones) {
            stoneCounts.put(stone, stoneCounts.getOrDefault(stone, 0L) + 1);
        }

        for (int i = 0; i < 25; i++) {
            countStones();
        }

        System.out.println("Amount of stones for part1: " + stoneCounts.values().stream().mapToLong(Long::longValue).sum());
    }

    @Override
    public void solvePartTwo() {
        //first comment :) the reason this is 50 is because I already did 25
        for (int i = 0; i < 50; i++) {
            countStones();
        }

        System.out.println("Amount of stones for part2: " + stoneCounts.values().stream().mapToLong(Long::longValue).sum());
    }

    private void countStones() {
        Map<Long, Long> newStoneCounts = new HashMap<>();

        for (Map.Entry<Long, Long> stones : stoneCounts.entrySet()) {
            Long stone = stones.getKey();
            Long count = stones.getValue();

            if (memo.containsKey(stone)) {
                for (Long result : memo.get(stone)) {
                    newStoneCounts.put(result, newStoneCounts.getOrDefault(result, 0L) + count);
                }
            } else {
                ArrayList<Long> result = blinkOnce(stone);
                memo.put(stone, result);

                for (Long res : result) {
                    newStoneCounts.put(res, newStoneCounts.getOrDefault(res, 0L) + count);
                }
            }
        }

        stoneCounts.clear();
        stoneCounts.putAll(newStoneCounts);
    }

    private ArrayList<Long> blinkOnce(Long stone) {
        ArrayList<Long> result = new ArrayList<>();
        if (stone == 0) {
            result.add(1L);
        } else if (String.valueOf(Math.abs(stone)).length() % 2 == 0) {
            int digitCount = (int) Math.log10(stone) + 1;
            int divisor = (int) Math.pow(10, digitCount / 2);
            result.add(stone / divisor);
            result.add(stone % divisor);
        } else {
            result.add(stone * 2024);
        }
        return result;
    }
}
