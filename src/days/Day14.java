package days;

import java.util.ArrayList;
import java.util.HashSet;

import helper.Coordinate;
import helper.Parser;

public class Day14 implements Day {
    private ArrayList<Coordinate> startLocations = new ArrayList<>();
    private ArrayList<Coordinate> velocities = new ArrayList<>();
    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;

    @Override
    public void solvePartOne() {
        parseTextFile("inputs/day14.txt");
        int total = 0, zoneOne = 0, zoneTwo = 0, zoneThree = 0, zoneFour = 0;
        for (int i = 0; i < startLocations.size(); i++) {
            Coordinate start = startLocations.get(i);
            Coordinate velocity = velocities.get(i);

            int velocityX = velocity.getX() * 100;
            int velocityY = velocity.getY() * 100;

            int newX = (((velocityX + start.getX()) % WIDTH) + WIDTH) % WIDTH;
            int newY = (((velocityY + start.getY()) % HEIGHT) + HEIGHT) % HEIGHT;
            int middleX = (WIDTH - 1) / 2;
            int middleY = (HEIGHT - 1) / 2;

            if (newX >= 0 && newX < middleX && newY >= 0 && newY < middleY) {
                zoneOne++;
            } else if (newX > middleX && newX < WIDTH && newY >= 0 && newY < middleY) {
                zoneTwo++;
            } else if (newX >= 0 && newX < middleX && newY > middleY && newY < HEIGHT) {
                zoneThree++;
            } else if (newX > middleX && newX < WIDTH && newY > middleY && newY < HEIGHT) {
                zoneFour++;
            }
        }
        total = (zoneOne * zoneTwo * zoneThree * zoneFour);

        System.out.println("the safety factor is: " + total);
    }

    @Override
    public void solvePartTwo() {
        int seconds = 0;
        boolean xMasTree = false;
        HashSet<Coordinate> robotLocations = new HashSet<>();

        while (!xMasTree) {
            robotLocations.clear();
            xMasTree = true;

            for (int i = 0; i < startLocations.size(); i++) {
                Coordinate start = startLocations.get(i);
                Coordinate velocity = velocities.get(i);

                int velocityX = velocity.getX() * seconds;
                int velocityY = velocity.getY() * seconds;

                int newX = (((velocityX + start.getX()) % WIDTH) + WIDTH) % WIDTH;
                int newY = (((velocityY + start.getY()) % HEIGHT) + HEIGHT) % HEIGHT;

                Coordinate newPosition = new Coordinate(newX, newY);

                if (!robotLocations.add(newPosition)) {
                    xMasTree = false;
                    seconds++;
                    break;
                }
            }
        }

        System.out.println("Christmas tree found after: " + seconds + " seconds");
    }

    private void parseTextFile(String filePath) {
        ArrayList<String> lines = Parser.readFile(filePath);
        if (lines == null) {
            return;
        }
        for (String line : lines) {
            String[] parts = line.split(" ");
            String[] pCoords = parts[0].substring(2).split(",");
            String[] vCoords = parts[1].substring(2).split(",");

            Coordinate start = new Coordinate(Integer.parseInt(pCoords[0]), Integer.parseInt(pCoords[1]));
            Coordinate velocity = new Coordinate(Integer.parseInt(vCoords[0]), Integer.parseInt(vCoords[1]));

            startLocations.add(start);
            velocities.add(velocity);
        }
    }
}
