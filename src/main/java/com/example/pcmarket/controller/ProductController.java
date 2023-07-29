package com.example.pcmarket.controller;

import com.example.pcmarket.dto.ApiResponse;
import com.example.pcmarket.dto.ProductDTO;
import com.example.pcmarket.entity.Products;
import com.example.pcmarket.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping
    public List<Products> getAll(){
        List<Products> allP = productService.getAllP();
        return allP;
    }

    @GetMapping("/{id}")
    public Products getProduct(@PathVariable Integer id){
        Products product = productService.getProduct(id);
        return product;
    }

    @GetMapping("/page/{page_number}")
    public Page<Products> getPage(@PathVariable Integer page_number){
        Page<Products> pageP = productService.getPageP(page_number);
        return pageP;
    }

    @PostMapping
    public ApiResponse addP(@RequestBody ProductDTO productDTO){
        ApiResponse apiResponse = productService.addProduct(productDTO);
        return apiResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationError(MethodArgumentNotValidException ex){
         List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getMapErrors(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getMapErrors(List<String> errors){
        Map<String, List<String>> errorsResponse = new HashMap<>();
        errorsResponse.put("errors",errors);
        return errorsResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = productService.deleteP(id);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse update(@RequestBody ProductDTO productDTO, @PathVariable Integer id){
        ApiResponse apiResponse = productService.updateP(productDTO, id);
        return apiResponse;
    }
}
