package com.example.pcmarket.service;

import com.example.pcmarket.dto.ApiResponse;
import com.example.pcmarket.dto.CategoryDTO;
import com.example.pcmarket.entity.Category;
import com.example.pcmarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category getCategor(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.get();
    }

    public Page<Category> getPage(Integer page_number){
        Pageable pageable = PageRequest.of(page_number,5);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage;
    }

    public ApiResponse addCategory(CategoryDTO categoryDTO){
        boolean byName = categoryRepository.existsByName(categoryDTO.getName());
        if (byName){
            return new ApiResponse("Bunday name li category mavjud",false);
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return new ApiResponse("Sava Category", true);
    }

    public ApiResponse delete(Integer id){
        categoryRepository.deleteById(id);
        return new ApiResponse("Delete Category", true);
    }

    public ApiResponse update(CategoryDTO categoryDTO, Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(categoryDTO.getName());
            categoryRepository.save(category);
            return new ApiResponse("Update Category",true);
        }
        return new ApiResponse("Not Update", false);
    }
}
