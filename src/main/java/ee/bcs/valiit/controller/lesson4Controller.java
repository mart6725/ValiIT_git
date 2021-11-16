package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.bankCustomer;
import ee.bcs.valiit.tasks.employee;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class lesson4Controller {
    HashMap<String, bankCustomer> accountBalanceMap = new HashMap<>();

    @GetMapping("customers")
    public HashMap<String,Integer> getCustomers(){

        return null;
    }
    @PostMapping("customers")
    public void addAccount(@RequestBody bankCustomer customer) {

                // lisame uue employee objekti listi


    }




}
