package acorn.shop.entity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "customer") // 고객 테이블
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
/**
 * 회원 엔티티 클래스
 *
 * 예제:
 *
 * # 빌더를 사용하여 생성
 * Customer customer = Customer.builder()
 *              .customerId("user01")
 *              .customerName("람머스")
 *              .customerPassword("1234")
 *              .customerEmail("user@naver.com")
 *              .customerPhoneNumber("010-0000-0000")
 *              .customerAddress("서울시")
 *              .customerDetailAdd("어딘가")
 *              .build();
 *
 * # 회원 정보 업데이트
 * customer.updateCustomerPassword("5678");
 * customer.updateCustomerEmail("user_@naver.com");
 * customer.updateCustomerPhoneNumber("010-0000-0000");
 * customer.updateCustomerAddress("서울시");
 * customer.updateCustomerDetailAdd("어딘가");
 */
public class Customer {
    @Id
    @Column(name = "customer_id", unique = true, nullable = false)
    private String customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_password", nullable = false)
    private String customerPassword;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_detailadd")
    private String customerDetailAdd;

    @Column(name = "customer_registdate")
    @Temporal(TemporalType.DATE)
    private Date customerRegistDate;

    @PrePersist
    protected void onCreate() {
        customerRegistDate = new Date(); // default (생성 시점)
    }

    @PreUpdate
    protected void onUpdate() {
    }

    public void updateCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
    public void updateCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public void updateCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public void updateCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public void updateCustomerDetailAdd(String customerDetailAdd) {
        this.customerDetailAdd = customerDetailAdd;
    }
}
