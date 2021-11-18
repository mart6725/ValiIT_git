package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// tagastaks kogu listi
//tagastaks indexi j'rgi (path variable) yhe tootaja
//saaks yle kirjutada indexi jargi (path variable) request body
//eemalda vastaval indexil
@RestController
public class employeeController {
    private List<employee> employeeList = new ArrayList<>();


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @GetMapping("employee")
    public List<employee>  getEmployee(){

        return employeeList;
    }


    @PostMapping("employee")
    public void saveEmployee(@RequestBody employee employee) {

        String sql="INSERT INTO employee(first_name, last_name, position, address) VALUES (:firstName,:lastName,:position,:address)";
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("firstName",employee.getFirstName());
        paramMap.put("lastName",employee.getLastName());
        paramMap.put("position",employee.getPosition());
        paramMap.put("address",employee.getAddress());
        jdbcTemplate.update(sql,paramMap);


        employeeList.add(employee);            // lisame uue employee objekti listi


    }

    @GetMapping("employee/getEmployee/{id}")         // id j2rgi tagastame
    public employee getByIndex (@PathVariable("id") int id){

        return employeeList.get(id);            // idexi j'rgi otsime

    }
    @PutMapping("employee/update/{id}")
    public void updateByIndex(@RequestBody employee employee,@PathVariable("id") int id){
        employeeList.set(id,employee);              /// set on ylekirjutamine indexi j'rgi


    }
    @DeleteMapping("employee/remove/{id}")
    public void removeByEmployeeByIndex(@PathVariable("id") int id){
        employeeList.remove(id);


    }
}
