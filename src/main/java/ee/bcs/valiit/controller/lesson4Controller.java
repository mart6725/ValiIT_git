package ee.bcs.valiit.controller;

import ee.bcs.valiit.Service.CustomerService;
import ee.bcs.valiit.tasks.BankCustomer;
import ee.bcs.valiit.tasks.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class lesson4Controller {

//    @Autowired                                                    // sellega saame kasutada jdbcTemplate
//    private NamedParameterJdbcTemplate jdbcTemplate;


    @Autowired
    private CustomerService customerService;


    //*** KLIENDI TRANSAKTSIOONIDE AJALUGU*********************************************

    @GetMapping("customers/trans")
    public List<Transaction> getTransactions(@RequestParam("acNum") int acNum) {

        return customerService.getTransactions(acNum);

    }

    // *** TERVE KLIENDI TABEL*************************************************************
    @GetMapping("customers/all")
    public List<BankCustomer> getAll() {

        return customerService.allCustomers();
    }


    //*******BALANCE************************************************************

    @GetMapping("customers/getBalance/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") Integer accountNumber) {

        return customerService.getBalance(accountNumber);
    }


    //***** LISA KONTO******************************************************************************************

    @PostMapping("customers")
    public String addAccount(@RequestBody BankCustomer customer) {


        return customerService.createAccount(customer.getAccountNumber(),
                customer.getCustomerName(), customer.isLocked(), customer.getBalance());
    }

    //****KONTO LUKUSTAMINE********************************************************************


    @PutMapping("customers/lock")
    public String lock(@RequestParam("acNum") int acNum) {

        return customerService.lock(acNum);
    }

    //****KONTO LAHTI LUKUSTAMINE**********************************************************************************


    @PutMapping("customers/unlock")
    public String unlock(@RequestParam("acNum") int acNum) {


        return customerService.unlock(acNum);
    }


    //**** RAHA LISAMINE*************************************************************************


    @PutMapping("customers/addBalance")
    public String addBalance(@RequestBody BankCustomer customer) {

        return customerService.deposit(customer.getAccountNumber(), customer.getBalance());
    }


    //V6TA RAHA V2LJA***********************************************************************

    @PutMapping("customers/withdraw")
    public String withdraw(@RequestBody BankCustomer customer) {

        return customerService.withdraw(customer.getAccountNumber(), customer.getBalance());

    }


    //***KANNA RAHA********************************************************************************************************

    //
    @PutMapping("customers/transfer")
    public String transfer(@RequestParam("acNumFrom") int acNumFrom,
                           @RequestParam("acNumTo") int acNumTo,
                           @RequestParam("amount") int amount) {

        return customerService.transfer(acNumFrom, acNumTo, amount);

    }

}




