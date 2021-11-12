package ee.bcs.valiit.tasks;

import java.util.Random;
import java.util.Scanner;

public class Lesson3Hard {

    // TODO kirjuta mäng mis leiab suvalise numbri 0-99, mille kasutaja peab ära arvama
    // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
    // ja kasutaja peab saama uuesti arvata
    // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);

        int counter=0;
        System.out.println("Guess the number !");


        while(true){

            System.out.println("Enter a number from 0-99");
            int number =  scanner.nextInt();
            scanner.nextLine();


            if(number==i){
                counter++;
                System.out.println("you guessed it with " + counter + " tries");
                break;


            }else if(number<i){
                counter++;
                System.out.println("number is bigger ");

            }else{
                counter++;
                System.out.println("number is smaller");
            }

        }


    }
}
