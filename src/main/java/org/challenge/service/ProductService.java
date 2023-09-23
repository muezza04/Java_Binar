package org.challenge.service;

import java.util.List;

public interface ProductService {
    void MainMenu();

    void showMenu();

    String priceFormatted(Integer data);

    void validMenu();

    Integer processMenu();

    void menuOrder(int results);

    Integer processMenuOrder(int order);

    String listBuy(String getSelect);
    boolean listQty(int result, int select);

    void reorders();

    void confirmation() ;

    void validConfirm();

    void dataOrder();

    int dataAmount(List<Integer> values);

    Integer processConfirm();

    void payment() ;

    void processPayment(StringBuilder result);

    void outputPayment(StringBuilder data);

    void writeFile(String targetFile, String data);

    void readFile(String originFile);
}
