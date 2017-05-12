package restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restful.model.Employee;
import restful.repository.EmployeeRepository;

import java.io.Serializable;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public Employee getById(Serializable id) {
        return employeeRepository.findOne((Long) id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        employeeRepository.delete((Long) id);
    }
}
