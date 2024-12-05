package helper;

import java.util.ArrayList;

public class Coordinate {
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    public static final int[][] DIRECTIONS_DIAG = {
        {1, 0},    
        {0, 1},    
        {-1, 0},   
        {0, -1},   
        {-1, -1},  
        {1, -1},   
        {-1, 1},   
        {1, 1}     
    };
    
    public static final int[][] DIRECTIONS_NO_DIAG = {
        {1, 0},    
        {0, 1},    
        {-1, 0},   
        {0, -1},   
    };


    public static boolean checkPerimeter(int x, int y, int rows, int cols, int size, char[][] grid) {
        return (x + size < cols && x - size >= 0 && y + size < rows && y - size >= 0);
    }

}
