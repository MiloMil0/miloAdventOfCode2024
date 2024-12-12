import java.util.ArrayList;

import days.*;

public class App {
    public static void main(String[] args) {

        ArrayList<Day> days = new ArrayList<>();
        double totalRunTime = 0.0;

        days.add(new Day1());
        days.add(new Day2());
        days.add(new Day3());
        days.add(new Day4());
        days.add(new Day5());
        days.add(new Day6());
        days.add(new Day7());
        days.add(new Day8());
        days.add(new Day9());
        days.add(new Day10());
        days.add(new Day11());

        for (Day day : days) {
            totalRunTime += calculateRunTime(day);
        }

        System.out.println("Total runtime so far: " + totalRunTime);
    }

    private static double calculateRunTime(Day day) {

        long part1Start = System.nanoTime();
        day.solvePartOne();
        long part1End = System.nanoTime();
        double part1TimeInSeconds = (part1End - part1Start) / 1_000_000_000.0;  

        long part2Start = System.nanoTime();
        day.solvePartTwo();
        long part2End = System.nanoTime();
        double part2TimeInSeconds = (part2End - part2Start) / 1_000_000_000.0;  

        double totalDayTimeInSeconds = part1TimeInSeconds + part2TimeInSeconds;
        System.out.println("Total runtime for " + day.getClass().getSimpleName() + ": " + totalDayTimeInSeconds + " seconds");

        return totalDayTimeInSeconds;
    }

}

