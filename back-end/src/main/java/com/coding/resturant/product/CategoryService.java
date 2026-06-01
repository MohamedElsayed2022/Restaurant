package com.coding.resturant.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> allCategories() {
      return categoryRepository.findAll();
    }
    public Category createCategory(Category category , MultipartFile image) {
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get("uploads/categories/" + fileName);

            try {
                Files.copy(image.getInputStream(), path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        category.setLogo(fileName);
        return categoryRepository.save(category);
    }
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    public Category updateCategory(Long id ,  Category category, MultipartFile image) {
        Category oldCategory = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found"));

        oldCategory.setName(category.getName());
        if (image != null && !image.isEmpty()) {

                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get("uploads/categories/");
            try {
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                Path newPath = path.resolve(fileName);
                Files.copy(image.getInputStream(), newPath);

                oldCategory.setLogo(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }
        return categoryRepository.save(oldCategory);

    }
}
