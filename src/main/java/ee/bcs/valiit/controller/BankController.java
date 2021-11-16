package ee.bcs.valiit.controller;

import ee.bcs.valiit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BankController {
    @Autowired
    private AccountService accountService;

    @GetMapping("account_test/{id}")
    public AccountResponse getAccounts(@PathVariable("id") Long id){
        return accountService.getAccount(id);
    }

    @PostMapping("account/{requestName}/{requestAddress}")
    public void createAccount(@PathVariable("requestName") String requestName,
                              @PathVariable("requestAddress") String requestAddress){
        accountService.createAccount(requestName, requestAddress);
    }
    // depositMoney (accountNr, money)
    // localhost:8080/account?accountNr=ads&money=24
    @PutMapping("account")
    public void depositMoney(@RequestParam("accountNr")  String accountNr,
                             @RequestParam("money") BigDecimal amount,
                             BigDecimal id){
        String sql = "SELECT amount FROM account where id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
    }
    // withdrawMoney (accountNr, money)
    // transferMoney (fromAccount, toAccount, money)
    // getAccountBalance (accountNr)
    // Raskem
    // createClient(firstName lastName, ....)
    // muuta createAccount (clientId, accountNr)
    // getBalanceHistory(accountNr)

    @GetMapping("test-sql")
    public void selectManyRowsSample(){
        String sql = "SELECT * FROM account";
        Map paramMap = new HashMap();
    }
}
