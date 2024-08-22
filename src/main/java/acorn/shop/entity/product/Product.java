package acorn.shop.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "product") // 상품 테이블
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
/**
 * Product Entity 클래스
 *
 * # 빌더를 사용하여 생성
 * Product product = Product.builder()
 *              .productName("Laptop")
 *              .productPrice(1500)
 *              .productQuantity(20)
 *              .build();
 *
 * # 수량 및 가격 업데이트
 * product.updateQuantity(30);
 * product.updatePrice(750);
 */
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_quantity", nullable = false)
    private int productQuantity;

    @Column(name = "product_registdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productRegistDate;

    @Column(name = "product_lastmodidate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productLastModiDate;

    @PrePersist
    protected void onCreate() {
        productRegistDate = new Date();
        productLastModiDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        productLastModiDate = new Date();
    }

    public void updateProductName(String newProductName) {
        this.productName = newProductName;
    }
    public void updateQuantity(int newQuantity) {
        this.productQuantity = newQuantity;
    }
    public void updatePrice(int newPrice) {
        this.productPrice = newPrice;
    }
}