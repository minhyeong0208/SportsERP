package acorn.shop.dto.customer;

import acorn.shop.dto.ShopDTO;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO extends ShopDTO {
    private int cartItemId;
    private int cartId;
    private int productId;
    private int cartItemQuantity;
}