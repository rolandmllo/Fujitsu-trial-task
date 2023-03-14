import app.api.controller.PriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: implement API tests
@SpringBootTest(classes = app.Application.class)
public class APITests {
    @Autowired
    PriceController priceController;
    @Test
    public void contextLoads() {
        assertThat(priceController).isNotNull();
    }
}
