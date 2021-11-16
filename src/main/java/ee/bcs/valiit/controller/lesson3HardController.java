package ee.bcs.valiit.controller;

import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class lesson3HardController {

    Random random = new Random();
    int i = random.nextInt(100);
    int counter = 0;

    @GetMapping("lesson3hard/guessNumber")
    public String guessNumber(@RequestParam("number") int number){
        counter++;
        if(number==i){
            int oldCounter=counter;
            counter = 0;
            i = random.nextInt(100);

            return "you guessed it with " + oldCounter + " tries";

        }else if(number<i){

            return "number is bigger ";

        }else{

            return "number is smaller";

        }


    }




}
