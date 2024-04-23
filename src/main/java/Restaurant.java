import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Restaurant {
    private static final ArrayList<MenuItem> MENU_ITEMS = new ArrayList<>();

    private ArrayList<String> pesanan = new ArrayList<>();
    private ArrayList<Integer> kuantitas = new ArrayList<>();
    private double totalHarga = 0;
    private int totalItems = 0;

    static {
        // Initialize menu items
        MENU_ITEMS.add(new MenuItem("Nasi Goreng", 15000.0));
        MENU_ITEMS.add(new MenuItem("Mie Goreng", 13000.0));
        MENU_ITEMS.add(new MenuItem("Nasi + Ayam", 18000.0));
        MENU_ITEMS.add(new MenuItem("Es Teh", 3000.0));
        MENU_ITEMS.add(new MenuItem("Es Jeruk", 5000.0));
    }

    public ArrayList<String> getPesanan() {
        return pesanan;
    }

    public ArrayList<Integer> getKuantitas() {
        return kuantitas;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void startRestaurantApp() {
        Scanner input = new Scanner(System.in);
        boolean inginPesanLagi = true;

        System.out.println("==========================");
        System.out.println("Selamat Datang di BinarFud");
        System.out.println("==========================");

        try {
            while (inginPesanLagi) {
                System.out.println("Silahkan Pilih Makanan :");
                MENU_ITEMS.stream()
                        .map(menuItem -> (MENU_ITEMS.indexOf(menuItem) + 1) + ". " + menuItem.getName() + " | Rp. " + menuItem.getPrice())
                        .forEach(System.out::println);
                System.out.println("99. Pesan dan Bayar");
                System.out.println("0. Keluar Aplikasi");
                System.out.print("=> ");

                int pilih = input.nextInt();

                if (pilih == 0) {
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    break;
                }

                Optional<MenuItem> selectedItem = Optional.empty();
                if (pilih != 99 && (pilih >= 1 && pilih <= MENU_ITEMS.size())) {
                    selectedItem = Optional.of(MENU_ITEMS.get(pilih - 1));
                }

                if (pilih != 0 && pilih != 99 && selectedItem.isPresent()) {
                    System.out.println("====================");
                    System.out.println("Berapa " + selectedItem.get().getName() + " yang Anda pesan?");
                    System.out.print("Masukkan jumlah pesanan (0 untuk Kembali): ");
                    int qty = input.nextInt();

                    if (qty <= 0) {
                        System.out.println("Jumlah pesanan tidak valid. Mohon masukkan angka yang lebih besar dari 0.");
                        continue;
                    }

                    pesanan.add(selectedItem.get().getName());
                    kuantitas.add(qty);
                    totalHarga += selectedItem.get().getPrice() * qty;
                    totalItems += qty;
                } else if (pilih == 99) {
                    inginPesanLagi = false;
                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih nomor dari menu yang tersedia.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Mohon masukkan input yang valid.");
            input.nextLine(); // Clear the input buffer
        }

        if (totalItems > 0) {
            // Display order details
            System.out.println("========================");
            System.out.println("Konfirmasi & Pembayaran");
            System.out.println("========================");
            System.out.println("Pesanan Anda:");
            pesanan.forEach(menu -> {
                int qty = kuantitas.get(pesanan.indexOf(menu));
                double hargaMenu = MENU_ITEMS.stream().filter(item -> item.getName().equals(menu)).findFirst().get().getPrice();
                double subtotal = hargaMenu * qty;
                System.out.println(menu + " | Qty: " + qty + " | Total: Rp." + subtotal);
            });

            // Payment options
            System.out.println("Total Harga: Rp." + totalHarga);
            System.out.println("========================");
            System.out.println("Menu Pembayaran:");
            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke Menu Utama");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("=> ");

            int pilihanPembayaran = input.nextInt();
            switch (pilihanPembayaran) {
                case 1:
                    // Generate receipt
                    try {
                        FileWriter myWriter = new FileWriter("struk_pembelian.txt");
                        myWriter.write("========================\n");
                        myWriter.write("Struk Pembelian:\n");
                        pesanan.forEach(menu -> {
                            int qty = kuantitas.get(pesanan.indexOf(menu));
                            double hargaMenu = MENU_ITEMS.stream().filter(item -> item.getName().equals(menu)).findFirst().get().getPrice();
                            double subtotal = hargaMenu * qty;
                            try {
                                myWriter.write(menu + " | Qty: " + qty + " | Total: Rp." + subtotal + "\n");
                                System.out.println(menu + " | Qty: " + qty + " | Total: Rp." + subtotal);
                            } catch (IOException e) {
                                System.out.println("Terjadi kesalahan dalam menyimpan struk pembelian.");
                                System.out.println("Mohon maaf, pembayaran tidak berhasil. Silakan coba lagi.");
                            }
                        });
                        myWriter.write("Total Harga: Rp." + totalHarga + "\n");
                        myWriter.write("Terima kasih telah menggunakan layanan kami.\n");
                        myWriter.close();
                        System.out.println("Struk pembelian telah disimpan dalam file 'struk_pembelian.txt'");
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan dalam menyimpan struk pembelian.");
                        System.out.println("Mohon maaf, pembayaran tidak berhasil. Silakan coba lagi.");
                    }
                    break;
                case 2:
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } else {
            System.out.println("Tidak ada pesanan yang diproses.");
        }
    }
}
