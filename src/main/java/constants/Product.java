package constants;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Product {

    private String productTitle = "";
    private String itemNumber = "";
    private double price = 1;
    private int quantity=1;
    private double subtotal = price*quantity;
}
