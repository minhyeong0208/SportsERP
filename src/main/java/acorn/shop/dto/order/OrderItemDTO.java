package acorn.shop.dto.order;

import acorn.shop.dto.ShopDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO extends ShopDTO {
    private int orderItemId;
    private int orderId;
    private int productId;
    private int orderItemQuantity;
    private int orderItemPrice;
    private Date orderItemRegistDate;
    private Date orderItemLastModiDate;
}