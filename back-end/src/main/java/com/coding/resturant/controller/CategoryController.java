package com.coding.resturant.controller;

import com.coding.resturant.model.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.coding.resturant.service.CategoryService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("categories")
    public List<Category> getAllCategory() {
      return categoryService.allCategories();
    }

    @PostMapping( value = "category" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Category createCategory(@ModelAttribute Category category , @RequestParam("image") MultipartFile image) {
         return categoryService.createCategory(category , image);
    }
}
