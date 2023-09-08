package org.challenge.service;

import java.util.List;

public interface ProductService {
    void dataMenu();
    void showMenu();
    void validMenu();
    void processMenu();
    void listOrder(int results);
    void processListOrder(int order);
    void listBuy(String getSelect);
    void listQty(int result, int select);
    void reorders();
    void confirmation();
    void validConfirm();
    void dataOrder();
    int dataAmount(List<Integer> values);
    void processConfirm();
    void payment();
    void processPayment(StringBuilder result);
    void outputPayment(StringBuilder data);
    void writeFile(String targetFile, String data);
    void readFile(String originFile);
    String priceFormatted(int data);
}
