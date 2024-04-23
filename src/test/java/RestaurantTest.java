import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class RestaurantTest {

    @Test
    public void testValidOrder() {
        // Persiapan input
        String input = "2\n3\n99\n1\n"; // Memilih menu Mie Goreng sebanyak 3 porsi, lalu konfirmasi dan bayar

        // Simulasikan input dari pengguna
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Eksekusi kode
        Restaurant restaurant = new Restaurant();
        restaurant.startRestaurantApp();

        // Periksa apakah pesanan ditambahkan dengan benar
        assertEquals(1, restaurant.getPesanan().size()); // Cek apakah pesanan ditambahkan
        assertEquals(1, restaurant.getKuantitas().size()); // Cek apakah kuantitas pesanan ditambahkan
        assertEquals(39000.0, restaurant.getTotalHarga(), 0.001); // Cek apakah total harga sesuai
        assertEquals(3, restaurant.getTotalItems()); // Cek apakah total item sesuai
    }

    @Test
    public void testInvalidOrder() {
        // Persiapan input
        String input = "-1\n0\n99\n1\n"; // Memilih menu dengan inputan jumlah pesanan yang tidak valid, lalu konfirmasi dan bayar

        // Simulasikan input dari pengguna
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Eksekusi kode
        Restaurant restaurant = new Restaurant();
        restaurant.startRestaurantApp();

        // Periksa apakah pesanan tidak berhasil ditambahkan
        assertEquals(0, restaurant.getPesanan().size()); // Cek apakah pesanan tidak ditambahkan
        assertEquals(0, restaurant.getKuantitas().size()); // Cek apakah kuantitas pesanan tidak ditambahkan
        assertEquals(0.0, restaurant.getTotalHarga(), 0.001); // Cek apakah total harga tetap nol
        assertEquals(0, restaurant.getTotalItems()); // Cek apakah total item tetap nol
    }
}
