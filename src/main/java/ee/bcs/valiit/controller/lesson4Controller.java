package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.bankCustomer;
import ee.bcs.valiit.tasks.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class lesson4Controller {
    HashMap<Integer, bankCustomer> accountBalanceMap = new HashMap<>();
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //*******BALANCE************************************************************

    @GetMapping("customers/getBalance/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") Integer accountNumber) {
        String sql = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";           // kysime andmebaasist

        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("accountNumber", accountNumber);
        int balance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
        return "Arve number: " + accountNumber + ". Saldo: " + balance;        // viimane on et mis tyypi tahame tagastada
    }


    //***** LISA KONTO******************************************************************************************

    @PostMapping("customers")
    public String addAccount(@RequestBody bankCustomer customer) {

        String sql = "INSERT INTO bank_customers(account_number, customer_name, is_locked, balance) VALUES (:accountNumber,:customerName,:isLocked,:balance)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", customer.getAccountNumber());
        paramMap.put("customerName", customer.getCustomerName());
        paramMap.put("isLocked", customer.isLocked());
        paramMap.put("balance", customer.getBalance());

        jdbcTemplate.update(sql, paramMap);

        return "Konto loodud  name: " + customer.getCustomerName() + " Account Num: " + customer.getAccountNumber();
    }

    //****KONTO LUKUSTAMINE********************************************************************


    @PutMapping("customers/lock")
    public String lock(@RequestParam("acNum") int acNum) {

        String sql = "UPDATE bank_customers SET is_locked=true WHERE account_number= :accountNum";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNum", acNum);

        jdbcTemplate.update(sql, paramMap);


        return "Konto numbriga " + acNum + " on  lukus";
    }

    //****KONTO LAHTI LUKUSTAMINE**********************************************************************************


    @PutMapping("customers/unlock")
    public String unlock(@RequestParam("acNum") int acNum) {

        String sql = "UPDATE bank_customers SET is_locked=false WHERE account_number= :accountNum";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNum", acNum);

        jdbcTemplate.update(sql, paramMap);


        return "Konto numbriga " + acNum + " on  lukust lahti";
    }


    //**** RAHA LISAMINE*************************************************************************


    @PutMapping("customers/addBalance")
    public String addBalance(@RequestBody bankCustomer customer) {


        String sql3 = "SELECT is_locked FROM bank_customers WHERE account_number= :accountNumber";
        Map<String, Object> paraMap2 = new HashMap<>();
        paraMap2.put("accountNumber", customer.getAccountNumber());
        boolean isLocked = jdbcTemplate.queryForObject(sql3, paraMap2, Boolean.class);


        if (customer.getBalance() > 0 && !isLocked ) {

            String sql2 = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";           // kysime andmebaasist
            Map<String, Object> paraMap1 = new HashMap<>();                                 //kysime palju kontol raha
            paraMap1.put("accountNumber", customer.getAccountNumber());
            int balanceInitial = jdbcTemplate.queryForObject(sql2, paraMap1, Integer.class);


            String sql = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";  //lisame kontole raha juurde
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("accountNum", customer.getAccountNumber());
            paramMap.put("balance", customer.getBalance() + balanceInitial);         // liidan esialgse balance juurde
            jdbcTemplate.update(sql, paramMap);


        } else {
            return "account is locked or invalid amount";
        }
        return "Added " + customer.getBalance() + " EUR ";
    }


    //V6TA RAHA V2LJA***********************************************************************

    @PutMapping("customers/withdraw")
    public String withdraw(@RequestBody bankCustomer customer) {

        String sql3 = "SELECT is_locked FROM bank_customers WHERE account_number= :accountNumber";
        Map<String, Object> paraMap2 = new HashMap<>();
        paraMap2.put("accountNumber", customer.getAccountNumber());
        boolean isLocked = jdbcTemplate.queryForObject(sql3, paraMap2, Boolean.class);

        String sql2 = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";           // kysime andmebaasist
        Map<String, Object> paraMap1 = new HashMap<>();                                 //kysime palju kontol raha
        paraMap1.put("accountNumber", customer.getAccountNumber());
        int balanceInitial = jdbcTemplate.queryForObject(sql2, paraMap1, Integer.class);


        if (customer.getBalance() > 0 && !isLocked && customer.getBalance() <= balanceInitial) {




            String sql = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";  //lisame kontole raha juurde
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("accountNum", customer.getAccountNumber());
            paramMap.put("balance", balanceInitial - customer.getBalance());         // liidan esialgse balance juurde
            jdbcTemplate.update(sql, paramMap);


        } else {
            return "account is locked or invalid amount";
        }
        return customer.getBalance() + " EUR Withdrawn ";

    }

    //
    @PutMapping("customers/transfer")
    public String transfer(@RequestParam("acNumFrom") int acNumFrom,
                           @RequestParam("acNumTo") int acNumTo,
                           @RequestParam("amount") int amount) {

        String sql3LockedFrom = "SELECT is_locked FROM bank_customers WHERE account_number= :accountNumber";
        Map<String, Object> paraMap2LockedFrom = new HashMap<>();
        paraMap2LockedFrom.put("accountNumber", acNumFrom);
        boolean isLockedFrom = jdbcTemplate.queryForObject(sql3LockedFrom, paraMap2LockedFrom, Boolean.class);

        String sql3LockedTo = "SELECT is_locked FROM bank_customers WHERE account_number= :accountNumber";
        Map<String, Object> paraMap2LockedTo = new HashMap<>();
        paraMap2LockedTo.put("accountNumber", acNumTo);
        boolean isLockedTo = jdbcTemplate.queryForObject(sql3LockedTo, paraMap2LockedTo, Boolean.class);

        String sql2BalanceFrom = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";
        Map<String, Object> paraMap1BalanceFrom = new HashMap<>();
        paraMap1BalanceFrom.put("accountNumber", acNumFrom);
        int balanceInitialFrom = jdbcTemplate.queryForObject(sql2BalanceFrom, paraMap1BalanceFrom, Integer.class);

        String sql2BalanceTo = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";
        Map<String, Object> paraMap1BalanceTo = new HashMap<>();
        paraMap1BalanceTo.put("accountNumber", acNumTo);
        int balanceInitialTo = jdbcTemplate.queryForObject(sql2BalanceTo, paraMap1BalanceTo, Integer.class);



        if (amount > 0 && !isLockedFrom && !isLockedTo && amount<= balanceInitialFrom) {

            String sql = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("accountNum", acNumFrom);
            paramMap.put("balance", balanceInitialFrom - amount);
            jdbcTemplate.update(sql, paramMap);

            String sql1 = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("accountNum", acNumTo);
            paramMap1.put("balance", balanceInitialTo + amount);
            jdbcTemplate.update(sql1, paramMap1);



            return "Successfully transfered  " + amount + " to account " + acNumTo ;

        } else {
            return "account is locked or invalid amount ";
        }
    }


}
