import org.challenge.model.Product;
import org.challenge.repository.ProductRepository;
import org.challenge.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceDemoTest {
    InputStream inputStream = System.in;
    ProductServiceImpl productService;
    ProductRepository productRepository;
    Product product;

    private List<String> dataMenu = new ArrayList<>(Arrays.asList("Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"));
    private List<Integer> dataPrice = new ArrayList<>(Arrays.asList(15000, 13000, 18000, 3000, 5000));

    @BeforeEach
    void init() {
        productRepository = new ProductRepository();
        productService = new ProductServiceImpl(productRepository);
        product = new Product();
    }

    @Test
    @DisplayName("Test Formatted Price")
    void testPriceFormatted_success() {
        String result = productService.priceFormatted(10000);
        assertEquals("10,000", result);
        assertDoesNotThrow(() -> productService.priceFormatted(null));
    }

    @Test
    @DisplayName("Test Validasi Menu")
    void testValidMenu_success_exit() {
        String userInput = "y\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        productService = new ProductServiceImpl();

        assertDoesNotThrow(() -> productService.validMenu());
    }

    @Test
    @DisplayName("Test Process Menu")
    void testProcessMenu_success() {
        String userInput = "1\n2\n" +
                "n\n" +
                "1\nYanto";

        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        productService = new ProductServiceImpl(productRepository);

        assertDoesNotThrow(() -> productService.processMenu());
    }


    @Test
    void testProcessMenuOrder_success() {
        String userInput = "1\n" +
                "n\n" +
                "1\nYanto";

        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        productService = new ProductServiceImpl(productRepository);

        assertDoesNotThrow(() -> productService.processMenuOrder(2));
    }

    @Test
    void testProcessMenu_throws() {
        String userInput = "1\nnull\n" +
                "n\n" +
                "1\n";

        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        productService = new ProductServiceImpl(productRepository);

        assertThrows(InputMismatchException.class, ()-> productService.processMenu());
    }

    @Test
    @DisplayName("Test all method input empty Throws")
    void testAllMethodInput_empty() {
        String userInput = "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        productService = new ProductServiceImpl(productRepository);

        assertThrows(NoSuchElementException.class, ()-> productService.processMenu());
        assertThrows(NoSuchElementException.class, ()-> productService.processMenuOrder(2));
        assertThrows(NoSuchElementException.class, ()-> productService.reorders());
        assertThrows(NoSuchElementException.class, ()-> productService.processConfirm());
        assertThrows(NoSuchElementException.class, ()-> productService.outputPayment(new StringBuilder("Hallo")));
    }

    @Test
    @DisplayName("Test all method input char Throws")
    void testAllMethodInput_char() {
        String userInput = "asd\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        productService = new ProductServiceImpl(productRepository);

        assertThrows(NoSuchElementException.class, ()-> productService.processMenu());
        assertThrows(NoSuchElementException.class, ()-> productService.processMenuOrder(2));
        assertThrows(NoSuchElementException.class, ()-> productService.processConfirm());
    }

    @Test
    @DisplayName("Test all method input int Throws")
    void testAllMethodInput_int() {
        String userInput = "1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        productService = new ProductServiceImpl(productRepository);

        assertThrows(NoSuchElementException.class, ()-> productService.validMenu());
        assertThrows(NoSuchElementException.class, ()-> productService.reorders());
    }

    @Test
    void testListBuy_success() {
        String result =  productService.listBuy("Nasi Goreng");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertDoesNotThrow(() -> result);
    }

    @Test
    void testListQty_success() {
        Boolean process = productService.listQty(2, 3);
        assertEquals(Boolean.TRUE, process);
        assertNotNull(process);
        assertTrue(process);
    }

    @Test
    void testListQty_error() {
        assertThrows(IndexOutOfBoundsException.class, () -> productService.listQty(2, 6));
    }

    @Test
    void testDataAmount_success_null() {
        List<Integer> bahan = Arrays.asList(10,10,10,20,30);
        Integer resultExpected = 80;

        assertEquals(resultExpected, productService.dataAmount(bahan));
        assertThrows(NullPointerException.class, () -> productService.dataAmount(null));
        assertThrows(NullPointerException.class, () -> productService.dataAmount(Arrays.asList(10,10,null,20,30)));
    }

    @Test
    void testOutputPayment_error() {
        assertThrows(NullPointerException.class, () -> productService.outputPayment(new StringBuilder(null)));
    }
}
