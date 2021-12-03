package ee.bcs.valiit.controller;

import ee.bcs.valiit.Service.CustomerService;
import ee.bcs.valiit.tasks.BankAccounts;
import ee.bcs.valiit.tasks.Client;
import ee.bcs.valiit.tasks.ClientsAndAccounts;
import ee.bcs.valiit.tasks.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class lesson4Controller {

//    @Autowired                                                    // sellega saame kasutada jdbcTemplate
//    private NamedParameterJdbcTemplate jdbcTemplate;


    @Autowired
    private CustomerService customerService;


//    @GetMapping("customers/clients")
//    public List<>


    //*** KLIENDI TRANSAKTSIOONIDE AJALUGU*********************************************

    @GetMapping("/api/customers/trans")
    public List<Transaction> getTransactions(@RequestParam("acNum") int acNum) {

        return customerService.getTransactions(acNum);

    }


    //***KLIENDID KOOS ACCOUNTIDEGA**************************************************
    @GetMapping("/api/customers/allInfo")
    public List<ClientsAndAccounts> getAllInfo() {

        return customerService.allInfo();
    }


    // *** K6IK KONTOD*************************************************************
    @GetMapping("/api/customers/allAccounts")
    public List<BankAccounts> getAllAccounts() {

        return customerService.allAccounts();
    }

    //**K6ik kliendid
    @GetMapping("/api/customers/allClients")
    public List<Client> getAllClients() {

        return customerService.allClients();
    }

    //yks KLient**************************************************************
    @GetMapping("/api/customers/getClient/{id}")
    public Client getClient(@PathVariable("id")int id) {

        return customerService.getClient(id);
    }

    //Kustuta klient**************************************************************
    @DeleteMapping("/api/customers/delete/{id}")
    public String deleteClient(@PathVariable("id")int id) {

        return customerService.deleteClient(id);
    }

    //*******BALANCE************************************************************

    @GetMapping("/api/customers/getBalance/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") Integer accountNumber) {

        return customerService.getBalance(accountNumber);
    }
    //LISA KlIENT**************************************************************************************

    @PostMapping("/api/customers")
    public String addClient(@RequestBody Client client) {


        return customerService.createClient(client.getFirstName(), client.getLastName(), client.getAddress());
    }


    //***** LISA KONTO******************************************************************************************

    @PostMapping("/api/customers/account")
    public String addAccount(@RequestBody BankAccounts customer) {


        return customerService.createAccount(customer.getAccountNumber(),
                customer.getClientId(), customer.isLocked(), customer.getBalance());
    }

    //**Kliendi andmete muutmine************************************************************
    @PutMapping("/api/customers/clientUpdate/{id}")
    public String update(@RequestBody Client client, @PathVariable("id") int id) {


        return customerService.updateClient(id, client.getFirstName(), client.getLastName(), client.getAddress());
    }


    //****KONTO LUKUSTAMINE********************************************************************


    @PutMapping("/api/customers/lock")
    public String lock(@RequestParam("acNum") int acNum) {

        return customerService.lock(acNum);
    }

    //****KONTO LAHTI LUKUSTAMINE**********************************************************************************


    @PutMapping("/api/customers/unlock")
    public String unlock(@RequestParam("acNum") int acNum) {


        return customerService.unlock(acNum);
    }


    //**** RAHA LISAMINE*************************************************************************


    @PutMapping("/api/customers/addBalance")
    public String addBalance(@RequestBody BankAccounts customer) {

        return customerService.deposit(customer.getAccountNumber(), customer.getBalance());
    }


    //V6TA RAHA V2LJA***********************************************************************

    @PutMapping("/api/customers/withdraw")
    public String withdraw(@RequestBody BankAccounts customer) {

        return customerService.withdraw(customer.getAccountNumber(), customer.getBalance());

    }


    //***KANNA RAHA********************************************************************************************************

    //
    @PutMapping("/api/customers/transfer")
    public String transfer(@RequestParam("acNumFrom") int acNumFrom,
                           @RequestParam("acNumTo") int acNumTo,
                           @RequestParam("amount") int amount) {

        return customerService.transfer(acNumFrom, acNumTo, amount);

    }

}




