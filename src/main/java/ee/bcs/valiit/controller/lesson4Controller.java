package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.bankCustomer;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class lesson4Controller {
    HashMap<Integer, bankCustomer> accountBalanceMap = new HashMap<>();

    @GetMapping("customers")
    public HashMap<Integer, bankCustomer> getCustomers() {

        return accountBalanceMap;
    }


    @GetMapping("customers/getBalance")
    public String getBalance(@RequestParam("acNum") int acNum) {

        bankCustomer customer = accountBalanceMap.get(acNum);
        int balance = customer.getBalance();
        return "account " + acNum + " balance is " + balance;
    }


    @PostMapping("customers")
    public String addAccount(@RequestParam("acNum") int acNum,
                             @RequestParam("acName") String name) {

        bankCustomer newCustomer = new bankCustomer(acNum, name, false, 0);
        accountBalanceMap.put(acNum, newCustomer);

        return "Konto nimega " + name + " ja  numbriga " + acNum + " on  loodud";
    }

    @PutMapping("customers/lock")
    public String lock(@RequestParam("acNum") int acNum,
                       @RequestParam("lock") boolean lock) {

        bankCustomer customer = accountBalanceMap.get(acNum);
        customer.setLocked(lock);

        return "Konto numbriga " + acNum + " on  lukus";
    }

    @PutMapping("customers/unlock")
    public String unlock(@RequestParam("acNum") int acNum,
                         @RequestParam("lock") boolean lock) {

        bankCustomer customer = accountBalanceMap.get(acNum);
        customer.setLocked(lock);

        return "Konto numbriga " + acNum + " on  lukust lahti";
    }


    @PutMapping("customers/addBalance")
    public String addBalance(@RequestParam("acNum") int acNum,
                             @RequestParam("amount") int amount) {
        bankCustomer customer = accountBalanceMap.get(acNum);
        if (amount > 0 && !customer.isLocked()) {

            int initialBalance = customer.getBalance();
            customer.setBalance(initialBalance + amount);

        } else {
            return "account is locked or invalid amount";
        }
        return amount + " added to account ";
    }

    @PutMapping("customers/withdraw")
    public String withdraw(@RequestParam("acNum") int acNum,
                           @RequestParam("amount") int amount) {

        bankCustomer customer = accountBalanceMap.get(acNum);

        if (amount > 0 && amount <= customer.getBalance() && !customer.isLocked()) {

            int initialBalance = customer.getBalance();
            customer.setBalance(initialBalance - amount);

            return "Successfully withdrawn " + amount + " Balance is " + customer.getBalance();
        } else {
            return "account is locked or invalid amount ";
        }
    }

    //
    @PutMapping("customers/transfer")
    public String transfer(@RequestParam("acNumFrom") int acNumFrom,
                           @RequestParam("acNumTo") int acNumTo,
                           @RequestParam("amount") int amount) {
        bankCustomer customerFrom = accountBalanceMap.get(acNumFrom);
        bankCustomer customerTo = accountBalanceMap.get(acNumTo);


        if (amount > 0 && amount <= customerFrom.getBalance() && !customerFrom.isLocked() && !customerTo.isLocked()) {


            int currentBalanceFrom = customerFrom.getBalance();
            customerFrom.setBalance(currentBalanceFrom - amount);

            int currentBalanceTo = customerTo.getBalance();
            customerTo.setBalance(currentBalanceTo + amount);

            return "Successfully transfered  " + amount + " to account " + acNumTo +
                    " . Initial account balance is " + customerFrom.getBalance()
                    + " . Receiving account balance is " + customerTo.getBalance();

        } else {
            return "account is locked or invalid amount ";
        }
    }


}
