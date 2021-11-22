package ee.bcs.valiit.Service;

import ee.bcs.valiit.Repository.CustomerRepository;
import ee.bcs.valiit.tasks.Transaction;
import ee.bcs.valiit.tasks.bankCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


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


    //****LOCK****************************************************************************************************
    public String lock(int acNum) {

        customerRepository.locking(acNum,true);

        return "Konto numbriga " + acNum + " on  lukus";

    }


    //***UNLOCK******************************************************************************************************

    public String unlock(int acNum) {

        customerRepository.locking(acNum,false);


        return "Konto numbriga " + acNum + " on  lukust lahti";

    }


    //***** DEPOSIT*************************************************************************************************

    public String deposit(int accountNumber, int amount) {


        bankCustomer result = customerRepository.getCustomer(accountNumber);

        if (amount > 0 && !result.isLocked()) {

            int newBalance = result.getBalance() + amount;
            customerRepository.changeBalance(accountNumber, newBalance);

//**** LISAME TRANSACTIONITE TABELISSE
            String type = " Successfully deposited " + amount + " EUR";
            customerRepository.addToTransactionHistory(accountNumber, type);


        } else {
            return "account is locked or invalid amount";
        }
        return "Added " + amount + " EUR  ";

    }



    //*****WITHDRAW**********************************************************************************

    public String withdraw(int accountNumber, int amount) {

        bankCustomer result = customerRepository.getCustomer(accountNumber);


        if (amount > 0 && !result.isLocked()) {

            int newBalance = result.getBalance() - amount;
            customerRepository.changeBalance(accountNumber, newBalance);

    //**** LISAME TRANSACTIONITE TABELISSE

            String type = " Successfully withdrawn " + amount + " EUR";
            customerRepository.addToTransactionHistory(accountNumber, type);

        } else {
            return "account is locked or invalid amount";
        }
        return "Withdrawn " + amount + " EUR  ";

    }




    //******* TRANSFER************************************************************************************

    public String transfer(int acNumFrom, int acNumTo, int amount) {

            bankCustomer customerFrom = customerRepository.getCustomer(acNumFrom);
            bankCustomer customerTo = customerRepository.getCustomer(acNumTo);


        if (amount > 0 && !customerFrom.isLocked() && !customerTo.isLocked() && amount <= customerFrom.getBalance()) {

            int newBalanceFrom = customerFrom.getBalance() - amount;
            int newBalanceTo = customerTo.getBalance() + amount;

            customerRepository.changeBalance(acNumFrom, newBalanceFrom);
            customerRepository.changeBalance(acNumTo, newBalanceTo);



            // ** add to trans history
            String typeFrom = " Transfered " + amount + " EUR to account " + acNumTo ;
            String  typeTo = " Received " + amount + " EUR from account " + acNumFrom;
            customerRepository.addToTransactionHistory(acNumFrom,typeFrom);
            customerRepository.addToTransactionHistory(acNumTo,typeTo);

            return "Successfully transfered  " + amount + " to account " + acNumTo;

        } else {
            return "account is locked or invalid amount ";
        }
    }



    //***GET CUSTOMER TRANSACITONS******************************************************************

    public List<Transaction> getTransactions(int acNum) {

        return customerRepository.getCustomerTransactions(acNum);
    }

}


