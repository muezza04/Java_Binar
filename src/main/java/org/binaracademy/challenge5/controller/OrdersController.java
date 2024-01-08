package org.binaracademy.challenge5.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge5.model.DTO.request.OrdersRequest;
import org.binaracademy.challenge5.model.DTO.response.OrdersResponse;
import org.binaracademy.challenge5.model.DTO.request.ProductRequest;
import org.binaracademy.challenge5.service.impl.OrdersService;
import org.binaracademy.challenge5.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<OrdersResponse>> getOrders() {
        log.info("getting all orders in controller");
        List<OrdersResponse> ordersResponses = ordersService.readOrders();

        return new ResponseEntity<>(ordersResponses, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> postOrders(@RequestBody OrdersRequest ordersRequest) {
        try {
            ordersService.addOrders(ordersRequest);
            return new ResponseEntity<>("Orders Created success", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{orderId}")
    public ResponseEntity<String> putOrders(@PathVariable("orderId") Long orderId, @RequestBody OrdersRequest ordersRequest){
        try {
            ordersService.updateOrders(ordersRequest, orderId);
            return new ResponseEntity<>("Order Id " + orderId + " update successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete/{orderId}")
    public ResponseEntity<String> deleteOrders(@PathVariable Long orderId){
        try {
            ordersService.deleteOrders(orderId);
            return new ResponseEntity<>("Order Id " + orderId + " delete successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
