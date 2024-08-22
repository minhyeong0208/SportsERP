package acorn.shop.entity.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;

import java.util.Date;

import acorn.shop.entity.customer.Customer;

@Entity
@Table(name = "orders") // 주문 테이블
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
/**
 * 주문 엔티티 클래스
 *
 * 예제:
 *
 * # 빌더를 사용하여 생성
 * Order order = Order.builder()
 *              .customer(customer)
 *              .orderDate(new Date())
 *              .build();
 *
 * # 주문 날짜 업데이트
 * order.updateOrderDate(new Date());
 */
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @PrePersist
    protected void onCreate() {
        orderDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    }

    // 주문 날짜 변경 메소드
    public void updateOrderDate(Date newOrderDate) {
        this.orderDate = newOrderDate;
    }
}
