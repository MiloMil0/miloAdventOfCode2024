package days;

import java.util.ArrayList;

import helper.Parser;

public class Day13 implements Day {
    ArrayList<long[]> matrixX = new ArrayList<>();
    ArrayList<long[]> matrixY = new ArrayList<>();

    @Override
    public void solvePartOne() {
        parseMatrix("inputs/day13.txt");
        long total = 0;
        for (long i = 0; i < matrixX.size(); i++) {

            long[] pushes = solveMatrix(matrixX.get((int)i), matrixY.get((int)i));
            total += pushes[0] * 3 + pushes[1];
        }
        System.out.println("The total button pushes is: " + total);
    }

    @Override
    public void solvePartTwo() {
        long total = 0;
        for (long i = 0; i < matrixX.size(); i++) {
            matrixX.get((int)i)[2] += 10000000000000L;
            matrixY.get((int)i)[2] += 10000000000000L;
            long[] pushes = solveMatrix(matrixX.get((int)i), matrixY.get((int)i));
            total += pushes[0] * 3 + pushes[1];
        }
        System.out.println("The new total button pushes is: " + total);
    }

    private long[] solveMatrix(long[] matrixX, long[] matrixY) {
        long detA = (matrixX[0] * matrixY[1]) - (matrixX[1] * matrixY[0]);
        if (detA == 0) {
            return new long[] { 0, 0 };
        }
        long numA = (matrixY[1] * matrixX[2]) + (-matrixX[1] * matrixY[2]);
        long numB = (-matrixY[0] * matrixX[2]) + (matrixX[0] * matrixY[2]);

        if (numA % detA != 0 || numB % detA != 0) {
            return new long[] { 0, 0 };
        }

        return new long[] { (numA / detA), (numB / detA) };
    }

    private void parseMatrix(String filepath) {
        ArrayList<String> lines = Parser.readFile(filepath);
        if (lines == null) {
            return;
        }
        long[] xValues = new long[3];
        long[] yValues = new long[3];
        int index = 0;
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) {
                if (index > 0) {
                    matrixX.add(xValues.clone());
                    matrixY.add(yValues.clone());
                    index = 0;
                }
            } else if (line.startsWith("Button A")) {
                String[] parts = line.split(", ");
                xValues[index] = extractNumber(parts[0]);
                yValues[index++] = extractNumber(parts[1]);
            } else if (line.startsWith("Button B")) {
                String[] parts = line.split(", ");
                xValues[index] = extractNumber(parts[0]);
                yValues[index++] = extractNumber(parts[1]);
            } else if (line.startsWith("Prize")) {
                String[] parts = line.split(", ");
                xValues[index] = extractNumber(parts[0].replace("X=", "X+"));
                yValues[index++] = extractNumber(parts[1].replace("Y=", "Y+"));
            }

        }
        if (index > 0) {
            matrixX.add(xValues.clone());
            matrixY.add(yValues.clone());
        }
    }

    private long extractNumber(String part) {
        return Long.parseLong(part.substring(part.indexOf('+') + 1).trim());
    }

}
