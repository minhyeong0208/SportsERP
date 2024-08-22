package acorn.shop.dto.product;

import acorn.shop.dto.ShopDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO extends ShopDTO {
    private int productId;
    private String productName;
    private int productPrice;
    private int productQuantity;
    private Date productRegistDate;
    private Date productLastModiDate;
}