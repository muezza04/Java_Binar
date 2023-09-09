package org.challenge.service;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void dataMenu();
    void showMenu();
    void validMenu() throws IOException;
    void processMenu();
    void listOrder(int results);
    void processListOrder(int order);
    void listBuy(String getSelect);
    void listQty(int result, int select);
    void reorders() throws IOException;
    void confirmation();
    void validConfirm();
    void dataOrder();
    int dataAmount(List<Integer> values);
    void processConfirm();
    void payment() throws IOException;
    void processPayment(StringBuilder result);
    void outputPayment(StringBuilder data) throws IOException;
    void writeFile(String targetFile, String data);
    void readFile(String originFile);
    String priceFormatted(int data);
}
