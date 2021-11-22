package ee.bcs.valiit.Service;

import ee.bcs.valiit.Repository.CustomerRepository;
import ee.bcs.valiit.tasks.Transaction;
import ee.bcs.valiit.tasks.bankCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    //    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private CustomerRepository customerRepository;


    public List<bankCustomer> allCustomers() {

        return customerRepository.allCustomers();


    }


//** TEEME KASUTAJA**************************************************************************************

    public String createAccount(int accountNumber, String customerName, boolean locked, int balance) {

        customerRepository.createAccount(accountNumber, customerName, locked, balance);

        return "Konto loodud  name: " + customerName + " Account Num: " + accountNumber;


    }

    //*********GETBALANCE*************************************************************************
    public String getBalance(int accountNumber) {


        return "Arve number: " + accountNumber + ". Saldo: " + customerRepository.getBalance(accountNumber);
    }


    //****LUKSUTA******************************************************
    public String lock(int acNum) {

        customerRepository.lock(acNum);

        return "Konto numbriga " + acNum + " on  lukus";

    }

    //***UNLOCK******************************************************************************************************

    public String unlock(int acNum) {

        customerRepository.unlock(acNum);


        return "Konto numbriga " + acNum + " on  lukust lahti";

    }

    //***** DEPOSIT*************************************************************************************************

    public String deposit(int accountNumber, int amount) {


        bankCustomer result = customerRepository.getBalanceAndLocked(accountNumber);

        if (amount > 0 && !result.isLocked()) {

            customerRepository.addBalance(accountNumber, amount);

//**** LISAME TRANSACTIONITE TABELISSE
            customerRepository.addToTransactionHistoryDeposit(accountNumber, amount);


        } else {
            return "account is locked or invalid amount";
        }
        return "Added " + amount + " EUR  ";

    }


    //*****WITHDRAW**********************************************************************************

    public String withdraw(int accountNumber, int amount) {

        bankCustomer result = customerRepository.getBalanceAndLocked(accountNumber);


        if (amount > 0 && !result.isLocked()) {

            customerRepository.withdraw(accountNumber, amount);

//**** LISAME TRANSACTIONITE TABELISSE*

            customerRepository.addToTransactionHistoryWithdraw(accountNumber, amount);

        } else {
            return "account is locked or invalid amount";
        }
        return "Withdrawn " + amount + " EUR  ";

    }
    //******* TRANSFER************************************************************************************

    public String transfer(int acNumFrom, int acNumTo, int amount) {

        bankCustomer customerFrom = customerRepository.getBalanceAndLocked(acNumFrom);
        bankCustomer customerTo = customerRepository.getBalanceAndLocked(acNumTo);


        if (amount > 0 && !customerFrom.isLocked() && !customerTo.isLocked() && amount <= customerFrom.getBalance()) {

            customerRepository.withdraw(acNumFrom, amount);
            customerRepository.addBalance(acNumTo, amount);

            // ** add to trans history
            customerRepository.addToTransactionHistoryTransferFrom(acNumFrom, acNumTo, amount);

            customerRepository.addToTransactionHistoryTransferTo(acNumFrom, acNumTo, amount);

            return "Successfully transfered  " + amount + " to account " + acNumTo;

        } else {
            return "account is locked or invalid amount ";
        }
    }
    //***GET CUSTOMER TRANSACITONS******************************************************************

    public List<Transaction> getTranactions(int acNum) {


        return customerRepository.getCustomerTransactions(acNum);
    }

}


