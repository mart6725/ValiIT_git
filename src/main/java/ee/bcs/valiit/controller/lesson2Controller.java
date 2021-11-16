package ee.bcs.valiit.controller;


import ee.bcs.valiit.tasks.Lesson2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class lesson2Controller {

    @GetMapping("lesson2/sample")
    public int[] sample() {
        return Lesson2.sampleArray();

    }

    @GetMapping("lesson2/firstn")
    public int[] firstN(@RequestParam("a") int a) {
        return Lesson2.firstN(a);

    }

    @GetMapping("lesson2/genA")
    public int[] genA(@RequestParam("a") int a) {
        return Lesson2.generateArray(a);

    }

    @GetMapping("lesson2/decreasing")
    public int[] decreasing(@RequestParam("a") int a) {
        return Lesson2.decreasingArray(a);

    }
    @GetMapping("lesson2/3")
    public int[] yl3(@RequestParam("a") int a) {
        return Lesson2.yl3(a);

    }
}
