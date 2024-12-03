package helper;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<Integer> convertString(String[] line) {
        ArrayList<Integer> numberList = new ArrayList<>();

            for(String num: line) {
                numberList.add(Integer.parseInt(num));
            }

        return numberList;
    }
}
