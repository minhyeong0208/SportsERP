package acorn.shop.dto.order;

import acorn.shop.dto.ShopDTO;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends ShopDTO {
    private int orderId;
    private String customerId;
    private Date orderDate;
}