package ee.bcs.valiit.Repository;

import ee.bcs.valiit.tasks.BankAccounts;
import ee.bcs.valiit.tasks.Client;
import ee.bcs.valiit.tasks.ClientsAndAccounts;
import ee.bcs.valiit.tasks.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;



    public int createClient(String firstName,String lastName,String address) {
        String sql = "INSERT INTO client (first_name,last_name,address) VALUES (:firstName,:lastName,:address)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("firstName", firstName);
        paramMap.put("lastName", lastName);
        paramMap.put("address", address);
        //jdbcTemplate.update(sql, paramMap);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap),keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }



    public void createAccount(int accountNumber, int clientId, boolean locked, int balance) {
        String sql = "INSERT INTO bank_accounts(account_number, client_id, locked, balance) VALUES (:accountNumber,:clientId,:locked,:balance)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("clientId", clientId);
        paramMap.put("locked", locked);
        paramMap.put("balance", balance);

        jdbcTemplate.update(sql, paramMap);

    }
    public List<ClientsAndAccounts> allInfo() {

        String sql = "SELECT * FROM bank_accounts b JOIN client c ON c.id = b.client_id";
        Map<String, Object> paramMap = new HashMap<>();
        List<ClientsAndAccounts> result = jdbcTemplate.query(sql, paramMap, new ClientRowMapper());
        return result;

    }
        private class ClientRowMapper implements RowMapper<ClientsAndAccounts> {
        @Override
        public ClientsAndAccounts mapRow(ResultSet resultSet, int i) throws SQLException {
            ClientsAndAccounts result = new ClientsAndAccounts();                                   // teeme uue objekti kuhu me andmed paneme
            result.setAccountNumber(resultSet.getInt("account_number"));  // kysime iga rea tulbad
            result.setBalance(resultSet.getInt("balance"));
            result.setFirstName(resultSet.getString("first_name"));
            result.setLastName(resultSet.getString("last_name"));
            result.setAddress(resultSet.getString("address"));
            return result;                                                 // paneme objekti kogu info ja tagastame
        }

    }


    public List<Client> allClients() {

        String sql = "SELECT *FROM client";
        Map<String, Object> paramMap = new HashMap<>();
        List<Client> result = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Client.class));
        return result;

    }






    public List<BankAccounts> allAccounts() {

        String sql = "SELECT *FROM bank_accounts";
        Map<String, Object> paramMap = new HashMap<>();
        List<BankAccounts> result = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(BankAccounts.class));
        return result;

    }

    public int getBalance(int accountNumber) {

        String sql = "SELECT balance FROM bank_accounts WHERE account_number= :accountNumber";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("accountNumber", accountNumber);
        int balance = jdbcTemplate.queryForObject(sql, paraMap, Integer.class);
        return balance;
    }


    public void locking(int acNum, boolean locked) {

        String sql = "UPDATE bank_accounts SET locked=:locked WHERE account_number= :accountNum";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNum", acNum);
        paramMap.put("locked", locked);
        jdbcTemplate.update(sql, paramMap);
    }


    public BankAccounts getCustomer(int accountNumber) {

        String sql = "SELECT *FROM bank_accounts WHERE account_number = :accountNumber";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("accountNumber", accountNumber);
        BankAccounts result = jdbcTemplate.queryForObject(sql, paramMap1, new BeanPropertyRowMapper<>(BankAccounts.class));
        return result;
    }


    public void changeBalance(int accountNumber, int balance) {

        String sql1 = "UPDATE bank_accounts SET balance= :balance WHERE account_number= :accountNum";
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

        String sql = "SELECT transaction,time FROM transactions WHERE account_number=:accountNumber";
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
