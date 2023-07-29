package com.example.pcmarket.controller;

import com.example.pcmarket.dto.ApiResponse;
import com.example.pcmarket.dto.EmployeeDTO;
import com.example.pcmarket.entity.Employee;
import com.example.pcmarket.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping
    public List<Employee> getAll(){
        List<Employee> allE = employeeService.getAllE();
        return allE;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    @GetMapping("/page/{page_number}")
    public Page<Employee> getPage(@PathVariable Integer page_number){
        Page<Employee> pageE = employeeService.getPageE(page_number);
        return pageE;
    }

    @PostMapping
    public ApiResponse saveE(@RequestBody EmployeeDTO employeeDTO){
        ApiResponse apiResponse = employeeService.saveEmployee(employeeDTO);
        return apiResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handvalidationErrors(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrors(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrors(List<String> errors){
        Map<String, List<String>> errorsResponse = new HashMap<>();
        errorsResponse.put("errors",errors);
        return errorsResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = employeeService.deleteE(id);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse update(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id){
        ApiResponse apiResponse = employeeService.updateE(employeeDTO, id);
        return apiResponse;
    }
}
