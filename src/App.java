import java.util.ArrayList;

import days.Day;
import days.Day1;

public class App {
    public static void main(String[] args) {

        ArrayList<Day> days = new ArrayList<>();
        double totalRunTime = 0.0;

        days.add(new Day1());

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

