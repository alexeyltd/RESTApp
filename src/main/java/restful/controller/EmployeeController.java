package restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;
import restful.model.Employee;
import restful.service.EmployeeService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    final static Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        logger.debug("Added: " + employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {

        Employee currentEmployee = employeeService.getById(employee.getId());
        if (currentEmployee == null) {
            logger.debug("Employee with id: " + employee.getId() + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            employeeService.save(employee);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);

        if (employee == null) {
            logger.debug("Employee with id: " + id + " does not exist");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found employee: " + employee);
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAll();
        if (employees.isEmpty()) {
            logger.debug("Employees does not exist");
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found " + employees.size() + " employees");
        logger.debug(employees);
        logger.debug(Arrays.toString(employees.toArray()));
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);
        if (employee == null) {
            logger.debug("Employee with id: " + id + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        else {
            employeeService.delete(id);
            logger.debug("Employee with id: " + id + " was deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

}
