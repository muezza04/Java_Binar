package org.challenge;

import java.io.*;
import java.util.*;

public class Challenge1 {
    // Inisialisasi list menu dan harga
    static List<String> listMenu = Arrays.asList(new String[] {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"});
    static List<Integer> listPrice = Arrays.asList(new Integer[] {15000, 13000, 18000, 3000, 5000});
    // List untuk menyimpan pesanan yang dipilih
    static List<String> listSelect = new ArrayList<>();
    static List<Integer> listQty = new ArrayList<>();
    static List<Integer> listHarga = new ArrayList<>();
    // Scanner untuk membaca input dari pengguna
    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Memeriksa keselarasan antara listMenu dan listPrice
        if (listMenu.size() != listPrice.size()) {
            System.out.println("Error!, Ada kesalahan pada sistem");
            System.exit(0);
        }
        // Menampilkan menu awal dan meminta input dari pengguna
        menu();
        getMenu();
    }

    // Fungsi untuk mengubah harga menjadi format mata uang
    public static String priceFormatted(int data) {
        return String.format("%,d", data);
    }

    // Fungsi untuk membaca data dari file
    public static void read(String originFile) {
        try {
            File file = new File(originFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] tempArr;
            while((line = bufferedReader.readLine()) != null) {
                tempArr = line.split(",");
                for (String s : tempArr) {
                    System.out.print(s + " ");
                }
                System.out.println();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk menulis data ke dalam file
    public static void write(String targetFile, String data) {
        try {
            File file = new File(targetFile);
            if(file.createNewFile()) {
                System.out.println("file has been created");
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.flush();
            bw.close();
            System.out.println("Penulisan ke file succes\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk melakukan pembayaran
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
        // Membaca dan menampilkan isi file yang telah ditulis
        read("D:\\Aplication\\Running\\java\\BE_JAVA\\Binar\\BinarChallenge\\src\\main\\resources\\"+result+".txt");
    }

    // Fungsi untuk menghasilkan tampilan detail pesanan
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

    // Fungsi untuk konfirmasi dan pembayaran
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

        //Melakukan penjumlahan total qty dan harga pembelian
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

        try {
            System.out.print("=> ");
            int result = inputScanner.nextInt();
            if (result == 0) {
                inputScanner.close();
                System.exit(0);
            } else if (result == 1) {
                payment();
                System.exit(0);
            } else if (result == 2) {
                // Problem looping infinite
                pesanan();
            } else  {
                System.out.println("Gagal, silahkan pilih ulang");
                confirmation();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka!");
            inputScanner.nextLine();
            confirmation();
        }
    }

    // Fungsi untuk memesan makanan
    public static void pesanan() {
        inputScanner.nextLine();
        while (true) {
            System.out.print("Apakah Anda ingin mesan lagi? (y/n): ");
            String input = inputScanner.nextLine().toLowerCase();

            if (input.equals("y")) {
                System.out.println("Silahkan memilih menu yang tersedia!");
                menu();
                getMenu();
                return;
            } else if (input.equals("n")) {
                System.out.println("Silahkan melanjutkan pembayaran!");
                // Problem looping infinite
                confirmation();
                return;
            } else {
                System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
            }
        }
    }

    // Fungsi untuk menghitung harga total berdasarkan jumlah pesanan
    public static void qty(int hasil, int select) {
        int i = hasil * listPrice.get(select);
        listHarga.add(i);
    }

    // Fungsi untuk memesan jumlah makanan tertentu
    public static void listOrder(int getSelect) {
        System.out.println("==========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("==========================\n");
        System.out.println(listMenu.get(getSelect) + "\t | "+listPrice.get(getSelect));
        System.out.println("(input 0 untuk kembali)\n");

        try {
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
            System.exit(0);
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka!");
            listOrder(getSelect);
        }
    }

    // Fungsi untuk menambahkan makanan yang dibeli ke dalam list
    public static void listBuy(String getSelect) {
        listSelect.add(getSelect);
    }

    // Fungsi untuk menampilkan menu utama
    public static void getMenu() {
        System.out.print("=> ");
        try {
            int result = inputScanner.nextInt();

            if (result >= 1 && result <= listMenu.size()) {
                listOrder(result - 1);
            } else if (result == 99) {
                confirmation();
            } else if (result == 0) {
                inputScanner.nextLine();
                System.out.print("Apakah Anda ingin keluar? (y/n): ");
                String input = inputScanner.nextLine().toLowerCase();

                if (input.equals("y")) {
                    System.out.println("Terima kasih! Program keluar.");
                    inputScanner.close();
                    System.exit(0);
                } else if (input.equals("n")) {
                    System.out.println("Silahkan memilih menu yang tersedia!");
                } else {
                    System.out.println("Masukkan tidak valid. Ketik 'y' untuk keluar atau 'n' untuk lanjut.");
                    getMenu();
                }
            } else {
                System.out.println("Gagal, silahkan pilih ulang");
                getMenu();
            }
        } catch (InputMismatchException e) {
            inputScanner.nextLine();
            System.out.println("Input tidak valid. Masukkan angka!");
            getMenu();
        }
    }

    // Fungsi untuk menampilkan menu awal
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
