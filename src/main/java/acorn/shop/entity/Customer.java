package acorn.shop.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name="customer_id")
    private String id;

    @Column(name="customer_name", nullable = false)
    private String name;

    @Column(name="customer_password")
    private String password;

    @Column(name="customer_email")
    private String email;

    @Column(name="customer_phone_number")
    private String phoneNumber;

    @Column(name="customer_address")
    private String address;

    @Column(name="customer_detailadd")
    private String detailAddress;

    @Column(name="customer_registdate")
    private Date registDate;

    @PrePersist
    public void onPrePersist() {
        this.registDate = new Date(); // 현재 날짜로 설정
    }
}
