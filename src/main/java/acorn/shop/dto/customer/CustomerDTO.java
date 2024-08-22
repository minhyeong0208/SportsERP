package acorn.shop.dto.customer;

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
public class CustomerDTO extends ShopDTO {
    private String customerId;
    private String customerName;
    private String customerPassword;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerAddress;
    private String customerDetailAdd;
    private Date customerRegistdate;
}