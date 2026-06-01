package com.coding.resturant.product;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(Product product, List<MultipartFile> imgs){
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile img : imgs){
            String fileName = System.currentTimeMillis() + "_" + img.getOriginalFilename();
            Path path = Paths.get("uploads/orders/" + fileName);
            try {
                Files.copy(img.getInputStream(), path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fileNames.add(fileName);
        }
        product.setImg(fileNames);

        return productRepository.save(product);
    }
    public List<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
    }
    public List<Product> getProductsByCategoryId(Long categoryId , int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoryId(categoryId , pageable).getContent();
    }
    public List<Product> getProductsByKey(String Key , int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContaining(Key ,pageable).getContent();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    public long getProductsSize() {
        return productRepository.count();
    }
    public long getProductCountByCategoryId(long categoryId){
        return productRepository.getProductLengthByCategoryId(categoryId);
    }
    public long getProductCountByKey(String Key){
        return productRepository.getProductLengthByKey(Key);
    }
    public void  deleteProductById(Long id){ productRepository.deleteById(id); }
    public Product updateProduct( Long id, Product newProduct, List<MultipartFile> imgs ) {

        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setCategory(newProduct.getCategory());

        if (imgs != null && !imgs.isEmpty()) {

            List<String> fileNames = new ArrayList<>();

            for (MultipartFile img : imgs) {

                String fileName =
                        System.currentTimeMillis()
                                + "_" +
                                img.getOriginalFilename();

                Path path = Paths.get("uploads/" + fileName);

                try {
                    Files.copy(img.getInputStream(), path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                fileNames.add(fileName);
            }

            oldProduct.setImg(fileNames);
        }

        return productRepository.save(oldProduct);
    }

}
