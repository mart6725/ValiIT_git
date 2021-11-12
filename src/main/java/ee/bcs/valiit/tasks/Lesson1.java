package ee.bcs.valiit.tasks;


import java.util.Scanner;

// TODO kasuta if/else. Ära kasuta Math librarit
public class Lesson1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println("vali meetod 0-7, ja sisesta nr");
            System.out.println("0 -> prindi String");
            System.out.println("1 -> leia kahe arvu min");
            System.out.println("2 -> leia kahe arvu max");
            System.out.println("3 -> leia arvu absoluutArv");
            System.out.println("4 -> kas arv on paaris v6i paaritu");
            System.out.println("5 -> leia kolme arvu MIN");
            System.out.println("6 -> leia kolme arvu MAX");
            System.out.println("7 -> Sulge programm");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    someString();
                    break;
                case 1:
                    System.out.println("sisesta esimene nr ");
                    int esimene = scanner.nextInt();
                    System.out.println("sisesta teine nr ");
                    int teine = scanner.nextInt();
                    System.out.println("miinimum nendest on " + min(esimene, teine));
                    break;
                case 2:
                    System.out.println("sisesta esimene nr ");
                    int esimene1 = scanner.nextInt();
                    System.out.println("sisesta teine nr ");
                    int teine1 = scanner.nextInt();
                    System.out.println("maximum nendest on " + max(esimene1, teine1));
                    break;
                case 3:
                    System.out.println("tagastab abs arvu, sisesta arv");
                    int arv = scanner.nextInt();
                    System.out.println("abs sellest arvust on " + abs(arv));
                    break;
                case 4:
                    System.out.println("sisesta arv ja saa teada kas see on paaris arv ");
                    int arv1 = scanner.nextInt();
                    boolean isEven = isEven(arv1);
                    if(isEven){
                        System.out.println("paaris arv");

                    }else{
                        System.out.println("paaritu");
                    }
                    break;
                case 5:
                    System.out.println("sisesta kokku kolm arvu ja leia min, sisesta esimene");
                    int arvEsimene = scanner.nextInt();
                    System.out.println("siseta teine");
                    int arvTeine = scanner.nextInt();
                    System.out.println("sisesta kolmas");
                    int arvKolmas = scanner.nextInt();
                    System.out.println("nende kolme arvu V2IKSEIM on " + min3(arvEsimene, arvTeine, arvKolmas));
                    break;
                case 6:
                    System.out.println("sisesta kokku kolm arvu ja leia MAX, sisesta esimene");
                    int arvEsimene1 = scanner.nextInt();
                    System.out.println("siseta teine");
                    int arvTeine2 = scanner.nextInt();
                    System.out.println("sisesta kolmas");
                    int arvKolmas3 = scanner.nextInt();
                    System.out.println("nende kolme arvu SUURIM on " + min3(arvEsimene1, arvTeine2, arvKolmas3));
                    break;
                case 7:
                    System.out.println("SULGEB....");
                    quit = true;
                    break;

            }
        }


        // Siia sisse võid sa kirjutada koodi, et testida kas su meetodid töötavad ilusti
        // Katseta IntelliJ shortcuti. Olles intelliJ kirjuta "sout" ja siis vajuta enter

//        System.out.println(min(1, 3)); // trükib miinimumi 1 ja 3
//        System.out.println(isEven(4));
//        System.out.println(min3(2,4,1));
        // System.out.println(min3(2, 2, 3));
    }

    // TODO
    //  Tagasta string mille väärtus oleks "\"\\""
    //  Trüki muutuja sisu välja
    public static String someString() {
        return "\"\\\"\\\\\"\"";

    }

    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a absoluut arv  // miinusm'rk ara v[tta
    public static int abs(int a) {

        if (a < 0) {
            return -1 * a;
        } else {
            return a;
        }
    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        if (a % 2 == 0) {
            return true;

        } else {
            return false;
        }
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min3(int a, int b, int c) {
        if (a <= c && a <= b) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }

    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max3(int a, int b, int c) {
        return max(max(a, b), c);                 // kasutame eelmiseid meetodeid
    }

}
