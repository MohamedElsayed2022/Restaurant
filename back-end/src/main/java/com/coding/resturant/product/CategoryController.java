package com.coding.resturant.product;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
    @DeleteMapping("category/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "Deleted Category";
    }

    @PutMapping( value = "category/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public Category updateCategory(@PathVariable Long id, @ModelAttribute Category category, @RequestParam(value = "image", required = false) MultipartFile image) {
        return categoryService.updateCategory(id, category ,image );
    }
}
