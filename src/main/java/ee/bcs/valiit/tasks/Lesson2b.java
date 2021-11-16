package ee.bcs.valiit.tasks;

public class Lesson2b {

    public static void main(String[] args) {

        int[] newArray = {5, 4, 3, 4, 3, 6, 2, 8, 9, 10};
        //System.out.println(Arrays.toString(reverseArray(newArray)));

        //System.out.println(Arrays.toString(evenNumbers(10)));

        //System.out.println(max(newArray));

        //System.out.println(fibonacci(6));

        multiplyTable(5, 5);

        //sequence3n(10,20);

    }

    // TODO loe funktsiooni sisendiks on täisarvude massiiv
    // TODO tagasta massiiv mille elemendid on vastupidises järiekorras
    public static int[] reverseArray(int[] inputArray) {
        int[] copy = inputArray.clone();
        int temp;
        for (int i = 0; i < copy.length; i++) {
            for (int j = i + 1; j < copy.length; j++) {         //array[i   ]= resularray{array.length -1 -i] lihtsam lahendsu

                //
                if (copy[i] < copy[j]) {
                    temp = copy[i];
                    copy[i] = copy[j];
                    copy[j] = temp;
                }
            }
        }
        return copy;

    }

    // TODO tagasta massiiv mis sisaldab n esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static int[] evenNumbers(int n) {
        int[] array = new int[999999];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }


        int[] newArray = new int[n];
        int j = 0;
        int counter = 0;
        for (int i = 0; i < array.length; i++) {            // resultarray[i]=i+1 *2 lihstam lahendus
            if (array[i] % 2 == 0) {
                counter++;
                newArray[j++] = array[i];
            }
            if (counter == n) {
                break;
            }

        }


        return newArray;
    }

    // TODO, leia massiivi kõige väiksem element
    public static int min(int[] x) {

        int min = x[0];
        for (int i = 0; i < x.length; i++) {
            int temp = x[i];
            if (temp < min) {
                min = temp;
            }
        }
        return min;


    }

    // TODO, leia massiivi kõige suurem element
    public static int max(int[] x) {


        int max = x[0];
        for (int i = 0; i < x.length; i++) {
            int temp = x[i];
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    // TODO, leia massiivi kõigi elementide summa
    public static int sum(int[] x) {
        int sum = 0;

        for (int i : x) {               //for each tsykkel
            sum += i;
        }

        return sum;


    }

    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee
    public static void multiplyTable(int x, int y) {
        for (int i = 1; i <= x; i++) {

            for (int j = 1; j <= y; j++) {
                System.out.print(i * j + "\t");   // teeb ilusaks, tab
            }
            System.out.println();
        }


    }


    // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0
    public static int fibonacci(int n) {
//
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);         // recursioon , performance kohutav

//        int previous2 = 0;
//        int previous = 1;
//        for (int i = 1; i < n;i++) {
//            int temp = previous + previous2;
//            previous2 = previous;
//            previous = temp;
//        }
//
//
//        return previous;

    }

    // TODO
    // Kujutame ette numbrite jada, kus juhul kui number on paaris arv siis me jagame selle 2-ga
    // Kui number on paaritu arv siis me korrutame selle 3-ga ja liidame 1. (3n+1)
    // Seda tegevust teeme me niikaua kuni me saame vastuseks 1
    // Näiteks kui sisend arv on 22, siis kogu jada oleks:
    // 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
    // Sellise jada pikkus on 16
    // Kirjutada programm, mis peab leidma kõige pikema jada pikkuse mis jääb kahe täis arvu vahele
    // Näiteks:
    // Sisend 10 20
    // Siis tuleb vaadata, kui pikk jada tuleb kui esimene numbr on 10. Järgmisen tuleb arvutada number 11 jada pikkus.
    // jne. kuni numbrini 20
    // Tagastada tuleb kõige pikem number
    // Näiteks sisendi 10 ja 20 puhul on vastus 20

    public static int sequence3n(int x, int y) {
        int counter = 1;
        int lastCount=0;
        int longestNum=0;
        for(int i = x; i<=y;i++){               //loopime x ja y vahemikku

            while(x>1){                         // iga numriga teeme while loopis arvutusi kuni see arv on 1 ja while loop katkeb

                if(x%2==0){
                    x=x/2;
                    counter++;
                }else{
                    x= x*3 +1;
                    counter++;
                }
            }
            if(counter>lastCount){              // kui k2esoleva nr jada on pikem siis asendame longest counteri
                lastCount = counter;
                longestNum=i;

            }

            counter=1;
            x=i+1;
        }
        System.out.println(longestNum);
        return lastCount;
    }

}

//9-28-14-7-22-11-34-17-52-26-13-
//tagastab arvu mitte jada pikkust
// 20-10-5-16-8-4-2-1