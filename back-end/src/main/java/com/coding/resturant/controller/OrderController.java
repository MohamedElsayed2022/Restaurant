package com.coding.resturant.controller;

import com.coding.resturant.model.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.coding.resturant.service.OrderService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "order" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Order createOrder(@ModelAttribute Order order , @RequestParam("file") MultipartFile file){
        return orderService.createOrder(order , file);
    }
    @GetMapping("allOrders")
  public List<Order> getAllOrders(@RequestParam int page , @RequestParam int size){
        return orderService.getOrders(page , size);
  }
  @GetMapping("category")
  public List<Order> getOrdersByCategoryId( @RequestParam int page , @RequestParam int size ,@RequestParam Long id){
        return orderService.getOrdersByCategoryId(id , page , size);
  }
  @GetMapping("orderKey")
    public List<Order> getOrdersByKey( @RequestParam int page , @RequestParam int size , @RequestParam String word){
        return orderService.getOrdersByKey(word , page , size);
  }
  @GetMapping("order")
    public Order getOrderById(  @RequestParam Long id){
        return orderService.getOrderById(id);
  }
  @GetMapping("orderSize")
  public Long getOrdersSize(){
        return orderService.getOrdersSize();
  }
  @GetMapping("orderSizeByCatId")
  public Long getLengthOrderByCategoryId(@RequestParam Long categoryId){
        return  orderService.getOrderCountByCategoryId(categoryId);
  }
    @GetMapping("orderSizeByKey")
    public Long getLengthOrderByKey(@RequestParam String key){
        return  orderService.getOrderCountByKey(key);
    }

}
