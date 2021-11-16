package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController                     // selle abil saame mujalt ligi sellele classile

public class testController {

    //localgost:8080/emplyee/5
    @GetMapping("hello/{name}")  // see on URLi path, l6pp   /hello..... case sensitive ja ; ei l2he l6ppu

    public String hello(@PathVariable("name")String employeeName){

        return "Hello world " + employeeName;
    }


}

