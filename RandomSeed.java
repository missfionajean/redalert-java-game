import java.util.Arrays;

// runnable program to test random function
public class RandomSeed {
    public static void main(String[] args) {

        // generating seed container
        int[] seed = new int[20];

        // declaring count variables to ensure equality
        int zeroCount = 0;
        int oneCount = 0;

        // for loop to access array index and update
        for (int i = 0; i < seed.length; i++) {
            // randomly generates a 1 or a 0
            int randomBinary = (int)(Math.random() * 2);

            // ensures there are exactly ten of each
            if (randomBinary == 0) {
                if (zeroCount < 10) {
                    seed[i] = randomBinary;
                    zeroCount++;
                } else {
                    seed[i] = 1;
                    oneCount++;
                }
            } else if (randomBinary == 1) {
                if (oneCount < 10) {
                    seed[i] = randomBinary;
                    oneCount++;
                } else {
                    seed[i] = 0;
                    zeroCount++;
                }
            } 
        }
        
        // prints array in string format for testing in vacuum
        System.out.println(Arrays.toString(seed));
      }
}
