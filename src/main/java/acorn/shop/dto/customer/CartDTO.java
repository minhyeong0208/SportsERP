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
public class CartDTO extends ShopDTO {
    private int cartId;
    private String customerId;
}