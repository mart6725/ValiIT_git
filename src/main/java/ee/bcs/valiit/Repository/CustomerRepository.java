package ee.bcs.valiit.Repository;

import ee.bcs.valiit.tasks.Transaction;
import ee.bcs.valiit.tasks.bankCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public void createAccount(int accountNumber, String customerName, boolean locked, int balance) {
        String sql = "INSERT INTO bank_customers(account_number, customer_name, locked, balance) VALUES (:accountNumber,:customerName,:locked,:balance)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("customerName", customerName);
        paramMap.put("locked", locked);
        paramMap.put("balance", balance);

        jdbcTemplate.update(sql, paramMap);

    }

    public List<bankCustomer> allCustomers() {

        String sql = "SELECT *FROM bank_customers";
        Map<String, Object> paramMap = new HashMap<>();
        List<bankCustomer> result = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(bankCustomer.class));         // v6i new BeanPropertyRowMapper<>(Transaction.class)
        return result;

    }

    public int getBalance(int accountNumber) {

        String sql = "SELECT balance FROM bank_customers WHERE account_number= :accountNumber";           // kysime andmebaasist
        Map<String, Object> paraMap = new HashMap<>();                      // teeme paramapi
        paraMap.put("accountNumber", accountNumber);
        int balance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
        return balance;
    }


    public void locking(int acNum, boolean locked) {

        String sql = "UPDATE bank_customers SET locked=:locked WHERE account_number= :accountNum";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNum", acNum);
        paramMap.put("locked", locked);
        jdbcTemplate.update(sql, paramMap);
    }


    public bankCustomer getCustomer(int accountNumber) {

        String sql = "SELECT *FROM bank_customers WHERE account_number = :accountNumber";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("accountNumber", accountNumber);
        bankCustomer result = jdbcTemplate.queryForObject(sql, paramMap1, new BeanPropertyRowMapper<>(bankCustomer.class));
        return result;
    }


    public void changeBalance(int accountNumber, int balance) {

        String sql1 = "UPDATE bank_customers SET balance= :balance WHERE account_number= :accountNum";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNum", accountNumber);
        paramMap.put("balance", balance);
        jdbcTemplate.update(sql1, paramMap);

    }


    public void addToTransactionHistory(int accountNumber, String type) {

        String sql5 = "INSERT INTO transactions(account_number, transaction) VALUES (:accountNumber,:transaction)";
        Map<String, Object> paramMap4 = new HashMap<>();
        paramMap4.put("accountNumber", accountNumber);
        paramMap4.put("transaction", type);
        jdbcTemplate.update(sql5, paramMap4);
    }


    public List<Transaction> getCustomerTransactions(int acNum) {

        String sql = "SELECT *FROM transactions WHERE account_number=:accountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", acNum);

        List<Transaction> result = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Transaction.class));

        return result;
    }

//    private class bankCustomerRowMapper implements RowMapper<bankCustomer> {
//        @Override
//        public bankCustomer mapRow(ResultSet resultSet, int i) throws SQLException {
//            bankCustomer result = new bankCustomer();                                   // teeme uue objekti kuhu me andmed paneme
//            result.setAccountNumber(resultSet.getInt("account_number"));  // kysime iga rea tulbad
//            result.setCustomerName(resultSet.getString("customer_name"));
//            result.setLocked(resultSet.getBoolean("locked"));
//            result.setBalance(resultSet.getInt("balance"));
//            return result;                                                 // paneme objekti kogu info ja tagastame
//        }
//
//    }


}
