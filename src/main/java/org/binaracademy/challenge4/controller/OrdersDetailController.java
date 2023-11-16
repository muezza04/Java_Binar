package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Orders;
import org.binaracademy.challenge4.model.OrdersDetail;
import org.binaracademy.challenge4.service.impl.OrdersDetailService;
import org.binaracademy.challenge4.service.impl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class OrdersDetailController {
    @Autowired
    private OrdersDetailService ordersDetailService;

    @Autowired
    private OrdersService ordersService;

}
