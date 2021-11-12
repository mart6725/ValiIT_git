package ee.bcs.valiit.tasks;

import java.util.Scanner;

public class Lesson3HardB {

    // TODO kirjuta mäng kus kasutaja peab ära arvama numbri 0-99 (nagu 3Hard)
    // NB programm ei tohi kohe alguses välja mõelda numbrit
    // vaid eesmärk on öelda kasutajale, et ta eksis nii kaua kui võimalik
    // ilma selleta, et ta läheks vastuollu ühegi varasema väitega
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int min = 0;
        int max = 99;
        int counter = 0;

        System.out.println("Guess the number !");
        System.out.println("Enter a number from 0-99");

        while (min!=max) {


            int number = scanner.nextInt();
            scanner.nextLine();
            System.out.println(min);
            System.out.println(max);

            if ((max-number)>(number-min)) {
                min=number;
                counter++;
                System.out.println("number is bigger");

            } else if ((max-number)<(number-min)) {
                max=number;
                counter++;
                System.out.println("number is smaller ");
            }else {
                counter++;
                min=max;
            }

        }
        System.out.println("correct number with " + counter + " tries !");


    }
}
