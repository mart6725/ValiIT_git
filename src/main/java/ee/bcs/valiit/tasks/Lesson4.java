package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    static HashMap<String, Integer> accountBalanceMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("Choose action");
            System.out.println("0 -> Create Account");
            System.out.println("1 -> Get Balance");
            System.out.println("2 -> Deposit money");
            System.out.println("3 -> Withdraw money");
            System.out.println("4 -> Transfer from account to account");
            System.out.println("5 -> Close program");


            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Please enter account number");
                    String accountNum = scanner.nextLine();
                    accountBalanceMap.put(accountNum, 0);
                    System.out.println("Account nr successfully added");
                    break;

                case 1:
                    System.out.println("please enter account nr");
                    String accountBalance = scanner.nextLine();
                    System.out.println("This account balance is " + accountBalanceMap.get(accountBalance) + " EUR");
                    break;

                case 2:
                    System.out.println("please enter account nr");
                    String accountAdd = scanner.nextLine();
                    System.out.println("please enter amount");
                    int amount = scanner.nextInt();
                    if (amount > 0) {
                        accountBalanceMap.put(accountAdd, amount);
                    } else {
                        System.out.println("add more");
                    }

                    System.out.println("successfully added  " + amount + " EUR and balance is " + accountBalanceMap.get(accountAdd) + " EUR");
                    break;

                case 3:
                    System.out.println("please enter account nr");
                    String accountRemoveNum = scanner.nextLine();
                    System.out.println("please enter amount");
                    int amountRemove = scanner.nextInt();
                    if (amountRemove > 0 && amountRemove <= accountBalanceMap.get(accountRemoveNum)) {
                        int currentBalance = accountBalanceMap.get(accountRemoveNum);
                        int newBalance = currentBalance - amountRemove;
                        accountBalanceMap.put(accountRemoveNum, newBalance);
                        System.out.println("Successfully withdrawn " + amountRemove + " Balance is " + accountBalanceMap.get(accountRemoveNum));
                    } else {
                        System.out.println("please remove valid amount");
                    }

                case 4:
                    System.out.println("please enter FIRST account nr");
                    String accountRemoveNum1 = scanner.nextLine();
                    System.out.println("please enter amount to trasfer");
                    int amountRemove1 = scanner.nextInt();
                    System.out.println("please enter  account nr to transfer");
                    scanner.nextLine();                                                 // puhastab buffri, kuna eelnev oli scanner.nextInt
                    String accountToTransfer = scanner.nextLine();

                    if (amountRemove1 > 0 && amountRemove1 <= accountBalanceMap.get(accountRemoveNum1)) {

                        int currentBalance = accountBalanceMap.get(accountRemoveNum1);                  // uus balance esimsele accountile
                        int newBalance = currentBalance - amountRemove1;
                        accountBalanceMap.put(accountRemoveNum1, newBalance);


                        int secondAccountBalance = accountBalanceMap.get(accountToTransfer);            // lisan summa uuele accountile
                        int SecondAccountNewBalance = secondAccountBalance + amountRemove1;
                        accountBalanceMap.put(accountToTransfer, SecondAccountNewBalance);


                        System.out.println("Successfully transfered " + amountRemove1 + " EUR");
                    } else {
                        System.out.println("please transfer valid amount");
                    }
                    break;

                case 5:
                    System.out.println("closing....");
                    quit = true;
                    break;

                default:
                    System.out.println("Unknown command");
                    break;

            }
            // TODO 1
            // Add command: "createAccount ${accountNr}"
            // this has to store accountNr with 0 balance
            // TODO 2
            // Add command: "getBalance ${accountNr}"
            // this has to display account balance of specific acount
            // TODO 3
            // Add command: "depositMoney ${accountNr} ${amount}
            // this has to add specified amount of money to account
            // You have to check that amount is positive number
            // TODO 4
            // Add command: "withdrawMoney ${accountNr} ${amount}
            // This has to remove specified amount of money from account
            // You have to check that amount is positive number
            // You may not allow this transaction if account balance would become negative
            // TODO 5
            // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
            // This has to remove specified amount from fromAccount and add it to toAccount
            // Your application needs to check that toAccount is positive
            // And from account has enough money to do that transaction

        }
    }
}
