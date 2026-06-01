package com.coding.resturant.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     Page<Product> findByCategoryId(Long categoryId , Pageable pageable);
     Page<Product> findByNameContaining(String Key , Pageable pageable);
     @Query("select count(p.id) from Product p where p.category.id = ?1")
     Long getProductLengthByCategoryId(Long categoryId);
     @Query("select count(p.id) from Product p where p.name like %?1%")
     Long getProductLengthByKey(String Key);
}
