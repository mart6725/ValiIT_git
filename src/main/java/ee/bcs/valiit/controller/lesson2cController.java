package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson2b;
import ee.bcs.valiit.tasks.Lesson2c;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class lesson2cController {

    @GetMapping("lesson2c/sequence")
    public int sequence(@RequestParam("x") int x,@RequestParam("y") int y){
        return Lesson2c.sequence3n(x,y);

    }
    @GetMapping("lesson2c/seqLength")
    public int sequenceLength(@RequestParam("x") int x){
        return Lesson2c.getSeqLength(x);

    }
    @GetMapping("lesson2c/nextElement")
    public int nextElement(@RequestParam("x") int x){
        return Lesson2c.nextElement(x);

    }
}
