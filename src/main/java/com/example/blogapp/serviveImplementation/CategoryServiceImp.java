package com.example.blogapp.serviveImplementation;

import com.example.blogapp.Dto.CategoryDto;
import com.example.blogapp.Repository.CategoryRepository;
import com.example.blogapp.entities.Category;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category saveCatogry = categoryRepo.save(cat);
        return this.modelMapper.map(saveCatogry, CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updateCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updateCat, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCatogeries() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> allDto = new ArrayList<>();
        for (Category cat : categories) {
            CategoryDto catDto = modelMapper.map(cat, CategoryDto.class);
            allDto.add(catDto);
        }
        return allDto;
    }
}
