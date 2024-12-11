package days;

import java.math.BigInteger;
import java.util.ArrayList;

import helper.Parser;

public class Day9 implements Day {
    private ArrayList<String> lines = new ArrayList<>();
    private ArrayList<Integer> spaces = new ArrayList<>();
    private ArrayList<Integer> fileID = new ArrayList<>();
    private long valueCounter = 0;

    @Override
    public void solvePartOne() {
        String filePath = "inputs/day9.txt";
        lines = Parser.readFile(filePath);
        if (lines == null) {
            return;
        }
        StringBuilder inputLine = new StringBuilder();
        for (String line : lines) {
            inputLine.append(line.trim());
        }

        for (int i = 0; i < inputLine.length(); i++) {
            int value = Character.getNumericValue(inputLine.charAt(i));

            if (i % 2 == 0) {
                fileID.add(value);
                valueCounter += value;
            } else {
                spaces.add(value);
            }
        }
        System.out.println("Total value part1: " + calculateSequence(false));
    }

    @Override
    public void solvePartTwo() {
        ArrayList<int[]> fileSpaceData = new ArrayList<>();
        ArrayList<int[]> spaceData = new ArrayList<>();
        int counter = 0;

        for (int i = 0; i < fileID.size(); i++) {
            fileSpaceData.add(new int[] { counter, fileID.get(i), i });
            counter += fileID.get(i);

            if (i < spaces.size()) {
                spaceData.add(new int[] { counter, spaces.get(i) });
                counter += spaces.get(i);
            }
        }
        BigInteger total = BigInteger.ZERO;

        for (int i = fileSpaceData.size() - 1; i >= 0; i--) {
            int progressIndex = 0;
            int fileSpace = fileSpaceData.get(i)[1];
            int availableSpace = 0;

            while (progressIndex < i) {
                availableSpace = spaceData.get(progressIndex)[1];
                int startRange = spaceData.get(progressIndex)[0];
                if (fileSpace <= availableSpace) {
                    total = total.add(
                            BigInteger.valueOf(i).multiply(BigInteger.valueOf(calculateRange(startRange, fileSpace))));
                    fileSpaceData.remove(i);
                    spaceData.set(progressIndex, new int[] { startRange + fileSpace, availableSpace - fileSpace });
                    break;
                } else {
                    progressIndex++;
                }
            }
        }

        for (int[] pair : fileSpaceData) {
            total = total
                    .add(BigInteger.valueOf(pair[2]).multiply(BigInteger.valueOf(calculateRange(pair[0], pair[1]))));
        }

        System.out.println("Total value part2: " + total);
    }

    private long calculateSequence(boolean part2) {
        long total = 0;

        ArrayList<Integer> fileIDCopy = new ArrayList<>(fileID);

        int counter = 0;
        long revMap = fileIDCopy.size() - 1;
        int iterations = 0;
        int currSpaces = 0;
        int index = 0;

        while (counter <= valueCounter) {
            if (index >= fileIDCopy.size() || index >= spaces.size()) {
                break;
            }
            iterations = fileIDCopy.get(index);

            while (iterations > 0 && counter < valueCounter) {
                total += index * counter;
                counter++;
                iterations--;
            }

            currSpaces = spaces.get(index);

            while (currSpaces > 0 && counter < valueCounter && revMap > 0) {
                int revMapEntry = Math.min(fileIDCopy.get((int) revMap), currSpaces);

                total += revMap * calculateRange(counter, revMapEntry);

                counter += revMapEntry;
                currSpaces -= revMapEntry;
                fileIDCopy.set((int) revMap, fileIDCopy.get((int) revMap) - revMapEntry);

                if (fileIDCopy.get((int) revMap) <= 0) {
                    revMap--;
                }
            }

            index++;
        }
        return total;
    }

    private static int calculateRange(int pos, int range) {
        int endCounter = pos + range - 1;
        int rangeLength = endCounter - pos + 1;

        return rangeLength * (pos + endCounter) / 2;

    }

}
