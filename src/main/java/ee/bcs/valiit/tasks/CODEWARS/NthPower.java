package ee.bcs.valiit.tasks.CODEWARS;

public class NthPower {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(nthPower(array1, 8));

    }

    public static int nthPower(int[] array, int n) {

        if (n < array.length) {

            int number = array[n];
            return (int) Math.pow(number, n);
        }


        return -1;
    }


}
