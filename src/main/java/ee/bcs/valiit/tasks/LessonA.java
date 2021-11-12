package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class LessonA {

    public static void main(String[] args) {
    int []  massiv= {1,6,3,4,5,6,7,8,9,10};
        e12(massiv);

    }


    public static int e1(int a) {
        return -1 * a;
    }

    public static int e2(int a, int b) {
        return (a + b) / 2;
    }

    public static int e3(int x) {
        // lahuta sisendist 5 ja siis korruta 99
        return (x - 5) * 99;
    }

    public static int e4(int a1, int b1, int a2, int b2, int a3, int b3) {
        // korruta a1 b1-ga, a2 b2-ga jne. Ning siis liida saadud numbrid
        int ab1 = a1 * b1;
        int ab2 = a2 * b2;
        int ab3 = a3 * b3;
        int sum = ab1 + ab2 + ab3;
        return sum;

    }

    public static int e5() {
        // return the answer to the Life, the Universe, and Everything.
        return 42;
    }

    public static boolean e6(int x) {
        // Kas arv on liigaasta
        // Wikipeediast:
        // Iga aasta, mis jagub neljaga, on liigaasta (kui ta samal ajal ei jagu sajaga). Kui aasta jagub sajaga ja ei jagu neljasajaga siis ta ei ole liigaasta. Aasta, mis jagub neljasajaga, on alati liigaasta.
        //See tähendab näiteks, et aastad 1984 ja 2000 olid liigaastad, 1900 aga mitte.
        if (x < 1 || x > 9999) {
            return false;
        } else if ((x % 4 == 0 && x % 100 != 0) || x % 400 == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean e7(boolean x) {
        // tagasta x-i vastand väärtus
        return !x;
    }

    public static boolean e8(boolean x1, boolean x2) {

        return x1 ^ x2;     // XOR meetod




    }
        // tagasta true kui ainult 1 sisend muutujatest on true
//        if ((x1 && !x2) || (!x1 && x2)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static boolean e9(boolean x1, boolean x2, boolean x3, boolean x4) {
        // tagasta true kui paaritu arv sisend muutujatest on true
        boolean[] booleans = {x1, x2, x3, x4};
        int sum = 0;
        for (int i = 0; i < booleans.length; i++) {

            if (booleans[i]) {
                sum++;

            }
        }
        if (sum % 2 != 0) {

            return true;
        }
        return false;
    }

    public static void e10(int x[]) {
        // muuda sisend massiivi nii et kõik elemendid oleksid 2x suuremad
        for (int i = 0; i < x.length; i++) {

            x[i] *= 2;

            System.out.println(x[i]);
        }
    }

    public static void e11(int x[]) {
        x[1] = 0;
        // määra sisend massiivi teine element (index 1) 0-iks
    }

    public static void e12(int x[]) {
        // vaheta massiivi esimene ja teine element omavahel// lahenda nii et ei defineeri muutujat
//        int temp0 = x[0];
//        int temp1 = x[1];
//        x[1] = temp0;
//        x[0] = temp1;



        System.out.println(Arrays.toString(x));


    }

    public static void e13(int x[]) {
        // määra massiivi teise elemendi väärtuseks sama mis esimesel elemendil
//        int temp = x[0];
//        x[1] = temp;
        x[1]=x[0];


    }

    public static void e14(int x[]) {
        // määra massiivi teise elemendi väärtuseks sama mis esimesel elemendil
        // määra massiivi neljanda elemendi väärtuseks sama mis kolmandal elemendil
        // määra massiivi kuuenda elemendi väärtuseks sama mis viiendal elemendil
        // määra massiivi kaheksanda elemendi väärtuseks sama mis seitsmendal elemendil
        int temp1 = x[0];
        x[1] = temp1;


        int temp2 = x[2];
        x[3] = temp2;

        int temp3 = x[4];
        x[5] = temp3;

        int temp = x[6];
        x[7] = temp;


    }

    public static void e15(int x[]) {
        // määra iga teine (indeksid 1, 3, jne) element massiivis samaks, mis oli talle eelnenud elemendi väärtus

        for (int i = 0; i < x.length; i++) {


               if (i % 2 != 0) {
                   x[i]=x[i-1];



               }
            System.out.println(x[i]);


        }


    }

}


