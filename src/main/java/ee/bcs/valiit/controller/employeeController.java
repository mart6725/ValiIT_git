package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
// tagastaks kogu listi
//tagastaks indexi j'rgi (path variable) yhe tootaja
//saaks yle kirjutada indexi jargi (path variable) request body
//eemalda vastaval indexil
@RestController
public class employeeController {
    private List<employee> employeeList = new ArrayList<>();


    @GetMapping("employee")
    public List<employee>  getEmployee(){

        return employeeList;
    }


    @PostMapping("employee")
    public void saveEmployee(@RequestBody employee employee) {

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
