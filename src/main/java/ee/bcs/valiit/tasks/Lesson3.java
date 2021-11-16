package ee.bcs.valiit.tasks;

import java.util.Arrays;
import java.util.HashMap;

public class Lesson3 {


    public static void main(String[] args) {
        int[] array = {6, 2, 7, 1, 8, 3};
        // System.out.println(reverseString("Tundub et tootab"));
        //System.out.println(isPrime(17));
        //System.out.println(Arrays.toString(sort(array)));
        //System.out.println(morseCode("hello"));
        evenFibonacci(10);

    }

    // TODO tagasta x faktoriaal.
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120
    public static int factorial(int x) {
        int multiplied = 1;

        for (int i = x; i > 0; i--) {

            multiplied = multiplied * i;

        }
        return multiplied;
    }

    // TODO tagasta string tagurpidi
    public static String reverseString(String a) {
        char[] ch = a.toCharArray();
        String reversed = "";

        for (int i = ch.length - 1; i >= 0; i--) {

            reversed += ch[i];

        }

        return reversed;
    }

    // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
    public static boolean isPrime(int x) {
        if (x == 2) {
            return true;
        }
        if (x == 1) {
            return false;
        }

        boolean prime = true;
        for (int i = 2; i < x ; i++) {
            if (x % i == 0) {

                prime = false;
                break;
            }


        }
        return prime;


    }


    // TODO sorteeri massiiv suuruse järgi.
    // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
    public static int[] sort(int[] a) {

        int[] copy = a.clone();                    // teen koopia arrayst

        for (int i = 0; i < copy.length; i++) {        // loopin a array alates indexist 0

            for (int j = i + 1; j < copy.length; j++) {      // esimsene loop seisab niikaua kuni teises loopis v6rdleme
                //millised j2rnevad arvud on v2iksemad ja vahetame kohad

                if (copy[j] < copy[i]) {
                    int temp = copy[i];
                    copy[i] = copy[j];
                    copy[j] = temp;

                }
            }

        }
        return copy;

    }

    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0
    // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
    public static int evenFibonacci(int x) {
        int[] fibArray = new int[x];                         // genereerin fibonacci array
        fibArray[0] = 0;
        fibArray[1] = 1;
        fibArray[2] = 1;
        int fibSum = 0;
        for (int i = 3; i < fibArray.length; i++) {

            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
            if (fibArray[i] > x) {                              // kui fib arvu v22rtus suurem kui x siis j2tab vahele kuni loopi l6puni

                continue;
            }

            if (fibArray[i] % 2 == 0) {
                fibSum += fibArray[i];
            }

        }


        System.out.println(Arrays.toString(fibArray));


        System.out.println(fibSum);
        return fibSum;
    }

    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja - ning eralda kõik tähed tühikuga
        HashMap<Character, String> tekstToMorse = new HashMap<>();    // teen uue hashmapi interface

        tekstToMorse.put('s', "... ");                                // lisan elementide key value paare
        tekstToMorse.put('o', "--- ");
        tekstToMorse.put('h', ".... ");
        tekstToMorse.put('e', ". ");
        tekstToMorse.put('l', ".-.. ");
        tekstToMorse.put(' ', " ");

        char[] charArray = text.toCharArray();                      // sisestatud tekst char arrayks
        String morse = "";

        for (int i = 0; i < charArray.length; i++) {                //itereerin selles arrays

            String morse1 = tekstToMorse.get(charArray[i]);         //v6tan mapist vastava t2he value

            morse += morse1;                                        // lisan value morse stringi

        }


        return morse.trim();
    }
}
