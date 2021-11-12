package ee.bcs.valiit.tasks;

import java.util.Arrays;
import java.util.Collections;

public class Lesson2 {
    public static void main(String[] args) {
        // TODO siia saab kirjutada koodi testimiseks

        //System.out.println(Arrays.toString(sampleArray()));
        System.out.println(Arrays.toString(decreasingArray(10)));


    }

    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public static int[] sampleArray() {

        return new int[]{1, 2, 3, 4, 5};
    }

    // TODO tagasta n esimest arvu alates 1-st
    // näiteks
    // sisend: 5
    // trüki välja: 1 2 3 4 5
    public static int[] firstN(int n) {
        int[] Nmassiiv = new int[n];
        for (int i = 0; i < Nmassiiv.length; i++) {
            Nmassiiv[i] = i + 1;
            System.out.println("index " + i + " v22rtus on  " + Nmassiiv[i]);
        }


        return Nmassiiv;
    }

    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public static int[] generateArray(int n) {

        int[] newArray = new int[n];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = i + 1;
            System.out.println("index " + i + " v22rtus on  " + newArray[i]);
        }


        return newArray;
    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0
    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {
        int n2 = Math.abs(n);
        int[] Nmassiiv = new int[n2+1];

        if (n < 0) {
            for (int i = Nmassiiv.length - 1; i >= 0; i--) {
                Nmassiiv[i]= -1*(n2-i);
                System.out.println(Nmassiiv[i]);
            }
            return Nmassiiv;


        } else {
            for (int i = Nmassiiv.length - 1; i >= 0; i--) {
                Nmassiiv[i]= n-i;
                System.out.println(Nmassiiv[i]);
            }


            }
            return Nmassiiv;

        }






    // TODO
    // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
    public static int[] yl3(int n) {
        int[] kolm = new int[n];

        for (int i = 0; i < kolm.length; i++) {
            kolm[i] = 3;
        }


        return kolm;
    }
}
