package ee.bcs.valiit.tasks.CODEWARS;

public class SumDifferences {
    public static void main(String[] args) {
        int[] array = {};
        System.out.println(sumOfDifferences(array));
    }

    public static int sumOfDifferences(int[] arr) {         // teeks array kahanevaks[10,2,1] ja siis (10-2) + (2-1) =9

        if(arr.length==0 || arr.length==1){
            return 0;
        }

        int[] copy = arr.clone();

        for (int i = 0; i < copy.length; i++) {             // teeme array kahanevaks
            for (int j = i + 1; j < copy.length; j++) {
                if (copy[j] > copy[i]) {
                    int temp = copy[i];
                    copy[i] = copy[j];
                    copy[j] = temp;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < copy.length - 1; i++) {                    // leiame sumofDiffernces
            sum += copy[i] - copy[i + 1];

        }
        return sum;
    }
}
