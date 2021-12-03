package ee.bcs.valiit.Service;

import ee.bcs.valiit.Exception.ApplicationException;
import ee.bcs.valiit.Repository.CustomerRepository;
import ee.bcs.valiit.tasks.BankAccounts;
import ee.bcs.valiit.tasks.Client;
import ee.bcs.valiit.tasks.ClientsAndAccounts;
import ee.bcs.valiit.tasks.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;


    //**KOGU INFO*********************************************************************************

    public List<ClientsAndAccounts> allInfo() {

        return customerRepository.allInfo();
    }

    //*K6IK KONTOD**************************************************************************
    public List<BankAccounts> allAccounts() {

        return customerRepository.allAccounts();
    }
    //**K6IK KLIENDID**********************************************************************

    public List<Client> allClients() {

        return customerRepository.allClients();
    }
    //yks KLient**************************************************************

    public Client getClient(int id) {

        return customerRepository.getClient(id);
    }
//Kustuta klient**************************************************************

    public String deleteClient(int id) {

       customerRepository.deleteAccount(id);
       customerRepository.deleteClient(id);
        return "Edukalt kustutatud klient ja kontod";
    }


    //KLIENDI LOOMINE********************************************************************************

    public String createClient(String firstName,String lastName,String address) {

        int clientId = customerRepository.createClient(firstName,lastName,address);


        return "Konto loodud  nimele: " + firstName + " " + lastName + " client id : " + clientId + " We care !";


    }






//** TEEME KONTO**************************************************************************************

    public String createAccount(int accountNumber, int clientId, boolean locked, int balance) {

        customerRepository.createAccount(accountNumber, clientId, locked, balance);



        return "Konto loodud  id: " + clientId + " Account Num: " + accountNumber;


    }

    //*********GETBALANCE*************************************************************************
    public String getBalance(int accountNumber) {


        return "Arve number: " + accountNumber + ". Saldo: " + customerRepository.getBalance(accountNumber);
    }
//UPDATE CLIENT***************************************************
public String updateClient(int id,String firstName,String lastName,String address) {


    customerRepository.updateCustomer(id,firstName,lastName,address);
    return " Uuendatud";
}





    //****LOCK****************************************************************************************************
    public String lock(int acNum) {

        customerRepository.locking(acNum, true);

        return "Konto numbriga " + acNum + " on  lukus";

    }


    //***UNLOCK******************************************************************************************************

    public String unlock(int acNum) {

        customerRepository.locking(acNum, false);


        return "Konto numbriga " + acNum + " on  lukust lahti";

    }


    //***** DEPOSIT*************************************************************************************************

    public String deposit(int accountNumber, int amount) {

            BankAccounts result = customerRepository.getCustomer(accountNumber);

        if (amount <= 0 || result.isLocked()) {
            throw new ApplicationException(" amount peaks olema suurem kui 0 v6i konto on lukus");
        }

            int newBalance = result.getBalance() + amount;
            customerRepository.changeBalance(accountNumber, newBalance);

//**** LISAME TRANSACTIONITE TABELISSE
            String type = " Successfully deposited " + amount + " EUR";
            customerRepository.addToTransactionHistory(accountNumber, type);


        return "Kontole lisatud " + amount + " EUR  ";

    }


    //*****WITHDRAW**********************************************************************************

    public String withdraw(int accountNumber, int amount) {

        BankAccounts result = customerRepository.getCustomer(accountNumber);

        if (amount <= 0 || result.isLocked()) {
            throw new ApplicationException(" amount peaks olema suurem kui 0 v6i konto on lukus");
        }
        if(amount> result.getBalance()){
            return "Kontol pole piisavalt vahendeid";
        }


            int newBalance = result.getBalance() - amount;
            customerRepository.changeBalance(accountNumber, newBalance);

            //**** LISAME TRANSACTIONITE TABELISSE

            String type = " Kontolt eemaldatud " + amount + " EUR";
            customerRepository.addToTransactionHistory(accountNumber, type);


        return amount + " EUR vähem kontol ";

    }


    //******* TRANSFER************************************************************************************

    public String transfer(int acNumFrom, int acNumTo, int amount) {

            BankAccounts customerFrom = customerRepository.getCustomer(acNumFrom);
            BankAccounts customerTo = customerRepository.getCustomer(acNumTo);

        if (amount <= 0 || customerFrom.isLocked() || customerTo.isLocked()) {
            throw new ApplicationException(" amount peaks olema suurem kui 0 v6i konto on lukus");
        }


            int newBalanceFrom = customerFrom.getBalance() - amount;
            int newBalanceTo = customerTo.getBalance() + amount;

            customerRepository.changeBalance(acNumFrom, newBalanceFrom);
            customerRepository.changeBalance(acNumTo, newBalanceTo);


            // ** add to trans history
            String typeFrom = " Transfered " + amount + " EUR to account " + acNumTo;
            String typeTo = " Received " + amount + " EUR from account " + acNumFrom;
            customerRepository.addToTransactionHistory(acNumFrom, typeFrom);
            customerRepository.addToTransactionHistory(acNumTo, typeTo);

            return "Successfully transfered  " + amount + " to account " + acNumTo;

    }


    //***GET CUSTOMER TRANSACITONS******************************************************************

    public List<Transaction> getTransactions(int acNum) {

        return customerRepository.getCustomerTransactions(acNum);
    }

}


