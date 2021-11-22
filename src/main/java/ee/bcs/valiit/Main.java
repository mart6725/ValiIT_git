package ee.bcs.valiit;

import ee.bcs.valiit.tasks.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Main {

    public static void main(String[] args) {
//        String a = "Hello World";
//        System.out.println(a.length());
//        System.out.println(a.charAt(6));
//        System.out.println(a.substring(0,5));
//        String b = "Hello World";
//        if(a.equals(b)){
//            System.out.println("Õige");
//        }
//        if(a == b){
//            System.out.println("Vale");
//        }





   }
//  @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate ;
//
//    @PutMapping("customers")
//    public List<Transaction>getAll(@RequestBody Transaction transaction){
//        String sql = "SELECT account_number FROM bank_customers WHERE id=:id";
//        Map<String,Object>paramMap=new HashMap<>();
//        paramMap.put("name",transaction.getAccountNumber());
//
//        List<Transaction> result = jdbcTemplate.query(sql,paramMap, new BeanPropertyRowMapper<>(Transaction.class));
//        return result;
    }



