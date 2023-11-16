package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.*;
import org.binaracademy.challenge4.model.DTO.UsersResponse;
import org.binaracademy.challenge4.service.impl.OrdersDetailService;
import org.binaracademy.challenge4.service.impl.OrdersService;
import org.binaracademy.challenge4.service.impl.ProductService;
import org.binaracademy.challenge4.service.impl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

@Component
public class OrdersController {
    @Autowired
    private UsersController usersController;
    @Autowired
    private OrdersDetailController ordersDetailController;

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersDetailService ordersDetailService;

}
