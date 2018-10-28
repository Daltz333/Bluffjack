package helpers;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {

    //thanks stackoverflow!
    public static int getRandomWithExclusion(Random rand, int start, int end, ArrayList<Integer> excludeRows) {

        if (!(excludeRows.size() == 11)) {
            int range = end - start + 1;

            int random = rand.nextInt(range) + 1;
            while (excludeRows.contains(random)) {
                random = rand.nextInt(range) + 1;
            }

            return random;
        } else {
            return -1;
        }
    }

}
