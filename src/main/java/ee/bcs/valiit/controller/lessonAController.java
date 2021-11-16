package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.LessonA;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class lessonAController {

    // localhost:8080/lessonA/e1?a=15

    @GetMapping("lessonA/e1")
    public int e1(@RequestParam("a") int a) {
        return LessonA.e1(a);


    }

    @GetMapping("lessonA/e2")
    public int e2(@RequestParam("a") int a, @RequestParam("b") int b) {
        return LessonA.e2(a, b);


    }

    @GetMapping("lessonA/e3")
    public int e3(@RequestParam("a") int a) {
        return LessonA.e3(a);


    }

    //http://localhost:8080/lessonA/e4?a1=10&b1=4&a2=2&b2=3&a3=5&b3=9
    @GetMapping("lessonA/e4")
    public int e4(@RequestParam("a1") int a1, @RequestParam("b1") int b1, @RequestParam("a2") int a2, @RequestParam("b2") int b2, @RequestParam("a3") int a3, @RequestParam("b3") int b3) {
        return LessonA.e4(a1, b1, a2, b2, a3, b3);


    }

    @GetMapping("lessonA/e5")
    public int e5() {
        return LessonA.e5();


    }

    @GetMapping("lessonA/e6")
    public boolean e6(@RequestParam("aasta") int aasta) {
        return LessonA.e6(aasta);


    }

    @GetMapping("lessonA/e7")
    public boolean e7(@RequestParam("!") boolean trueFalse) {
        return LessonA.e7(trueFalse);


    }

    @GetMapping("lessonA/e8")
    public boolean e8(@RequestParam("x1") boolean x1, @RequestParam("x2") boolean x2) {
        return LessonA.e8(x1, x2);


    }

    @GetMapping("lessonA/e9")
    public boolean e9(@RequestParam("x1") boolean x1, @RequestParam("x2") boolean x2, @RequestParam("x3") boolean x3, @RequestParam("x4") boolean x4) {
        return LessonA.e9(x1, x2, x1, x2);


    }

    //http://localhost:8080/lessonA/e10?a=1,2,3,4,5,6
    @GetMapping("lessonA/e10")
    public int[] e10(@RequestParam("a") int[] array) {
        LessonA.e10(array);
        return array;

    }

    @GetMapping("lessonA/e11")
    public int[] e11(@RequestParam("array") int[] array) {
        LessonA.e11(array);
        return array;

    }

    @GetMapping("lessonA/e12")
    public int[] e12(@RequestParam("a") int[] a) {
        LessonA.e12(a);
        return a;

    }

    @GetMapping("lessonA/e13")
    public int[] e13(@RequestParam("a") int[] a) {
        LessonA.e13(a);
        return a;


    }

    @GetMapping("lessonA/e14")
    public int[] e14(@RequestParam("a") int[] a) {
        LessonA.e14(a);
        return a;


    }


    @GetMapping("lessonA/e15")
    public int[] e15(@RequestParam("a") int[] a) {
        LessonA.e15(a);
        return a;

    }


}
