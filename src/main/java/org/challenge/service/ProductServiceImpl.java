package org.challenge.service;

import org.challenge.model.Product;
import org.challenge.repository.ProductRepository;

import java.io.*;
import java.util.*;

public class ProductServiceImpl implements ProductService{

    Product product = new Product();
    Scanner inputScanner = new Scanner(System.in);

    public ProductServiceImpl(ProductRepository productRepository) {
        product.setListMenu(Arrays.asList(productRepository.getDataMenu()));
        product.setListPrice(Arrays.asList(productRepository.getDataPrice()));
    }

    @Override
    public void dataMenu() {
        for (int i = 0; i < product.getListMenu().size(); i++) {
            String priceLine = (i == product.getListMenu().size() - 1) ? "\t\t | " : "\t | ";
            System.out.println((i + 1) + ". " + product.getListMenu().get(i) + priceLine + priceFormatted(product.getListPrice().get(i)));
        }
    }

    @Override
    public void showMenu() {
        System.out.println(product.getTopLine());
        System.out.println("Selamat datang di BinarFud");
        System.out.println(product.getUnderline());
        System.out.println("Silahkan pilih makanan : ");
        dataMenu();
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi");
    }

    @Override
    public void validMenu() {
        System.out.print("Apakah Anda ingin keluar? (y/n): ");
        String input = inputScanner.nextLine().toLowerCase();

        if (input.equals("y")) {
            System.out.println("Terima kasih! Program keluar.");
            inputScanner.close();
            System.exit(0);
        } else if (input.equals("n")) {
            System.out.println("Silahkan memilih menu yang tersedia diatas!");
            processMenu();
        } else {
            System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
            validMenu();
        }
    }

    @Override
    public void processMenu(){
        try {
            System.out.print("=> ");
            int result = inputScanner.nextInt();

            if (result >= 1 && result <= product.getListMenu().size()) {
                listOrder(result - 1);
            } else if (result == 99) {
                confirmation();
            } else if (result == 0) {
                inputScanner.nextLine();
                validMenu();
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            inputScanner.nextLine();
            System.out.println("Input tidak valid!");
            processMenu();
        }
    }

    @Override
    public void listOrder(int resultsOrder) {
        System.out.println(product.getTopLine());
        System.out.println("Berapa pesanan anda");
        System.out.println(product.getUnderline());
        System.out.println(product.getListMenu().get(resultsOrder) + "\t | "+product.getListPrice().get(resultsOrder));
        System.out.println("(input 0 untuk kembali)\n");

        processListOrder(resultsOrder);
    }

    @Override
    public void processListOrder(int order) {
        try {
            inputScanner.nextLine();
            System.out.print("qty => ");
            int result = inputScanner.nextInt();
            if (result == 0) {
                processMenu();
            } else {
                listBuy(product.getListMenu().get(order));
                product.getListQty().add(result);
                listQty(result, order);
                inputScanner.nextLine();
                reorders();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid!");
            processListOrder(order);
        }
    }

    @Override
    public void listBuy(String getSelect) {
        product.getListSelect().add(getSelect);
    }

    @Override
    public void listQty(int result, int select) {
        int i = result * product.getListPrice().get(select);
        product.getListHarga().add(i);
    }

    @Override
    public void reorders() {
        System.out.print("Apakah Anda ingin mesan lagi? (y/n): ");
        String input = inputScanner.nextLine().toLowerCase();

        if (input.equals("y")) {
            System.out.println("Silahkan memilih menu yang tersedia!");
            showMenu();
            processMenu();
        } else if (input.equals("n")) {
            System.out.println("Silahkan melanjutkan pembayaran!");
            confirmation();
        } else {
            System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
            reorders();
        }
    }

    @Override
    public void confirmation() {
        validConfirm();

        System.out.println(product.getTopLine());
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println(product.getUnderline());

        dataOrder();

        System.out.println("-------------------------------+");

        System.out.println("Total\t\t\t"+
                dataAmount(product.getListQty())+"\t\t"+priceFormatted(
                        dataAmount(product.getListHarga()))+"\n");

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar dari aplikasi");

        processConfirm();
    }

    @Override
    public void validConfirm() {
        if (product.getListSelect().isEmpty() && product.getListQty().isEmpty() && product.getListHarga().isEmpty()){
            System.out.println("Anda belum memilih menu");
            processMenu();
        }
    }

    @Override
    public void dataOrder() {
        for (int i = 0; i<product.getListSelect().size(); i++) {
            String priceLine = (product.getListSelect().get(i).equals("Es Teh Manis")) ? "\t" : "\t\t";
            System.out.println(product.getListSelect().get(i) + priceLine + product.getListQty().get(i) + "\t\t" + priceFormatted(product.getListHarga().get(i)));
        }
    }

    @Override
    public int dataAmount(List<Integer> values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    @Override
    public void processConfirm() {
        try {
            System.out.print("=> ");
            int result = inputScanner.nextInt();
            if (result == 0) {
                inputScanner.close();
                System.exit(0);
            } else if (result == 1) {
                payment();
            } else if (result == 2) {
                inputScanner.nextLine();
                reorders();
            } else  {
                System.out.println("Gagal, silahkan pilih ulang");
                confirmation();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka!");
            inputScanner.nextLine();
            processConfirm();
        }
    }

    @Override
    public void payment() {
        StringBuilder data = new StringBuilder();
        data.append("================================\n");
        data.append("BinarFud\n");
        data.append("================================\n\n");
        data.append("Terimakasih sudah memesan \ndi BinarFud\n\n");
        data.append("Dibawah ini adalah pesanan anda :\n\n");
        processPayment(data);
        data.append("Pembayaran : BinarCash\n\n");
        data.append("================================\n");
        data.append("Simpan struk ini sebagai \nbukti pembayaran\n");
        data.append("================================");

        outputPayment(data);
    }

    @Override
    public void processPayment(StringBuilder result) {
        for (int i = 0; i < product.getListSelect().size(); i++) {
            String priceLine = (product.getListSelect().get(i).equals(product.getListMenu().get(3))) ? "\t" : "\t\t";
            result.append(product.getListSelect().get(i)).append(priceLine).append(product.getListQty().get(i)).append("\t\t").append(priceFormatted(product.getListHarga().get(i))).append("\n");
        }
        result.append("-------------------------------+\n");

        result.append("Total\t\t\t").append(dataAmount(product.getListQty())).append("\t\t").append(priceFormatted(dataAmount(product.getListHarga()))).append("\n\n");
    }

    @Override
    public void outputPayment(StringBuilder data) {
        inputScanner.nextLine();
        System.out.print("Masukkan nama file => ");
        String result = inputScanner.nextLine();

        String locFile = "D:\\Aplication\\Running\\java\\BE_JAVA\\Binar\\BinarChallenge\\Challenge2\\src\\main\\resources\\";

        writeFile(locFile+result+".txt", data.toString());
        // Membaca dan menampilkan isi file yang telah ditulis
        readFile(locFile+result+".txt");
    }

    @Override
    public void writeFile(String targetFile, String data) {
        try {
            File file = new File(targetFile);
            boolean fileExists = file.exists();

            try (FileWriter fw = new FileWriter(file, true); // true untuk append ke file yang ada
                 BufferedWriter bw = new BufferedWriter(fw)) {

                if (!fileExists) {
                    System.out.println("File baru telah dibuat: " + targetFile);
                }

                bw.write(data);
                bw.newLine();

                System.out.println("Penulisan ke file berhasil\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readFile(String originFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(originFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tempArr = line.split(",");
                for (String s : tempArr) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String priceFormatted(int data) {
        return String.format("%,d", data);
    }
}
