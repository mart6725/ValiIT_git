package ee.bcs.valiit.tasks.CODEWARS;

import java.util.Arrays;

public class MinMax {

    public static void main(String[] args) {
        int[] array = {15, 11, 10, 7, 12};
        System.out.println(Arrays.toString(solve(array)));


    }

    public static int[] solve(int[] arr) {

        int[] copy = arr.clone();
        int counter = 0;

        for (int i = 0; i < copy.length; i++) {
            counter++;
            for (int j = i + 1; j < copy.length; j++) {
                if (counter % 2 == 0) {

                    if (copy[j] < copy[i]) {
                        int temp = copy[i];
                        copy[i] = copy[j];
                        copy[j] = temp;
                    }

                }else{
                    if (copy[j] > copy[i]) {
                        int temp = copy[i];
                        copy[i] = copy[j];
                        copy[j] = temp;
                    }

                }
            }

        }
        return copy;


    }


}
