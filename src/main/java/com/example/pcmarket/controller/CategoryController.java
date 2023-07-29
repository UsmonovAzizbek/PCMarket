package com.example.pcmarket.controller;

import com.example.pcmarket.dto.ApiResponse;
import com.example.pcmarket.dto.CategoryDTO;
import com.example.pcmarket.entity.Category;
import com.example.pcmarket.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public List<Category> getAll(){
        List<Category> serviceAll = categoryService.getAll();
        return serviceAll;
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id){
        Category categor = categoryService.getCategor(id);
        return categor;
    }

    @GetMapping("/page/{page_number}")
    public Page<Category> getPage(@PathVariable Integer page_number){
        Page<Category> categoryPage = categoryService.getPage(page_number);
        return categoryPage;
    }

    @PostMapping
    public ApiResponse save(@RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse = categoryService.addCategory(categoryDTO);
        return apiResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handValidationError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors),new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors){
        Map<String, List<String>> errorsResponse = new HashMap<>();
        errorsResponse.put("errors",errors);
        return errorsResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse delete = categoryService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public ApiResponse update(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer id){
        ApiResponse response = categoryService.update(categoryDTO, id);
        return response;
    }
}
