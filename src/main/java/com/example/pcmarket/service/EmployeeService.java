package com.example.pcmarket.service;

import com.example.pcmarket.dto.ApiResponse;
import com.example.pcmarket.dto.EmployeeDTO;
import com.example.pcmarket.entity.Employee;
import com.example.pcmarket.repository.EmployeeRepository;
import com.sun.xml.internal.ws.api.pipe.helper.AbstractPipeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllE(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    public Employee getEmployee(Integer id){
        Optional<Employee> byId = employeeRepository.findById(id);
        return byId.get();
    }

    public Page<Employee> getPageE(Integer page_number){
        Pageable pageable = PageRequest.of(page_number, 5);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage;
    }

    public ApiResponse saveEmployee(EmployeeDTO employeeDTO){
        boolean byPassword = employeeRepository.existsByPassword(employeeDTO.getPassword());
        if (byPassword){
            return new ApiResponse("Username yoki Password ni uzgartiring", false);
        }
        Employee employee = new Employee();
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(employeeDTO.getPassword());
        employee.setWorkTask(employeeDTO.getWorkTask());
        employeeRepository.save(employee);
        return new ApiResponse("Save Employee", true);
    }

    public ApiResponse deleteE(Integer id){
        employeeRepository.deleteById(id);
        return new ApiResponse("Delete Employee", true);
    }

    public ApiResponse updateE(EmployeeDTO employeeDTO, Integer id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setUsername(employeeDTO.getUsername());
            employee.setPassword(employeeDTO.getPassword());
            employee.setWorkTask(employeeDTO.getWorkTask());
            employeeRepository.save(employee);
            return new ApiResponse("Update Employee",true);
        }
        return new ApiResponse("Not Update Employee", false);
    }
}
