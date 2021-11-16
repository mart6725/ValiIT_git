package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson2b;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class lesson2bController {


    @GetMapping("lesson2b/reverse")
    public int []reverse(@RequestParam("a")int[]a){
        return Lesson2b.reverseArray(a);

    }
    @GetMapping("lesson2b/even")
    public int []even(@RequestParam("a")int a){
        return Lesson2b.evenNumbers(a);

    }
    @GetMapping("lesson2b/min")
    public int min(@RequestParam("a") int[]a){
        return Lesson2b.min(a);

    }
    @GetMapping("lesson2b/sum")
    public int sum(@RequestParam("a") int[]a){
        return Lesson2b.sum(a);

    }
    @GetMapping("lesson2b/fibo")
    public int fibo(@RequestParam("a") int a){
        return Lesson2b.fibonacci(a);

    }
    @GetMapping("lesson2b/sequence")
    public int sequence(@RequestParam("x") int x,@RequestParam("y") int y){
        return Lesson2b.sequence3n(x,y);

    }
}
