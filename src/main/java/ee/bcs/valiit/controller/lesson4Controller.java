package ee.bcs.valiit.controller;

import ee.bcs.valiit.solution.AccountDtoSolution;
import ee.bcs.valiit.tasks.Transaction;
import ee.bcs.valiit.tasks.bankCustomer;
import ee.bcs.valiit.tasks.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class lesson4Controller {
    HashMap<Integer, bankCustomer> accountBalanceMap = new HashMap<>();
    @Autowired                  // sellega saame kasutada jdbcTemplate
    private NamedParameterJdbcTemplate jdbcTemplate;

   //*** KLIENDI TRANSAKTSIOONIDE AJALUGU*********************************************

   @GetMapping("customers/trans")
   public List<Transaction>getTransactions(@RequestParam("acNum") int acNum){

       String sql="SELECT *FROM transactions WHERE account_number=:accountNumber";
       Map<String,Object>paramMap=new HashMap<>();
       paramMap.put("accountNumber",acNum);

       List<Transaction> result = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Transaction.class));

       return result;

   }











    @GetMapping("customers/all")
    public List<bankCustomer> getAll() {

        String sql = "SELECT *FROM bank_customers";

        Map<String, Object> paramMap = new HashMap<>();


        List<bankCustomer> result = jdbcTemplate.query(sql, paramMap, new bankCustomerRowMapper());         // v6i new BeanPropertyRowMapper<>(Transaction.class)

        return result;
    }

    //**** Selle manuaalse meeetodiga kutsume iga rida tabelist eraldi ja paneme kokku result alla
    private class bankCustomerRowMapper implements RowMapper<bankCustomer> {
        @Override
        public bankCustomer mapRow(ResultSet resultSet, int i) throws SQLException {
            bankCustomer result = new bankCustomer();                                   // teeme uue objekti kuhu me andmed paneme
            result.setAccountNumber(resultSet.getInt("account_number"));  // kysime iga rea tulbad
            result.setCustomerName(resultSet.getString("customer_name"));
            result.setLocked(resultSet.getBoolean("locked"));
            result.setBalance(resultSet.getInt("balance"));
            return result;                                                 // paneme objekti kogu info ja tagastame
        }

    }


    //*******BALANCE************************************************************

    @GetMapping("customers/getBalance/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") Integer accountNumber) {
        String sql = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";           // kysime andmebaasist

        Map<String, Object> paraMap = new HashMap<>();                      // teeme paramapi
        paraMap.put("accountNumber", accountNumber);
        int balance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);    // viimane on et mis tyypi tahame tagastada
        return "Arve number: " + accountNumber + ". Saldo: " + balance;
    }


    //***** LISA KONTO******************************************************************************************

    @PostMapping("customers")
    public String addAccount(@RequestBody bankCustomer customer) {

        String sql = "INSERT INTO bank_customers(account_number, customer_name, locked, balance) VALUES (:accountNumber,:customerName,:locked,:balance)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", customer.getAccountNumber());
        paramMap.put("customerName", customer.getCustomerName());
        paramMap.put("locked", customer.isLocked());
        paramMap.put("balance", customer.getBalance());

        jdbcTemplate.update(sql, paramMap);

        return "Konto loodud  name: " + customer.getCustomerName() + " Account Num: " + customer.getAccountNumber();
    }

    //****KONTO LUKUSTAMINE********************************************************************


    @PutMapping("customers/lock")
    public String lock(@RequestParam("acNum") int acNum) {

        String sql = "UPDATE bank_customers SET locked=true WHERE account_number= :accountNum";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNum", acNum);

        jdbcTemplate.update(sql, paramMap);


        return "Konto numbriga " + acNum + " on  lukus";
    }

    //****KONTO LAHTI LUKUSTAMINE**********************************************************************************


    @PutMapping("customers/unlock")
    public String unlock(@RequestParam("acNum") int acNum) {

        String sql = "UPDATE bank_customers SET locked=false WHERE account_number= :accountNum";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNum", acNum);

        jdbcTemplate.update(sql, paramMap);


        return "Konto numbriga " + acNum + " on  lukust lahti";
    }


    //**** RAHA LISAMINE*************************************************************************


    @PutMapping("customers/addBalance")
    public String addBalance(@RequestBody bankCustomer customer) {


//        String sql3 = "SELECT locked FROM bank_customers WHERE account_number= :accountNumber";
//        Map<String, Object> paraMap2 = new HashMap<>();
//        paraMap2.put("accountNumber", customer.getAccountNumber());
//        boolean isLocked = jdbcTemplate.queryForObject(sql3, paraMap2, Boolean.class);
        String sql = "SELECT locked, balance FROM bank_customers WHERE account_number = :accountNumber";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("accountNumber", customer.getAccountNumber());
        bankCustomer result = jdbcTemplate.queryForObject(sql, paramMap1, new bankCustomerRowMapperAdd());         // v6i BeanPropertyRowMapper<>(bankCustomer.class)


        if (customer.getBalance() > 0 && !result.isLocked()) {
//            String sql2 = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";           // kysime andmebaasist
//            Map<String, Object> paraMap1 = new HashMap<>();                                 //kysime palju kontol raha
//            paraMap1.put("accountNumber", customer.getAccountNumber());
//            int balanceInitial = jdbcTemplate.queryForObject(sql2, paraMap1, Integer.class);

            String sql1 = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("accountNum", customer.getAccountNumber());
            paramMap.put("balance", customer.getBalance() + result.getBalance());
            jdbcTemplate.update(sql1, paramMap);


//**** LISAME TRANSACTIONITE TABELISSE************************************************************


            String sql2 = "INSERT INTO transactions(account_number, transaction) VALUES (:accountNumber,:transaction)";
            Map<String, Object> paramMap4 = new HashMap<>();
            paramMap4.put("accountNumber", customer.getAccountNumber());
            paramMap4.put("transaction", "Deposited " + customer.getBalance() + " EUR");
            jdbcTemplate.update(sql2, paramMap4);




        } else {
            return "account is locked or invalid amount";
        }
        return "Added " + customer.getBalance() + " EUR  ";
    }

    private class bankCustomerRowMapperAdd implements RowMapper<bankCustomer> {    // teeme selgeks andmebaasile et milline info tagastada
        @Override
        public bankCustomer mapRow(ResultSet resultSet, int i) throws SQLException {
            bankCustomer resultAdd = new bankCustomer();                                   // teeme uue objekti kuhu me andmed paneme
            resultAdd.setLocked(resultSet.getBoolean("locked"));
            resultAdd.setBalance(resultSet.getInt("balance"));
            return resultAdd;                                                 // paneme objekti kogu info ja tagastame
        }

    }


    //V6TA RAHA V2LJA***********************************************************************

    @PutMapping("customers/withdraw")
    public String withdraw(@RequestBody bankCustomer customer) {

//        String sql3 = "SELECT locked FROM bank_customers WHERE account_number= :accountNumber";
//        Map<String, Object> paraMap2 = new HashMap<>();
//        paraMap2.put("accountNumber", customer.getAccountNumber());
//        boolean isLocked = jdbcTemplate.queryForObject(sql3, paraMap2, Boolean.class);
//
//        String sql2 = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";           // kysime andmebaasist
//        Map<String, Object> paraMap1 = new HashMap<>();                                 //kysime palju kontol raha
//        paraMap1.put("accountNumber", customer.getAccountNumber());
//        int balanceInitial = jdbcTemplate.queryForObject(sql2, paraMap1, Integer.class);

        String sql = "SELECT locked,balance FROM bank_customers WHERE account_number = :accountNumber";
        Map<String,Object>paraMap1=new HashMap<>();
        paraMap1.put("accountNumber",customer.getAccountNumber());
        bankCustomer result= jdbcTemplate.queryForObject(sql,paraMap1,new bankCustomerRowMapperAdd());


        if (customer.getBalance() > 0 && !result.isLocked() && customer.getBalance() <= result.getBalance()) {


            String sql1 = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";  //lisame kontole raha juurde
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("accountNum", customer.getAccountNumber());
            paramMap.put("balance", result.getBalance()- customer.getBalance());         // liidan esialgse balance juurde
            jdbcTemplate.update(sql1, paramMap);

            //**Lisame transaction tabelisse

            String sql2 = "INSERT INTO transactions(account_number, transaction) VALUES (:accountNumber,:transaction)";
            Map<String, Object> paramMap4 = new HashMap<>();
            paramMap4.put("accountNumber", customer.getAccountNumber());
            paramMap4.put("transaction", "Withdrawn " + customer.getBalance() + " EUR");
            jdbcTemplate.update(sql2, paramMap4);


        } else {
            return "account is locked or invalid amount";
        }
        return customer.getBalance() + " EUR Withdrawn ";

    }


    //***KANNA RAHA********************************************************************************************************

    //
    @PutMapping("customers/transfer")
    public String transfer(@RequestParam("acNumFrom") int acNumFrom,
                           @RequestParam("acNumTo") int acNumTo,
                           @RequestParam("amount") int amount) {

        String sqlFrom = "SELECT balance,locked FROM bank_customers WHERE account_number = :accountNumber";
        Map<String,Object>paraMapFrom=new HashMap<>();
        paraMapFrom.put("accountNumber",acNumFrom);
        bankCustomer customerFrom= jdbcTemplate.queryForObject(sqlFrom,paraMapFrom,new bankCustomerRowMapperAdd());

        String sqlTo = "SELECT balance,locked FROM bank_customers WHERE account_number = :accountNumber";
        Map<String,Object>paraMapTo=new HashMap<>();
        paraMapTo.put("accountNumber",acNumTo);
        bankCustomer customerTo = jdbcTemplate.queryForObject(sqlTo,paraMapTo,new bankCustomerRowMapperAdd());




//        String sql3LockedFrom = "SELECT locked FROM bank_customers WHERE account_number= :accountNumber";
//        Map<String, Object> paraMap2LockedFrom = new HashMap<>();
//        paraMap2LockedFrom.put("accountNumber", acNumFrom);
//        boolean isLockedFrom = jdbcTemplate.queryForObject(sql3LockedFrom, paraMap2LockedFrom, Boolean.class);
//
//        String sql3LockedTo = "SELECT locked FROM bank_customers WHERE account_number= :accountNumber";
//        Map<String, Object> paraMap2LockedTo = new HashMap<>();
//        paraMap2LockedTo.put("accountNumber", acNumTo);
//        boolean isLockedTo = jdbcTemplate.queryForObject(sql3LockedTo, paraMap2LockedTo, Boolean.class);
//
//        String sql2BalanceFrom = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";
//        Map<String, Object> paraMap1BalanceFrom = new HashMap<>();
//        paraMap1BalanceFrom.put("accountNumber", acNumFrom);
//        int balanceInitialFrom = jdbcTemplate.queryForObject(sql2BalanceFrom, paraMap1BalanceFrom, Integer.class);
//
//        String sql2BalanceTo = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";
//        Map<String, Object> paraMap1BalanceTo = new HashMap<>();
//        paraMap1BalanceTo.put("accountNumber", acNumTo);
//        int balanceInitialTo = jdbcTemplate.queryForObject(sql2BalanceTo, paraMap1BalanceTo, Integer.class);


        if (amount > 0 && !customerFrom.isLocked() && !customerTo.isLocked() && amount <= customerFrom.getBalance()) {

            String sql = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("accountNum", acNumFrom);
            paramMap.put("balance", customerFrom.getBalance() - amount);
            jdbcTemplate.update(sql, paramMap);
//
            String sql1 = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("accountNum", acNumTo);
            paramMap1.put("balance", customerTo.getBalance() + amount);
            jdbcTemplate.update(sql1, paramMap1);

//** transaction tabelisse **************************************
            String sql5 = "INSERT INTO transactions(account_number, transaction) VALUES (:accountNumber,:transaction)";
            Map<String, Object> paramMap4 = new HashMap<>();
            paramMap4.put("accountNumber", acNumFrom);
            paramMap4.put("transaction", "Transfered" + amount + " EUR to account " + acNumTo);
            jdbcTemplate.update(sql5, paramMap4);

            String sql6 = "INSERT INTO transactions(account_number, transaction) VALUES (:accountNumber,:transaction)";
            Map<String, Object> paramMap5 = new HashMap<>();
            paramMap5.put("accountNumber", acNumTo);
            paramMap5.put("transaction", "Received " + amount + " EUR from " + acNumFrom);
            jdbcTemplate.update(sql6, paramMap5);


            return "Successfully transfered  " + amount + " to account " + acNumTo;

        } else {
            return "account is locked or invalid amount ";
        }
    }


}
