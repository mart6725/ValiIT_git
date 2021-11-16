package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson2c;
import ee.bcs.valiit.tasks.Lesson3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class lesson3Controller {

    @GetMapping("lesson3/factorial")
    public int factorial(@RequestParam("x") int x){
        return Lesson3.factorial(x);

    }

}
