package org.challenge.service;

import lombok.NoArgsConstructor;
import org.challenge.model.Product;
import org.challenge.repository.ProductRepository;

import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

@NoArgsConstructor
public class ProductServiceImpl implements ProductService{
    Product product = new Product();
    Scanner inputScanner = new Scanner(System.in);

    // Konstruktor untuk menginisialisasi produk dari repositori
    public ProductServiceImpl(ProductRepository productRepository) {
        initializeProduct(productRepository);
    }

    // Metode untuk menginisialisasi produk dari repositori
    private void initializeProduct(ProductRepository productRepository) {
        product.setListMenu(productRepository.getDataMenu());
        product.setListPrice(productRepository.getDataPrice());
    }

    @Override
    public void MainMenu() {
        System.out.println(product.getLINE());
        System.out.println("Selamat datang di BinarFud");
        System.out.println(product.getLINE());
        System.out.println("Silahkan pilih makanan : ");
        showMenu();
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi");
    }

    // Show header menu
    @Override
    public void showMenu() {
        for (int i = 0; i < product.getListMenu().size(); i++) {
            String priceLine = (i == product.getListMenu().size() - 1) ? "\t\t | " : "\t | ";
            System.out.println((i + 1) + ". " + product.getListMenu().get(i) + priceLine + priceFormatted(product.getListPrice().get(i)));
        }
    }

    // Mengformat harga menjadi string dengan pemisah ribuan
    @Override
    public String priceFormatted(Integer data) {
        return String.format("%,d", data);
    }

    @Override
    public void validMenu() {
        System.out.print("Apakah Anda ingin keluar? (y/n): ");
        String result = inputScanner.nextLine().toLowerCase();

        if (result.equals("y")) {
            System.out.println("Terima kasih! Program keluar.");
            System.exit(0);
        } else if (result.equals("n")) {
            System.out.println("Silahkan memilih menu yang tersedia diatas!");
            processMenu();
        } else {
            System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
            validMenu();
        }
    }

    @Override
    public Integer processMenu() {
        System.out.print("=> ");
        int result = inputScanner.nextInt();
        try {
            if (result >= 1 && result <= product.getListMenu().size()) {
                this.menuOrder(result - 1);
            } else if (result == 99) {
                this.confirmation();
            } else if (result == 0) {
                this.validMenu();
            } else {
                throw new InputMismatchException();
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid!");
            inputScanner.nextLine();
            this.processMenu();
        }
        return result;
    }

    // Menampilkan pesanan dan harga
    @Override
    public void menuOrder(int resultsOrder) {
        System.out.println(product.getLINE());
        System.out.println("Berapa pesanan anda");
        System.out.println(product.getLINE()+"\n");
        System.out.println(product.getListMenu().get(resultsOrder) + "\t | "+product.getListPrice().get(resultsOrder));
        System.out.println("(input 0 untuk kembali)\n");

        processMenuOrder(resultsOrder);
    }

    @Override
    public Integer processMenuOrder(int order) {
        System.out.print("qty => ");
        int result = inputScanner.nextInt();
        try {
            if (result == 0) {
                this.MainMenu();
                this.processMenu();
            } else {
                this.listBuy(product.getListMenu().get(order));
                product.getListQty().add(result);
                this.listQty(result, order);

                this.reorders();
            }

        } catch (Exception e) {
            System.out.println("Input tidak valid!");
            this.processMenuOrder(order);
        }

        return result;
    }

    // Menambahkan pesanan ke daftar
    @Override
    public String listBuy(String getSelect) {
        return String.valueOf(product.getListSelect().add(getSelect));
    }

    // Menambahkan jumlah harga pesanan
    @Override
    public boolean listQty(int result, int select) {
        int i = result * product.getListPrice().get(select);
        return product.getListHarga().add(i);
    }

    @Override
    public void reorders() {
        inputScanner.nextLine(); // Untuk menghilangkan data dari sebelumnya
        System.out.print("Apakah Anda ingin mesan lagi? (y/n): ");
        String result = inputScanner.nextLine().toLowerCase();

        if (result.equals("y")) {
            System.out.println("Silahkan memilih menu yang tersedia!");
            this.MainMenu();
            this.processMenu();
        } else if (result.equals("n")) {
            System.out.println("Silahkan melanjutkan pembayaran!");
            this.confirmation();
        } else {
            System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
            this.reorders();
        }
    }

    @Override
    public void confirmation() {
        this.validConfirm();

        // Menampilkan konfirmasi dan rincian pembayaran
        System.out.println(product.getLINE());
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println(product.getLINE()+"\n");

        this.dataOrder();

        System.out.println("-------------------------------+");

        System.out.println("Total\t\t\t"+
                dataAmount(product.getListQty())+"\t\t"+priceFormatted(
                        dataAmount(product.getListHarga()))+"\n");

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar dari aplikasi");

        this.processConfirm();
    }

    // Memeriksa apakah ada pesanan yang dipilih sebelum konfirmasi
    @Override
    public void validConfirm() {
        boolean allListsEmpty = Stream.of(
                        product.getListSelect(),
                        product.getListQty(),
                        product.getListHarga())
                .allMatch(List::isEmpty);

        if (allListsEmpty) {
            System.out.println("Anda belum memilih menu");
            this.processMenu();
        }
    }


    @Override
    public void dataOrder() {
        for (int i = 0; i<product.getListSelect().size(); i++) {
            String priceLine = (product.getListSelect().get(i).equals("Es Teh Manis")) ? "\t" : "\t\t";
            System.out.println(product.getListSelect().get(i) + priceLine + product.getListQty().get(i) + "\t\t" + priceFormatted(product.getListHarga().get(i)));
        }
    }

    // Menghitung total nilai dari daftar integer
    @Override
    public int dataAmount(List<Integer> values) {
        int total = values.stream()
                .mapToInt(Integer::intValue)
                .sum();

        return Optional.ofNullable(total).get();
    }

    @Override
    public Integer processConfirm() {
        System.out.print("=> ");
        int result = inputScanner.nextInt();
        try {
            if (result == 0) {
                System.exit(0);
            } else if (result == 1) {
                this.payment();
            } else if (result == 2) {
                this.reorders();
            } else  {
                System.out.println("Gagal, silahkan pilih ulang");
                this.confirmation();
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid. Masukkan angka!");
            this.processConfirm();
        }
        return result;
    }

    // Menyiapkan data pembayaran
    @Override
    public void payment() {
        StringBuilder data = new StringBuilder();
        data.append("================================\n");
        data.append("BinarFud\n");
        data.append("================================\n\n");
        data.append("Terimakasih sudah memesan \ndi BinarFud\n\n");
        data.append("Dibawah ini adalah pesanan anda :\n\n");
        this.processPayment(data);
        data.append("Pembayaran : BinarCash\n\n");
        data.append("================================\n");
        data.append("Simpan struk ini sebagai \nbukti pembayaran\n");
        data.append("================================");

        this.outputPayment(data);
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

        try {
            if (result.isEmpty() || result.equals(null)){
                throw new NullPointerException();
            }
            // C:\Users\<nama_pengguna>\Downloads
            String locFile = "D:\\Aplication\\Running\\java\\BE_JAVA\\Binar\\BinarChallenge\\Challenge3\\src\\main\\resources\\";

            // Menulis data ke file dan menampilkan isi file yang telah ditulis
            writeFile(locFile+result+".txt", data.toString());
            readFile(locFile+result+".txt");

        } catch (Exception e) {
            System.out.println("Nama yang dimasukkan tidak boleh kosong\nTekan enter untuk mengulang kembali");
            this.outputPayment(data);
        }
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
}
