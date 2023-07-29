package com.example.pcmarket.service;

import com.example.pcmarket.dto.ApiResponse;
import com.example.pcmarket.dto.ProductDTO;
import com.example.pcmarket.entity.Category;
import com.example.pcmarket.entity.Products;
import com.example.pcmarket.repository.CategoryRepository;
import com.example.pcmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Products> getAllP(){
        List<Products> productList = productRepository.findAll();
        return productList;
    }

    public Products getProduct(Integer id){
        Optional<Products> byId = productRepository.findById(id);
        return byId.get();
    }

    public Page<Products> getPageP(Integer page_number){
        Pageable pageable = PageRequest.of(page_number, 5);
        Page<Products> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    public ApiResponse addProduct(ProductDTO productDTO){
        Products products = new Products();
        products.setName(productDTO.getName());
        products.setDescription(productDTO.getDescription());
        products.setPrice(productDTO.getPrice());
        Optional<Category> repositoryById = categoryRepository.findById(productDTO.getCategory_id());
        if (repositoryById.isPresent()){
            products.setCategory(repositoryById.get());
            productRepository.save(products);
            return new ApiResponse("Save Product", true);
        }
        return new ApiResponse("Bunday Id li Category Yoq", false);

    }

    public ApiResponse deleteP(Integer id){
        productRepository.deleteById(id);
        return new ApiResponse("Delete",true);
    }

    public ApiResponse updateP(ProductDTO productDTO, Integer id){
        Optional<Products> optionalProducts = productRepository.findById(id);
        if (optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            products.setName(productDTO.getName());
            products.setDescription(productDTO.getDescription());
            products.setPrice(productDTO.getPrice());
            productRepository.save(products);
            return  new ApiResponse("Update Product", true);
        }
        return new ApiResponse("Not Update",false);
    }
}

