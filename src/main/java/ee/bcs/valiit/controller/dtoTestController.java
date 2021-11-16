package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.dtoTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dtoTestController {

    @GetMapping("testDto/test")
    public dtoTest testDto() {

        dtoTest dtoTest = new dtoTest();
        dtoTest.setName("Test");
        dtoTest.setDate("2021");
        dtoTest.setDuration(24);

        return dtoTest;


    }
    @PostMapping("dtoTest")
    public dtoTest saveTest (@RequestBody dtoTest test){

        return test;
    }


}
