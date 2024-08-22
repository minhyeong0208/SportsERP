package acorn.shop.entity.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;

import java.util.Date;

import acorn.shop.entity.product.Product;

@Entity
@Table(name = "orderitem") // 주문 항목 테이블
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
/**
 * OrderItem Entity 클래스
 *
 * # 빌더를 사용하여 생성
 * OrderItem orderItem = OrderItem.builder()
 *              .order(order)
 *              .product(product)
 *              .orderItemQuantity(3)
 *              .orderItemPrice(4500)
 *              .build();
 *
 * # 수량 및 가격 업데이트
 * orderItem.updateQuantity(5);
 * orderItem.updatePrice(6000);
 */
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitem_id")
    private int orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "orderitem_quantity", nullable = false)
    private int orderItemQuantity;

    @Column(name = "orderitem_price", nullable = false)
    private int orderItemPrice;

    @Column(name = "orderitem_registdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderItemRegistDate;

    @Column(name = "orderitem_lastmodidate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderItemLastModiDate;

    @PrePersist
    protected void onCreate() {
        orderItemRegistDate = new Date();
        orderItemLastModiDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        orderItemLastModiDate = new Date();
    }

    public void updateQuantity(int newQuantity) {
        this.orderItemQuantity = newQuantity;
    }

    public void updatePrice(int newPrice) {
        this.orderItemPrice = newPrice;
    }
}

