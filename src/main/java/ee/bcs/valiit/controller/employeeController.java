package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    private List<Employee> employeeList = new ArrayList<>();


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @GetMapping("employee")
    public List<Employee>  getEmployee(){

        String sql = "SELECT * FROM employee";
        Map<String,Object>paramMap=new HashMap<>();

        List<Employee> employeeList = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Employee.class));
        return employeeList;
    }


    @PostMapping("employee")
    public String saveEmployee(@RequestBody Employee employee) {

        String sql="INSERT INTO employee(first_name, last_name, position, address) VALUES (:firstName,:lastName,:position,:address)";
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("firstName",employee.getFirstName());
        paramMap.put("lastName",employee.getLastName());
        paramMap.put("position",employee.getPosition());
        paramMap.put("address",employee.getAddress());
        jdbcTemplate.update(sql,paramMap);

        return "Töötaja lisatud ";
        //employeeList.add(employee);            // lisame uue employee objekti listi


    }

    @GetMapping("employee/getEmployee/{id}")         // id j2rgi tagastame
    public Employee getByIndex (@PathVariable("id") int id){
        String sql = "SELECT * FROM employee WHERE id=:id";
        Map<String,Object>paramMap=new HashMap<>();

        paramMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<>(Employee.class));




        //return employeeList.get(id);            // idexi j'rgi otsime

    }
    @PutMapping("employee/update/{id}")
    public String updateByIndex(@RequestBody Employee employee, @PathVariable("id") int id){
        String sql="UPDATE employee SET first_name=:firstName,last_name=:lastName,position=:position,address=:address WHERE id=:id";
        Map<String,Object>paramMap=new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("firstName", employee.getFirstName());
        paramMap.put("lastName", employee.getLastName());
        paramMap.put("position", employee.getPosition());
        paramMap.put("address", employee.getAddress());
        jdbcTemplate.update(sql, paramMap);

        return "Uuendatud";
    }
    @DeleteMapping("employee/remove/{id}")
    public int removeByEmployeeByIndex(@PathVariable("id") int id){
        String sql = "DELETE FROM employee WHERE id=:id";
        Map<String,Object>paramMap=new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.update(sql,paramMap);


    }
}
