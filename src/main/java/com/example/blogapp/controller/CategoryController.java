package com.example.blogapp.controller;

import com.example.blogapp.Dto.CategoryDto;
import com.example.blogapp.response.ApiResponse;
import com.example.blogapp.serviveImplementation.CategoryServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryServiceImp.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
        CategoryDto updateCatDto = categoryServiceImp.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(updateCatDto, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
        categoryServiceImp.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("delete ", false), HttpStatus.OK);
    }

    //get
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
        CategoryDto category = categoryServiceImp.getCategory(catId);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> catogeries = categoryServiceImp.getCatogeries();
        return ResponseEntity.ok(catogeries);

    }
    // getAll

}
