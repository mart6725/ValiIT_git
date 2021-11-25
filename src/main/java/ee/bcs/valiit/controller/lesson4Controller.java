package ee.bcs.valiit.controller;

import ee.bcs.valiit.Service.CustomerService;
import ee.bcs.valiit.tasks.BankAccounts;
import ee.bcs.valiit.tasks.Client;
import ee.bcs.valiit.tasks.ClientsAndAccounts;
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


//    @GetMapping("customers/clients")
//    public List<>



    //*** KLIENDI TRANSAKTSIOONIDE AJALUGU*********************************************

    @GetMapping("customers/trans")
    public List<Transaction> getTransactions(@RequestParam("acNum") int acNum) {

        return customerService.getTransactions(acNum);

    }


    //***KLIENDID KOOS ACCOUNTIDEGA**************************************************
    @GetMapping("customers/allInfo")
    public List<ClientsAndAccounts> getAllInfo() {

        return customerService.allInfo();
    }


    // *** K6IK KONTOD*************************************************************
    @GetMapping("customers/allAccounts")
    public List<BankAccounts> getAllAccounts() {

        return customerService.allAccounts();
    }

    //**K6ik kliendid
    @GetMapping("customers/allClients")
    public List<Client> getAllClients() {

        return customerService.allClients();
    }



    //*******BALANCE************************************************************

    @GetMapping("customers/getBalance/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") Integer accountNumber) {

        return customerService.getBalance(accountNumber);
    }
    //LISA KlIENT**************************************************************************************

    @PostMapping("customers")
    public String addClient(@RequestBody Client client) {


        return customerService.createClient(client.getFirstName(),client.getLastName(),client.getAddress());
    }



    //***** LISA KONTO******************************************************************************************

    @PostMapping("customers/account")
    public String addAccount(@RequestBody BankAccounts customer) {


        return customerService.createAccount(customer.getAccountNumber(),
                customer.getClientId(), customer.isLocked(), customer.getBalance());
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
    public String addBalance(@RequestBody BankAccounts customer) {

        return customerService.deposit(customer.getAccountNumber(), customer.getBalance());
    }


    //V6TA RAHA V2LJA***********************************************************************

    @PutMapping("customers/withdraw")
    public String withdraw(@RequestBody BankAccounts customer) {

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




