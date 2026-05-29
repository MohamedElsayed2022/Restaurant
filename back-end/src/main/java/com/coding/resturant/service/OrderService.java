package com.coding.resturant.service;

import com.coding.resturant.model.Order;
import com.coding.resturant.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public Order createOrder(Order order, MultipartFile img){
        String fileName = System.currentTimeMillis() + "_" + img.getOriginalFilename();

        Path path = Paths.get("uploads/" + fileName);

        try {
            Files.copy(img.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        order.setImg(fileName);

        return orderRepository.save(order);
    }
    public List<Order> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable).getContent();
    }
    public List<Order> getOrdersByCategoryId(Long categoryId , int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByCategoryId(categoryId , pageable).getContent();
    }
    public List<Order> getOrdersByKey(String Key , int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByNameContaining(Key ,pageable).getContent();
    }
    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public long getOrdersSize() {
        return orderRepository.count();
    }
    public long getOrderCountByCategoryId(long categoryId){
        return orderRepository.getOrderLengthByCategoryId(categoryId);
    }
    public long getOrderCountByKey(String Key){
        return orderRepository.getOrderLengthByKey(Key);
    }


}
