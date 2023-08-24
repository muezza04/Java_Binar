package org.challenge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Challenge1 {
    static List<String> listMenu = Arrays.asList(new String[] {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"});
    static List<Integer> listPrice = Arrays.asList(new Integer[] {15000, 13000, 18000, 3000, 5000});
    static List<String> listSelect = new ArrayList<>();
    static List<Integer> listQty = new ArrayList<>();
    static List<Integer> listHarga = new ArrayList<>();
    static Scanner inputScanner = new Scanner(System.in);


    public static void main(String[] args) {
        if (listMenu.size() != listPrice.size()) {
            System.out.println("Error!, Ada kesalahan pada sistem");
            System.exit(0);
        }
        menu();
        getMenu();
    }

    public static String priceFormatted(int data) {
        return String.format("%,d", data);
    }

    public static void write(String targetFile, String data) {
        try {
            // java akan membuka file, klo tdk ada java akan create
            File file = new File(targetFile);
            if(file.createNewFile()) {
                System.out.println("file has been created");
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.flush();
            bw.close();
            System.out.println("Penulisan ke file succes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void payment() {
        StringBuilder data = new StringBuilder();
        data.append("================================\n");
        data.append("BinarFud\n");
        data.append("================================\n\n");
        data.append("Terimakasih sudah memesan \ndi BinarFud\n\n");
        data.append("Dibawah ini adalah pesanan anda :\n\n");
        prosesPemesanan(data);
        data.append("Pembayaran : BinarCash\n\n");
        data.append("================================\n");
        data.append("Simpan struk ini sebagai \nbukti pembayaran\n");
        data.append("================================");

        inputScanner.nextLine();
        System.out.print("Masukkan nama file => ");
        String result = inputScanner.nextLine();
        write("D:\\Aplication\\Running\\java\\BE_JAVA\\Binar\\BinarChallenge\\src\\main\\resources\\"+result+".txt", data.toString());
    }

    public static void prosesPemesanan(StringBuilder result) {
        for (int i = 0; i < listSelect.size(); i++) {
            String priceLine = (listSelect.get(i).equals("Es Teh Manis")) ? "\t" : "\t\t";
            result.append(listSelect.get(i)).append(priceLine).append(listQty.get(i)).append("\t\t").append(priceFormatted(listHarga.get(i))).append("\n");
        }
        result.append("-------------------------------+\n");
        int sum = 0;
        for (int qty : listQty) {
            sum += qty;
        }
        int total = 0;
        for (int harga : listHarga) {
            total += harga;
        }
        result.append("Total\t\t\t").append(sum).append("\t\t").append(priceFormatted(total)).append("\n\n");
    }

    public static void confirmation() {
        if (listSelect.isEmpty() && listQty.isEmpty() && listHarga.isEmpty()){
            System.out.println("Anda belum memesan");
            getMenu();
        }

        System.out.println("==========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("==========================\n");

        for (int i = 0; i<listSelect.size(); i++) {
            String priceLine = (listSelect.get(i).equals("Es Teh Manis")) ? "\t" : "\t\t";
            System.out.println(listSelect.get(i) + priceLine + listQty.get(i) + "\t\t" + priceFormatted(listHarga.get(i)));

        }
        System.out.println("-------------------------------+");
        int sum = 0;
        for (int qty : listQty){
            sum += qty;
        }
        int total = 0;
        for (int harga : listHarga){
            total += harga;
        }
        System.out.println("Total\t\t\t"+sum+"\t\t"+priceFormatted(total)+"\n");

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar dari aplikasi");

        System.out.print("=> ");
        int result = inputScanner.nextInt();
        if (result == 1) {
            payment();
            System.exit(0);
        } else if (result == 2) {
            // Problem looping infinite
            pesanan();
            return;
        } else if (result == 0) {
            inputScanner.close();
            System.exit(0);
        } else {
            System.out.println("Gagal, silahkan pilih ulang");
            confirmation();
        }
    }

    public static void pesanan() {
        while (true) {
            // Untuk membersihkan inputan Scanner pertama
            inputScanner.nextLine();
            System.out.print("Apakah Anda ingin mesan lagi? (y/n): ");
            String input = inputScanner.nextLine().toLowerCase();

            if (input.equals("y")) {
                System.out.println("Silahkan memilih menu yang tersedia!");
                menu();
                return;
            } else if (input.equals("n")) {
                System.out.println("Silahkan melanjutkan pembayaran!");
                //Problem looping infinite
                confirmation();
                return;
            } else {
                System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
            }
        }
    }
    public static void qty(int hasil, int select) {
        int i = hasil * listPrice.get(select);
        listHarga.add(i);
    }

    public static void listOrder(int getSelect) {
        System.out.println("==========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("==========================\n");
        System.out.println(listMenu.get(getSelect) + "\t | "+listPrice.get(getSelect));
        System.out.println("(input 0 untuk kembali)\n");
        inputScanner.nextLine();
        System.out.print("qty => ");
        int result = inputScanner.nextInt();
        if (result == 0) {
            menu();
            getMenu();
        } else {
            listBuy(listMenu.get(getSelect));
            listQty.add(result);
            qty(result, getSelect);
            pesanan();
        }
    }

    public static void listBuy(String getSelect) {
        listSelect.add(getSelect);
    }

    public static void getMenu() {
        try {
            System.out.print("=> ");
            int result = inputScanner.nextInt();
            if (result >= 1 && result <= listMenu.size()) {
                listOrder(result - 1);
            } else if (result == 99) {
                confirmation();
            } else if (result == 0) {
                while (true) {
                    // Untuk membersihkan inputan Scanner pertama
                    inputScanner.nextLine();
                    System.out.print("Apakah Anda ingin keluar? (y/n): ");
                    String input = inputScanner.nextLine().toLowerCase();

                    if (input.equals("y")) {
                        System.out.println("Terima kasih! Program keluar.");
                        inputScanner.close();
                        System.exit(0);
                    } else if (input.equals("n")) {
                        System.out.println("Silahkan memilih menu yang tersedia!");
                        return;
                    } else {
                        System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
                    }
                }
            } else {
                System.out.println("Gagal, silahkan pilih ulang");
                getMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka.");
        } finally {
            getMenu();
        }
    }

    public static void menu() {
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================\n");
        System.out.println("Silahkan pilih makanan : ");
        for (int i = 0; i < listMenu.size(); i++) {
            String priceLine = (i == listMenu.size() - 1) ? "\t\t | " : "\t | ";
            System.out.println((i + 1) + ". " + listMenu.get(i) + priceLine + priceFormatted(listPrice.get(i)));
        }
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi");
    }
}
