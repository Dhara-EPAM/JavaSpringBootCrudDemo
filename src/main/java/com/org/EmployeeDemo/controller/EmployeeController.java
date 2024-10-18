package com.org.EmployeeDemo.controller;

import com.org.EmployeeDemo.model.Employee;
import com.org.EmployeeDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> GetAllEmployee(){
        try {
            List<Employee> employeeList = new ArrayList<>();
            employeeRepo.findAll().forEach(employeeList::add);

            if(employeeList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employeeList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> GetEmployeeById(@PathVariable long id)
    {
        Optional<Employee> employeeData = employeeRepo.findById(id);
        if(employeeData.isPresent()){
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> AddEmployee(@RequestBody Employee employee)
    {
        Employee employeeObj = employeeRepo.save(employee);

        return new ResponseEntity<>(employeeObj, HttpStatus.OK);
    }

    @PostMapping("/updateEmployeeById/{id}")
    public ResponseEntity<Employee> UpdateEmployeeById(@PathVariable long id, @RequestBody Employee newEmployeeData)
    {
        Optional<Employee> oldBookData = employeeRepo.findById(id);
        if(oldBookData.isPresent())
        {
            Employee updatedEmployeeData = oldBookData.get();
            updatedEmployeeData.setFirstName(newEmployeeData.FirstName);
            updatedEmployeeData.setLastName(newEmployeeData.LastName);
            updatedEmployeeData.setDesignation(newEmployeeData.Designation);
            updatedEmployeeData.setAge(newEmployeeData.Age);

            Employee employeeObj = employeeRepo.save(updatedEmployeeData);

            return new ResponseEntity<>(employeeObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public ResponseEntity<HttpStatus> DeleteEmployeeById(@PathVariable long id)
    {
        employeeRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
