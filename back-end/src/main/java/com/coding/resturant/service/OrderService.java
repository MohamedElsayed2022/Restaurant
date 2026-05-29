package com.coding.resturant.service;

import com.coding.resturant.model.Order;
import com.coding.resturant.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public Order createOrder(Order order,List<MultipartFile> imgs){
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
        order.setImg(fileNames);

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
    public void  deleteOrderById(Long id){ orderRepository.deleteById(id); }
    public Order updateOrder( Long id, Order newOrder, List<MultipartFile> imgs ) {

        Order oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        oldOrder.setName(newOrder.getName());
        oldOrder.setPrice(newOrder.getPrice());
        oldOrder.setDescription(newOrder.getDescription());
        oldOrder.setCategory(newOrder.getCategory());

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

            oldOrder.setImg(fileNames);
        }

        return orderRepository.save(oldOrder);
    }

}
