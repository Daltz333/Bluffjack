package helpers;

import java.util.ArrayList;
import java.util.Random;

public class Utilities {

    //thanks stackoverflow!
    //generates a random int
    public static int getRandomWithExclusion(Random rand, int start, int end, ArrayList<Integer> exclusion) {
        //do not run if we have filled up arraylist
        if (!(exclusion.size() == end)) {
            int range = end - start + 1;

            int random = rand.nextInt(range) + 1;
            while (exclusion.contains(random)) {
                random = rand.nextInt(range) + 1;
            }

            return random;
        } else {
            return -1;
        }
    }

}
