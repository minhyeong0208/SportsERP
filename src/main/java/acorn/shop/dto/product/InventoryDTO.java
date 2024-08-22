package acorn.shop.dto.product;

import acorn.shop.dto.ShopDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDTO extends ShopDTO {
    private int inventoryId;
    private String inventoryName;
    private String category;
    private int quantity;
    private Date purchaseDate;
    private BigDecimal price;
}