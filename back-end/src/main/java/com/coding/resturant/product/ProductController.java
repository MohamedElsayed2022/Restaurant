package com.coding.resturant.product;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "product" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(@ModelAttribute Product product , @RequestParam("imgs")List<MultipartFile> imgs){
        return productService.createProduct(product , imgs);
    }
    @DeleteMapping("product")
    public String deleteProduct(@RequestParam("id") Long id){
        productService.deleteProductById(id);
        return  "Product Deleted Successfully";
    }
    @PutMapping( value = "product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public Product updateProduct( @PathVariable Long id, @ModelAttribute Product product, @RequestParam(value = "imgs", required = false)
            List<MultipartFile> imgs
    ) {
        return productService.updateProduct(id, product, imgs);
    }
    @GetMapping("allProducts")
  public List<Product> getAllProducts(@RequestParam int page , @RequestParam int size){
        return productService.getProducts(page , size);
  }
  @GetMapping("category")
  public List<Product> getProductsByCategoryId( @RequestParam int page , @RequestParam int size ,@RequestParam Long id){
        return productService.getProductsByCategoryId(id , page , size);
  }
  @GetMapping("productKey")
    public List<Product> getProductsByKey( @RequestParam int page , @RequestParam int size , @RequestParam String word){
        return productService.getProductsByKey(word , page , size);
  }
  @GetMapping("product")
    public Product getProductById(  @RequestParam Long id){
        return productService.getProductById(id);
  }
  @GetMapping("productSize")
  public Long getProductsSize(){
        return productService.getProductsSize();
  }
  @GetMapping("productSizeByCatId")
  public Long getLengthProductByCategoryId(@RequestParam Long categoryId){
        return  productService.getProductCountByCategoryId(categoryId);
  }
    @GetMapping("productSizeByKey")
    public Long getLengthProductByKey(@RequestParam String key){
        return  productService.getProductCountByKey(key);
    }

}
